package com.yc;

import org.ycframework.annotation.YcResouce;
import org.ycframework.annotation.YcService;


@YcService
public class UserBizImpl implements UserBiz{

    @YcResouce(name = "userDaoImpl")
    private UserDao userDao;
    @Override
    public void add(String name) {
        userDao.add(name);
    }
}
