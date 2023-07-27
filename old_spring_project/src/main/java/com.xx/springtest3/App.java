package com.xx.springtest3;

import com.xx.springtest3.system.Container;
import com.xx.springtest3.user.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
        //创建容器对象
        Container c = (Container) ac.getBean("container");

        c.add(new Student("张三",1.8,80.2));
        c.add(new Student("李四",1.7,70.2));
        c.add(new Student("王五",1.6,90.2));
        c.add(new Student("异常",0.1,1));

        System.out.println(c.getMax());
        System.out.println(c.getMin());
        System.out.println(c.getAvg());
        System.out.println(c.size());
    }
}
