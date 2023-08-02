package com.yc.test;

import com.yc.test.biz.HelloBiz;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class test {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //要使用接口对象定义获取到的实现对象
        HelloBiz biz = (HelloBiz) ac.getBean("helloBizImpl");

        //System.out.println(biz);
        //biz.showAll();
        biz.addUser("xx");
    }
}
