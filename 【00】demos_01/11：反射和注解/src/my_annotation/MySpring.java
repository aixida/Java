package my_annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

public class MySpring {

    //属性 为了存储所有被管理的对象
    private static HashMap<String,Object> beanBox = new HashMap<>();

    public static <T>T getBean(String className){//参数为类全名
        T obj = null;
        try {
            Class c = Class.forName(className);

            //0.将注解中的值拿出来
            Constructor con = c.getConstructor();
            Annotation a = con.getAnnotation(MyAnnotation.class);
            Class c2 = a.getClass();
            Method m2 = c2.getMethod("value");
            String[] values = (String[]) m2.invoke(a);

            //1.直接从beanBox集合中获取
            obj = (T)beanBox.get(className);
            //2.如果obj是null，证明之前没有创建过对象
            if (obj == null){
                obj = (T)c.getDeclaredConstructor().newInstance();
                beanBox.put(className, obj);
            }

            //把所有的属性都找到
            Field[] fs = c.getDeclaredFields();
            int i = 0;
            for(Field field:fs){
                //1.获取属性名
                String fieldName = field.getName();

                //2.手动的拼接串---setXXX方法
                String first = fieldName.substring(0,1).toUpperCase();
                String other = fieldName.substring(1);
                StringBuilder builder = new StringBuilder("set");
                builder.append(first);
                builder.append(other);

                //3.获取属性的类型，为了找寻setXXX方法时传递参数
                Class fieldTypeClass = field.getType();

                //4.通过处理好的setXXX方法名，找寻类中的setXXX方法
                Method m = c.getMethod(builder.toString(), fieldTypeClass);

                //5.赋值
                //补充：八个基本类型的包装类有七个都含有带String的构造方法，
                //     除了Character都有参数为String的构造方法
                m.invoke(obj, fieldTypeClass.getConstructor(String.class).newInstance(values[i]));

                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}