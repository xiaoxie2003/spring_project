package com.xx.spring.test3_factoryBean;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  //配置类
public class AppConfigTest3 {
    @Bean  //FruitFactoryBean被spring托管
    //ffb方法名对应的是水果工厂创建的对象-->pear
    public FruitFactoryBean ffb(){
        return new FruitFactoryBean();
    }
}
