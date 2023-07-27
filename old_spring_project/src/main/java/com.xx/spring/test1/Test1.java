package com.xx.spring.test1;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试什么是ioc
 */
public class Test1 {
    public static void main(String[] args) {
        //1.早期的程序
//        Student s = new Student();
//        s.setId(1);
//        s.setName("张三");
//        System.out.println(s.toString());
        //小结：这叫控制不反转 程序来创建对象 test1依赖于 Student的

        //2.引入spring的ioc机制
        //IOC：控制反转 由spring容器创建对象
        //ClassPathXmlApplicationContext: 类路径下的xml文件
        //1.ApplicationContext
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        //2.ApplicationContext context1 = new FileSystemXmlApplicationContext("classpath:beans.xml");
        //上面这句代码的意思是：读取class路径下beans.xml文件 并获取bean配置的class，实例化对象存到容器中

        //3.ApplicationContext
        //Resource res = new ClassPathResource("beans.xml");
        //BeanFactory context = new XmlBeanFactory(res);

        Student ss = (Student) context.getBean("s");
        System.out.println(ss);

        //容器的好处：容器来管理bean 比如单例：
//        Student s1 = (Student) context.getBean("s");
//        Student s2 = (Student) context.getBean("s");
//        System.out.println(s1.hashCode() + "\t" + s2.hashCode());

        //TODO:让属性有值 -> DI -> Dependency injection 依赖注入
    }
}