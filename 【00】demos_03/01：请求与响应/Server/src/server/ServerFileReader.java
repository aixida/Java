package server;

import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

//类的目的是在服务器启动的时候
//读取server.properties的配置文件----port
public class ServerFileReader {

    private static HashMap<String,String> map = new HashMap<>();

    static{
        try {
            Properties pro = new Properties();
            pro.load(new FileReader("src//server.properties"));
            Enumeration en = pro.propertyNames();
            while(en.hasMoreElements()){
                String key = (String)en.nextElement();
                String value = pro.getProperty(key);
                map.put(key,value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key){
        return map.get(key);
    }

}