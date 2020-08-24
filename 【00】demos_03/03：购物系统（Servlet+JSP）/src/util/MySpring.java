package util;

import java.util.HashMap;

public class MySpring {

    private static HashMap<String,Object> beanMap = new HashMap<>();

    public static synchronized <T>T getBean(String className){
        T obj = (T)beanMap.get(className);
        if(obj == null){
            try {
                Class clazz = Class.forName(className);
                obj = (T)clazz.getDeclaredConstructor().newInstance();
                beanMap.put(className,obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

}
