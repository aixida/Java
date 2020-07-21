package util;

import java.util.HashMap;

public class MySpring {

    public static HashMap<String, Object> beanBox = new HashMap<>();

    public static <T>T getBean(String className){//类全名
        T obj = (T)beanBox.get(className);
        try {
            if(obj == null){
                Class c = Class.forName(className);
                obj = (T) c.getDeclaredConstructor().newInstance();
                beanBox.put(className,obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}
