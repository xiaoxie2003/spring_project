package com.xx.spring.test5_import;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test5_2 {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig_2.class);
        String[] name = ac.getBeanDefinitionNames();
        for(String s:name){
            System.out.println(s);
        }
    }


}
