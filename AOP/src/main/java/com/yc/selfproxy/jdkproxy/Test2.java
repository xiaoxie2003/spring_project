package com.yc.selfproxy.jdkproxy;

import com.yc.selfproxy.staticproxy.OrderBiz;
import com.yc.selfproxy.staticproxy.OrderBizImpl;

public class Test2 {
    public static void main(String[] args) {
        //设置代理生成的字节码 保存到当前目录
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        JdkProxyTool jpt = new JdkProxyTool(new OrderBizImpl());
        OrderBiz ob = (OrderBiz) jpt.createProxy(); //$Proxy0
        //System.out.println("生成了代理类对象：" + ob); //com.yc.selfproxy.staticproxy.OrderBizImpl@3419866c

        ob.findOrderId(); //$Proxy.findOrderId()
        ob.addOrder(1,99);

    }
}
