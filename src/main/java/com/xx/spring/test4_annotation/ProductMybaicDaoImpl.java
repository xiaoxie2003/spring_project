package com.xx.spring.test4_annotation;

import org.springframework.stereotype.Repository;

@Repository  //这个注解表名当前的类要被spring托管 是一个DAO层的类
            //spring会将这里的异常转为RuntimeException
public class ProductMybaicDaoImpl implements ProductDao{
    public ProductMybaicDaoImpl() {
        System.out.println("ProductMybaicDaoImpl的构造");
    }

    @Override
    public void add() {
        System.out.println("ProductMybaicDaoImpl的add（）");
    }

    @Override
    public void find() {
        System.out.println("ProductMybaicDaoImpl的find（）");

    }

    @Override
    public void update() {
        System.out.println("ProductMybaicDaoImpl的update（）");

    }
}
