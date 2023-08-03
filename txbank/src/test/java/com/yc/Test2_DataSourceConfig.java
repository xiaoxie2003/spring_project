package com.yc;

import com.alibaba.druid.pool.DruidDataSource;
import com.yc.configs.Config;
import com.yc.configs.DataSourceConfig;
import junit.framework.TestCase;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class, DataSourceConfig.class})
@Log4j2
public class Test2_DataSourceConfig extends TestCase {

    @Autowired
    private DataSourceConfig dsc;

    @Autowired
    private Environment env; //获取环境变量包括系统环境变量

    @Autowired
    @Qualifier("dataSource")
    private DataSource ds;

    @Autowired
    @Qualifier("dbcpDataSource")
    private DataSource dbcpDataSource;

    @Autowired
    @Qualifier("druidDataSource")
    private DataSource druidDataSource;

    @Autowired
    private TransactionManager tx;

    @Test
    public void testTransactionManager(){
        log.info(tx);
    }

    @Test
    public void testDruidDataSource() throws SQLException {
        Assert.assertNotNull(druidDataSource.getConnection());
        Connection conn = druidDataSource.getConnection();
        log.info(conn + "\t" + ((DruidDataSource)druidDataSource).getInitialSize());
    }

    @Test
    public void testDsConnection() throws SQLException {
        Assert.assertNotNull(ds.getConnection());
        Connection conn = ds.getConnection();
        log.info(conn);
    }

    @Test
    public void testDbcpConnection() throws SQLException {
        Assert.assertNotNull(dbcpDataSource.getConnection());
        Connection conn = dbcpDataSource.getConnection();
        log.info(conn);
    }

    @Test
    public void testProperties(){
        Assert.assertEquals("root",dsc.getUsername());
        log.info(dsc.getUsername());
    }

    @Test
    public void testEnvironment(){
        log.info(env.getProperty("jdbc.password") +"\n" +  env.getProperty("user.dir"));
    }

}
