package com.xx.spring.test5_import;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class test5 {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigTest5.class);
        String [] names = ac.getBeanDefinitionNames();
        for(String n:names){
            System.out.println(n);
        }

        Banana b = (Banana) ac.getBean("com.xx.spring.test5_import.Banana");
        System.out.println(b);

        //通过反射取 也是单例的
        Banana b2 = ac.getBean(Banana.class);
        System.out.println(b2);


    }
}
