package com.xx.spring.test2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ThreadPoolExecutor;

public class TestMain {
    public static void main(String[] args) {
        //基于注解的配置文件
        //IOC 和 DI 完成
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        Apple apple = (Apple) ac.getBean("a");
        System.out.println(apple);

        ThreadPoolExecutor tpe = (ThreadPoolExecutor) ac.getBean("tpe");
        tpe.submit(()->{
           System.out.println("hello");
        });
    }
}
