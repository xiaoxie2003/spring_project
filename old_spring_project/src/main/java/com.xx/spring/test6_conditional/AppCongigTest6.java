package com.xx.spring.test6_conditional;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.xx.spring.test6_conditional")
public class AppCongigTest6 {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppCongigTest6.class);
        String []name = ac.getBeanDefinitionNames();
        for(String s:name){
            System.out.println(s);
        }
    }
}
