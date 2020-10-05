package mymvc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * session 使用频率远远小于 request
 * 所以使用注解存储 Controller 类要带走的 Session 值
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SessionAttributes {

    String[] value();

}
