package com.yc.cglibproxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


public class CglibProxyTool implements MethodInterceptor {
    //目标类
    private Object target;

    public CglibProxyTool(Object target) {
        this.target = target;
    }

    //生成代理对象的方法
    public Object createProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        Object proxy = enhancer.create();
        return proxy;
    }


    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        //以反射的方式读取下面的@PointCut中的切入点的表达式 => 在spring底层 用aspectJ来读取表达式
        if(method.getName().startsWith("add")){
            showHolle();
        }
        //showHolle(); //加入前置增强
        // orderBizImpl.findOrderId()
        Object returnValue = method.invoke(target,args);  //调用目标类的方法
        return returnValue;
    }

    //增强
    public void showHolle(){
        System.out.println("hello");
    }
}
