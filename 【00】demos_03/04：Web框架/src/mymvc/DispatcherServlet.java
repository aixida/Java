package mymvc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.*;
import java.net.URL;
import java.util.*;

/**
 * 仿照 Tomcat 对 *.jsp 请求进行统一的编译处理，我规定 *.do 请求为 controller 请求。
 *
 * 参考配置文件（只需要8行）后，找到这个类（继承HttpServlet，重写service方法）
 * 但这个类不做事，只负责处理分发
 *      同一个类型的所有方法放在一个类中，所以请求中需要包含类名与方法名。
 *      于是我规定请求如此写 “类名.do?method=方法名”
 */
public class DispatcherServlet extends HttpServlet {

    //为了解耦，集合存储全部对象中的所有方法 Map<对象, Map<方法名, Method>>
    private Map<Object, Map<String, Method>> objectMethodMap = new HashMap<>();

    //生命周期托管，单例模式（请求名 -- 对象）
    private Map<String, Object> objectMap = new HashMap<>();

    //缓存机制，提高性能（请求名 -- 类全名）
    private Map<String, String> realNameMap = new HashMap<>();

    //扫描配置文件 加载类名字
    private void loadProperties() {
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

    //扫描注解 加载类名字
    private void scanAnnotation() {
        String scanPackage = realNameMap.get("scanPackage");
        if (scanPackage != null) {
            String[] packageNames = scanPackage.split(",");
            for (String packageName:packageNames) {
                //获取包路径
                URL url = Thread.currentThread().getContextClassLoader().getResource(packageName.replace(".", "/"));
                if (url == null) {
                    System.out.println(packageName + "包不存在");
                    continue;
                }
                //操作真实文件
                String packagePath = url.getPath();//包全名
                File packageFile = new File(packagePath);
                //遍历包中所有class类
                File[] files = packageFile.listFiles();
                for (File file:files) {
                    String fileName = file.getName();//AtmController.class
                    fileName = fileName.substring(0, fileName.lastIndexOf("."));
                    String className = packageName + "." + fileName;//类全名
                    //反射
                    try {
                        Class c = Class.forName(className);
                        RequestMapper mapper = (RequestMapper) c.getAnnotation(RequestMapper.class);
                        if (mapper != null) {
                            //类名.do?method=方法名
                            realNameMap.put(mapper.value(), className);
                        } else {
                            //方法名.do
                            //...（注解写在方法上，需要扫描类中全部方法）
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //获取全部类全名
    @Override
    public void init() throws ServletException {
        this.loadProperties();
        this.scanAnnotation();
    }

    //负责处理所有的.do请求, 并进行分发
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //1.获取请求的类名
            String uri = request.getRequestURI();//工程名/资源名.do
            uri = uri.substring(uri.lastIndexOf("/") + 1);

            //2.从缓存中获取类全名
            String realControllerName = realNameMap.get(uri);
            if (realControllerName == null) {
                throw new ControllerNotFoundException("404: 请求对应的Controller没有找到");
            }

            //3.寻找类的对象
            Object obj = objectMap.get(uri);
            if (obj == null) {
                //3.1 加载对象
                Class c = Class.forName(realControllerName);//延迟加载
                obj = c.getConstructor().newInstance();
                objectMap.put(uri, obj);
                //3.2 加载对象中的方法
                Method[] methods = c.getDeclaredMethods();
                Map<String, Method> mm = new HashMap<>();//存放对象中全部方法
                for (Method method:methods) {
                    mm.put(method.getName(), method);
                }
                objectMethodMap.put(obj, mm);//存放对象及其方法
            }

            //4.寻找类的方法
            String methodName = request.getParameter("method");
            Method method = objectMethodMap.get(obj).get(methodName);

            //5.解析方法中参数
            Parameter[] parameters = method.getParameters();
            Object[] paramValues = new Object[parameters.length];//按方法中参数顺序存储值
            for (int i =0; i < parameters.length; i++) {
                Parameter parameter = parameters[i];
                Param parameterAnnotation = parameter.getAnnotation(Param.class);//获取参数前注解
                Class paramClass = parameter.getType();
                if (parameterAnnotation != null) {//基本数据类型、String（一个或多个参数） 每个参数前规定写有注解
                    String requestParamValue = request.getParameter(parameterAnnotation.value());
                    if (requestParamValue != null) {
                        //参数自动注入
                        if (paramClass == String.class) {
                            paramValues[i] = requestParamValue;
                        } else if (paramClass == int.class || paramClass == Integer.class) {
                            paramValues[i] = Integer.valueOf(requestParamValue);
                        } else if (paramClass == float.class || paramClass == Float.class) {
                            paramValues[i] = Float.valueOf(requestParamValue);
                        } else if (paramClass == double.class || paramClass == Double.class) {
                            paramValues[i] = Double.valueOf(requestParamValue);
                        }
                        //多个else
                    }
                } else {//数组 对象 map（只有一个参数） 规定参数前不写注解
                    if (paramClass.isArray()) {
                        //...不写了
                    } else {
                        Object paramObj = paramClass.getConstructor().newInstance();
                        //map
                        if (paramObj instanceof Map) {
                            Map<String, String> map = (Map<String, String>) paramObj;
                            Enumeration en = request.getParameterNames();
                            while (en.hasMoreElements()) {
                                String key = (String) en.nextElement();
                                String value = request.getParameter(key);
                                map.put(key, value);
                            }
                            paramValues[i] = map;
                        } else if (paramObj instanceof Object) {//对象
                            Field[] fields = paramClass.getDeclaredFields();
                            for (Field field:fields) {
                                field.setAccessible(true);
                                Class fieldType = field.getType();
                                Constructor fieldCon = fieldType.getConstructor(String.class);//基本数据类型、String
                                field.set(paramObj, fieldCon.newInstance(request.getParameter(field.getName())));
                            }
                            paramValues[i] = paramObj;
                        }
                    }
                }
            }

            //6.执行方法
            Object methodResult = method.invoke(obj, paramValues);
            String viewName;
            if (methodResult instanceof ModelAndView) {
                ModelAndView mv = (ModelAndView) methodResult;

                //7.解析ModelAndView
                HashMap<String, Object> mvMap = mv.getAttributeMap();
                Iterator<String> it = mvMap.keySet().iterator();
                while (it.hasNext()) {
                    String key = it.next();
                    Object value = mvMap.get(key);
                    request.setAttribute(key, value);
                }

                //8.解析session注解
                SessionAttributes sa = obj.getClass().getAnnotation(SessionAttributes.class);
                if (sa != null) {
                    String[] sessionAttrNames = sa.value();
                    if (sessionAttrNames.length != 0) {
                        HttpSession session = request.getSession();
                        for (String sessionAttriName:sessionAttrNames) {
                            session.setAttribute(sessionAttriName, mvMap.get(sessionAttriName));
                        }
                    }
                }

                viewName = mv.getViewName();
            } else {
                viewName = (String) methodResult;//methodResult instanceof String 若直接返回路径资源名
            }

            //9.处理转发与重定向
            if (methodResult != null && !"".equals(methodResult) && !"null".equals(methodResult)) {
                String[] vn = viewName.split(":");
                //处理请求转发
                if (vn.length == 1) {
                    request.getRequestDispatcher(viewName).forward(request, response);
                }
                //处理请求重定向
                if (vn[0].equals("redirect")) {
                    response.sendRedirect(vn[1]);
                }
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
           throw new MethodParamNoAnnoException("方法参数为基本类型、String时必须添加注解");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
