package com.yc.jdkproxy;

public class Test {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        //目标类
        HelloI target = new HelloImpl();
        CustomInvocationHandle handle = new CustomInvocationHandle(target);
        //生成代理类
        Object proxy = handle.creatProxy();
        System.out.println(proxy);

        HelloI hi = (HelloI) proxy;
        hi.sayHello();
        hi.showBye();
    }
}
