package pool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 读取 dbconfig.properties 配置文件
 * 项目启动时, 读取一遍即可
 * 使用缓存
 */
public class DBConfig {

    //缓存
    private static Map<String,String> map = new HashMap<>();

    //类加载时, 读取文件内容存入缓存
    static {
        try {
            Properties pro = new Properties();//本质上是一个集合, 读取文件数据像IO
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("dbconfig.properties");
            pro.load(in);
            Enumeration en = pro.propertyNames();
            while (en.hasMoreElements()){
                String key = (String) en.nextElement();
                String value = pro.getProperty(key);
                map.put(key,value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getStringValue(String key){
        return map.get(key);
    }

    public static Integer getIntegerValue(String key){
        return Integer.parseInt(map.get(key));
    }

}
