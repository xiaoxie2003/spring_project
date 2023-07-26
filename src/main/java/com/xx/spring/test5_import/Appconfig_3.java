package com.xx.spring.test5_import;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration   //只有pear托管了 才会托管Grape
@Import({Banana.class, FruitImportSelector.class, FruitNameImportBeanDefinitionRegistar.class})
public class Appconfig_3 {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(Appconfig_3.class);
        String[] name = ac.getBeanDefinitionNames();
        for(String s:name){
            System.out.println(s);
        }
    }
}
