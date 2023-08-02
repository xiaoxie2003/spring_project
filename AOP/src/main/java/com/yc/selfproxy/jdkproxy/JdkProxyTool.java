package com.yc.selfproxy.jdkproxy;

import org.aspectj.lang.annotation.Pointcut;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 */
public class JdkProxyTool implements InvocationHandler {
    //目标类
    private Object target;

    public JdkProxyTool(Object obj) {
        this.target = obj;
    }

    //生成代理对象的方法
    public Object createProxy() {
        Object proxy = Proxy.newProxyInstance(JdkProxyTool.class.getClassLoader(),
                target.getClass().getInterfaces(),
                this);
        return proxy;
    }

    //当在主程序中调用生成的Proxy的中的方法 会自动回调这个invoke（）在这个invoke（）加入增强，切面这些功能
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //以反射的方式读取下面的@PointCut中的切入点的表达式 => 在spring底层 用aspectJ来读取表达式
        if(method.getName().startsWith("add")){
            showHolle();
        }
        //showHolle(); //加入前置增强
        // orderBizImpl.findOrderId()
        Object returnValue = method.invoke(target,args);  //调用目标类的方法
        return returnValue;
    }

    //@Pointcut("execution(* com.yc.aop1.biz.*.make*(..))")

    //增强
    public void showHolle(){
        System.out.println("hello");
    }
    //增强
    public void showBye(){
        System.out.println("Bye");
    }
}
