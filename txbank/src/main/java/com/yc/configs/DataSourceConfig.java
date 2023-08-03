package com.yc.configs;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

//数据源的配置类
@Configuration
@PropertySource("classpath:db.properties")
@Data //lombok创建 get/set
@Log4j2
public class DataSourceConfig {
    //利用DI将db.properties中的内容注入
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.driverclass}")
    private String driverclass;
    //以上属性从配置文件读取出来后，都存到了spring容器的Environment的变量（系统属性）

    //value="#{ T(java.lang.Math).random() * 100.0 }"
    @Value("#{ T(Runtime).getRuntime().availableProcessors()*2}") //value里面接表达式 进行运算
    private int cpCount;

    //参数：第三方的框架中的类 用@Bean托管
    @Bean(initMethod = "init",destroyMethod = "close")
    public DruidDataSource druidDataSource(){ //DruidDataSource提供了连接池机制 所以才有init和close的方法
        DruidDataSource dds = new DruidDataSource();
        dds.setUrl(url);
        dds.setUsername(username);
        dds.setPassword(password);
        dds.setDriverClassName(driverclass);
        //以上只是配置了参数 并没有创建连接池 在这个类的init（）中完成了连接池的创建
        //当前主机的CPU数*2
        //int cpuCount = Runtime.getRuntime().availableProcessors()*2
        log.info("配置druid的连接池的大小：" + cpCount);
        dds.setInitialSize(cpCount);
        dds.setMaxActive(cpCount*2);
        return dds;
    }

    @Bean //IOC注解 托管第三方Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverclass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean //IOC注解 托管第三方Bean
    public DataSource dbcpDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverclass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

}
