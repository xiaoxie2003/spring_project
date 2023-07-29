package com.yc.service;

import com.yc.dao.UserDao;
import org.ycframework.annotation.YcLazy;
import org.ycframework.annotation.YcResouce;
import org.ycframework.annotation.YcService;


@YcService("ub")
//@YcLazy
public class UserBizImpl implements UserBiz{

    @YcResouce(name = "userDaoImpl")
    private UserDao userDao;
    @Override
    public void add(String name) {
        userDao.add(name);
    }
}
