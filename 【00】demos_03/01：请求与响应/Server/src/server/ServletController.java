package server;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

//这个类的目的是为了管理findController方法
//1.方法与之前服务器Handler做的事情不一致，抽离出来
//2.每一次寻找Controller类的时候都需要参考一下web.properties
//      读取文件性能会比较低，增加一个缓存机制
//3.每一个Controllerl类都是由findController来找寻
//      找到了Controller类的目的是为了执行里面的方法
//      让类中的方法有一个统一的规则--便于查找和使用
//4.发现Controller类与dao，service相似，只有方法执行，没有属性
//      让Controller类的对象变成单例模式
public class ServletController {

    //缓存: 存储配置文件web.properties中的信息（请求名字=真实的Controller的类全名）
    private static HashMap<String,String> controllerNameMap = new HashMap<>();
    //生命周期托管: 存储被管理的所有的Controller类对象
    private static HashMap<String,HttpServlet> controllerObjectMap = new HashMap<>();

    //两个集合的使用，是为了延迟加载模式

    //静态块，在当前类加载的时候将配置文件中的所有信息读取出来存入缓存集合中
    static {
        Properties pro = new Properties();
        try {
            pro.load(new FileReader("src//web.properties"));
            Enumeration en = pro.propertyNames();
            while(en.hasMoreElements()){
                String content = (String)en.nextElement();
                String realContentName = pro.getProperty(content);//类全名
                controllerNameMap.put(content,realContentName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //找--控制层(controller或者action或者servlet)--干活
    public static void findController(HttpServletRequest request,HttpServletResponse response) {
        //获取request对象中的请求名字
        String content = request.getContent();
        try {
            //先看缓存
            HttpServlet controllerObj = controllerObjectMap.get(content);
            //缓存中无
            if(controllerObj == null){
                String realControllerName = controllerNameMap.get(content);//从缓存中获取类全名
                if(realControllerName != null){
                    Class c = Class.forName(realControllerName);
                    controllerObj = (HttpServlet) c.getConstructor().newInstance();
                    controllerObjectMap.put(content,controllerObj);
                }
            }
            //------------以上可以确保controllerObject对象肯定存在---------------------
            Class controllerClass = controllerObj.getClass();
            Method method = controllerClass.getMethod("service", HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(controllerObj, request, response);
        } catch (NullPointerException e) {
            response.write("请求的" + content + "Controller不存在");
        } catch (NoSuchMethodException e){
            response.write("405, 没有可以执行的方法");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}