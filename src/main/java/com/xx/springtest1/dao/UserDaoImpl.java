package com.xx.springtest1.dao;

import com.xx.springtest1.dao.UserDao;
import org.springframework.stereotype.Repository;


@Repository //@Repository标识这是一个dao层的类 由spring管理
public class UserDaoImpl implements UserDao {

    @Override
    public void add(String uname) {
        System.out.println("添加了：" + uname);
    }
}
