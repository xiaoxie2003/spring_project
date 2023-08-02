package com.yc.jdkproxy;

import java.lang.reflect.InvocationHandler;  //JDK提供的
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

public class CustomInvocationHandle implements InvocationHandler {
    private Object target; // 目标类

    //传入目标类
    public CustomInvocationHandle(Object target) {
        this.target = target;
    }

    //生成代理对象的方法
    public Object creatProxy(){
        //面向接口！！！
        //jdk中提供了 Proxy类 有一个方法专门用于根据接口生成代理对象的方法
        //第一个参数：类加载器， 第二个参数：接口， 第三个参数：调用处理器
        Object proxy = Proxy.newProxyInstance(CustomInvocationHandle.class.getClassLoader(),target.getClass().getInterfaces(),this);
        return proxy;  //$proxy01  代理对象 ： sayHello（）  showBye（）
        //调用proxy代理对象时会自动回调CustomInvocationHandle实现的接口InvocationHandler里的方法
    }

    /**
     *
     * @param proxy 代理对象
     * @param method 调用的方法 sayHello（）
     * @param args 方法的参数值
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().indexOf("say")>=0){  //解析@Pointcut("execution(* com.yc.test.biz..*(..))")切入点表达式
            showTime();  //增强
        }
        //反射机制调用目标类的目标方法
        Object returnValue = method.invoke(target,args);
        if(method.getName().indexOf("say")>=0){  //解析@Pointcut("execution(* com.yc.test.biz..*(..))")切入点表达式
            showTime();  //增强
        }
        return returnValue;
    }


    //增强的方法
    public void showTime(){
        System.out.println("时间为：" + new Date());
    }
}
