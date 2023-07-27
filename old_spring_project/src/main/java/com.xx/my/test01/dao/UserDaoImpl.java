package com.xx.my.test01.dao;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{

    public UserDaoImpl() {
        System.out.println("UserDaoImpl的构造方法");
    }

    @Override
    public void add(String name) {
        System.out.println("UserDaoImpl的add（）");
        System.out.println("添加了：" + name);
    }
}
