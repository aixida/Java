package pool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConfig {

    private static Properties dbConfig;

    static {
        try {
            dbConfig = new Properties();
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("dbconfig.properties");
            dbConfig.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getConfig(String key){
        return dbConfig.getProperty(key,"");
    }

    public static String getConfig(String key,String defaultValue){
        return dbConfig.getProperty(key,defaultValue);
    }

    public static Integer getIntegerValue(String key){
        return Integer.parseInt(dbConfig.getProperty(key,""));
    }

    public static Integer getIntegerValue(String key,String defaultValue){
        return Integer.parseInt(dbConfig.getProperty(key,defaultValue));
    }

}
