package com.yc.aop1;

import com.yc.aop1.biz.OrderBiz;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
        OrderBiz ob = ac.getBean(OrderBiz.class);
//        ob.makeOrder(1,99);
//        ob.findOrderId("apple");
//        ob.findOrderId("apple");
//        ob.findOrderId("pear");

//        ob.findPid("apple");
//        ob.findPid("apple");
//        ob.findPid("pear");

//        ob.makeOrder(1,999);

//        ob.findOrderId("apple");
        ob.findPid("apple");
    }
}
