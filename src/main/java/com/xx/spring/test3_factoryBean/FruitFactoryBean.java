package com.xx.spring.test3_factoryBean;

import org.springframework.beans.factory.FactoryBean;

/**
 * 水果工厂 spring托管
 */
public class FruitFactoryBean implements FactoryBean {
    public FruitFactoryBean() {
        System.out.println("FruitFactoryBean构造");
    }

    @Override
    public Pear getObject() throws Exception {
        //加入逻辑
        return new Pear();
    }

    @Override
    public Class<?> getObjectType() {
        return Pear.class;
    }

}
