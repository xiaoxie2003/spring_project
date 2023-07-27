package com.xx.springtest4.datasource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.PrintWriter;

import java.sql.*;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

@Component //托管
public class MyDataSource implements DataSource {
    //连接池
    private ConcurrentLinkedQueue<MyConnection> pool;

    //配置后注入属性 结合属性完成注入操作
    //3.从db.properties属性文件中取
    @Value("${coreSize}")
    private int coreSize;
    @Value("${user}")
    private String user;
    @Value("${password}")
    private String password;
    @Value("${url}")
    private String url;

    public MyDataSource() {
        System.out.println("MyDataSource的构造方法");
    }

    @PostConstruct //此方法在构造方法之后执行 即初始化连接池
    public void init() throws SQLException {
        System.out.println("MyDataSource的init()");
        //连接池用同步队列 每次取队头 避免冲突
        pool = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < coreSize; i++) {
            //创建连接对象
            MyConnection mc = new MyConnection();
            //设置此连接的状态为空闲
            mc.statue = false;
            //获取连接
            mc.con = DriverManager.getConnection(url, user, password);
            //将此连接加入连接池
            pool.add(mc);
        }
    }

    //连接对象
    class MyConnection {
        Connection con;
        boolean statue;  //true再用 false空闲
    }

    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    @Override
    public Connection getConnection() throws SQLException {
        do{
            //从连接池取队头的连接对象
            MyConnection mc = this.pool.poll();
            if(mc == null){
                return null;
            }
            //如果此连接空闲就返回此连接
            if( !mc.statue){
                return mc.con;
            }
        }while (true);
    }

    /**
     * 返回连接 用完的连接不关闭 返回到连接池中
     * @param con
     */
    public void returnConnection(Connection con){
        MyConnection mc = new MyConnection();
        mc.statue = false;
        mc.con = con;
        this.pool.add(mc);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

}
