package com.yc.test.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 切面类
 */
//@Component //spring托管才行
//@Aspect  //设置切面类
//@Order(9)  //设置输出的优先级 -> 数字越小优先级越高 进来是优先级高的先运行，出去是优先级高的后运行
public class HelloWordAspect {

    //切入点表达式
    @Pointcut("execution(* com.yc.test.biz..*add*(..))") //add* 表示方法名为addxxx（）
    private void abc(){}

    //下面的方法是一个要加入的增强功能的方法，他会被加到acb（）的注解指定的位置
    @Before("abc()")
    public void doAccessCheck(){
        System.out.println("hello world......");
    }
    /*
        Before：在原方法之前运行
        After：在原方法之后运行
        Around：在原方法前后都执行
     */
}
