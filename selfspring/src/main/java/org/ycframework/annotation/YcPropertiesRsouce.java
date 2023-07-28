package org.ycframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface YcPropertiesRsouce {
    String[] value();  //表示用了@YcPropertiesRsouce注解 一定要指定value
}
