package com.xx.my.test01;

import com.xx.my.test01.biz.UserBizImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
        UserBizImpl ubi = (UserBizImpl) ac.getBean("userBizImpl");
        ubi.addUser("张三");

    }
}
