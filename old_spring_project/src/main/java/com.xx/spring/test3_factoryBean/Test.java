package com.xx.spring.test3_factoryBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试FactoryBean的使用
 */
public class Test {
    public static void main(String[] args) {
        //1.创建容器
        //容器启动：FruitFactoryBean   pear
        //在调用对象之前 容器只托管工厂
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigTest3.class);

        Object object = ac.getBean("ffb");  //beanid：ffb-->工厂创建的对象（pear）
        System.out.println(object);

        Object obj2 = ac.getBean("&ffb"); //beanid：&ffb-->工厂
        System.out.println(obj2);

        //2.pear默认是单例还是多例  --> 单例
        Object object3 = ac.getBean("ffb");  //beanid：ffb-->工厂创建的对象（pear）
        System.out.println(object);

        System.out.println(object.hashCode() + "\t" + object3.hashCode());

        //3.获取spring容器中所有托管的bean
        String[] beanNames = ac.getBeanDefinitionNames();
        for(String bn:beanNames){
            System.out.println(bn);
        }
    }
}
