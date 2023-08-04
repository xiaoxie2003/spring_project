package com.yc.biz;

import com.yc.bean.Account;
import com.yc.configs.Config;
import com.yc.configs.DataSourceConfig;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class) //spring整合junit
@ContextConfiguration(classes = {Config.class, DataSourceConfig.class}) //扫描配置文件
@Log4j2//日志
public class AccountBizImplTest {

    @Autowired
    private AccountBiz accountBiz;

    @Bean
    @Test
    public void openAccount() {
        Account a = accountBiz.openAccount(100);
        Assert.assertNotNull(a);
        log.info(a);
    }

    @Test
    public void deposite() {
        Account a = accountBiz.deposite(44,100);
        Assert.assertNotNull(a);
        log.info(a);
    }

    @Test
    public void withdraw() {
        Account a = accountBiz.withdraw(44,10000);
        Assert.assertNotNull(a);
        log.info(a);
    }

    @Test
    public void transfer() {
        Account a = accountBiz.transfer(44,100,43);
        Assert.assertNotNull(a);
        log.info(a);
    }

    @Test
    public void findAccount() {
        Account a = accountBiz.findAccount(44);
        Assert.assertNotNull(a);
        log.info(a);
    }
}