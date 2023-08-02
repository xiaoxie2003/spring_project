package com.yc.cglibproxy;

import com.yc.selfproxy.jdkproxy.JdkProxyTool;

import java.lang.reflect.Proxy;

public class OrderBizImpl {

    public void addOrder(int pid, int num) {
//        Object proxy = Proxy.newProxyInstance(JdkProxyTool.class.getClassLoader(),
//                target.getClass().getInterfaces(),
//                this);
        System.out.println("添加订单：" + pid + "，数量为：" + num);

    }

    public void findOrderId() {
        System.out.println("查询订单" );
    }
}
