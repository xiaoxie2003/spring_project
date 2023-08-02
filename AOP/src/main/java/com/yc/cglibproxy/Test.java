package com.yc.cglibproxy;

import com.yc.selfproxy.jdkproxy.JdkProxyTool;
import com.yc.selfproxy.staticproxy.OrderBiz;

public class Test {
    public static void main(String[] args) {
        //设置代理生成的字节码 保存到当前目录
        CglibProxyTool jpt = new CglibProxyTool(new OrderBizImpl());
        OrderBizImpl ob = (OrderBizImpl) jpt.createProxy();
        System.out.println("生成了代理类对象：" + ob.toString()); //com.yc.cglibproxy.OrderBizImpl@46d56d67

        ob.findOrderId();
        ob.addOrder(1,99);
    }
}
