package org.ycframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)//TYPE:类接口
@Retention(RetentionPolicy.RUNTIME)  //RUNTIME:运行期
@YcComponent
public @interface YcConfiguration {

}
