package com.xx.my.test01.biz;

import com.xx.my.test01.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBizImpl implements UserBiz{

    @Autowired
    private UserDao userDao;

    public UserBizImpl() {
        System.out.println("UserBizImpl的构造方法");
    }

    @Override
    public void addUser(String name) {
        System.out.println("UserBizImpl的addUser");
        userDao.add(name);
    }
}
