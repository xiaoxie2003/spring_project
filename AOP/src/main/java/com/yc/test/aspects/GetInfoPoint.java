package com.yc.test.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 如何获取连接点的信息
 */
//@Aspect
//@Component
public class GetInfoPoint {

    @Pointcut("execution(* com.yc.test.biz..*(..))")
    private void abc(){}

    @Before("abc()")  //获取加了增强的连接点的信息
    public void showInfo(JoinPoint jp){ //连接点对象
        System.out.println("连接点的信息：");
        System.out.println("目标类：" + jp.getTarget() + "，方法的签名：" + jp.getSignature());
        System.out.println("加了增强方法中的参数值：");

        Object[] objs = jp.getArgs();
        if(objs!=null && objs.length>0){
            for(Object obj:objs){
                System.out.println(obj);
            }
        }

    }
}
