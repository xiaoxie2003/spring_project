package com.yc.test.aspects;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class HiAspect implements Ordered { //实现接口设置优先级

    //切入点表达式
    @Pointcut("execution(* com.yc.test.biz..*add*(..))")
    private void abc(){}

    //下面的方法是一个要加入的增强功能的方法，他会被加到acb（）的注解指定的位置
    @Before("abc()")
    public void doAccessCheck(){
        System.out.println("hi......");
    }

    /**
     * 设置优先级的方法
     * @return
     */
    @Override
    public int getOrder() {
        return 1;
    }
}
