package mymvc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

/**
 * 仿照 Tomcat 对 *.jsp 请求进行统一的编译处理，我规定 *.do 请求为 controller 请求。
 *
 * 参考配置文件（只需要8行）后，找到这个类（继承HttpServlet，重写service方法）
 * 但这个类不做事，只负责处理分发
 *      同一个类型的所有方法放在一个类中，所以请求中需要包含类名与方法名。
 *      于是我规定请求如此写 “类名.do?method=方法名”
 */
public class DispatcherServlet extends HttpServlet {

    //缓存机制，提高性能
    private HashMap<String, String> realNameMap = new HashMap<>();

    //获取全部类全名（参考自己写的 properties 配置文件）
    @Override
    public void init() throws ServletException {
        try {
            Properties pro = new Properties();
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("ApplicationContext.properties");
            pro.load(inputStream);
            Enumeration en = pro.propertyNames();
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();
                String value = pro.getProperty(key);
                realNameMap.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //负责处理所有的.do请求, 并进行分发
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //1.获取请求的类名
            String uri = request.getRequestURI();//工程名/资源名.do
            uri = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));

            //2.从缓存中获取类全名
            String realControllerName = realNameMap.get(uri);
            if (realControllerName == null) {
                throw new ControllerNotFoundException("404: 请求对应的Controller没有找到");
            }

            //3.反射寻找类的对象
            Class c = Class.forName(realControllerName);
            Object obj = c.getConstructor().newInstance();

            //4.反射寻找类的方法
            String methodName = request.getParameter("method");
            Method method = c.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            //5.执行方法
            method.invoke(obj, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
