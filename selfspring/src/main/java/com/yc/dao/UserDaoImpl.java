package com.yc.dao;

import org.ycframework.annotation.YcRepository;

@YcRepository  //"userDaoImpl"
//@YcLazy
//@YcScope(value = "prototype")
public class UserDaoImpl implements UserDao{
    @Override
    public void add(String uname){
        System.out.println("dao添加了： " + uname);
    }
}
