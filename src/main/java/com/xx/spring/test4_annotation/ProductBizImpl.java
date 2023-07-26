package com.xx.spring.test4_annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service  //此注解表明此类为业务层的类
public class ProductBizImpl implements ProductBiz{

    @Autowired  //由spring自动的从容器中取出 ProductDao的一个实现类的对象 注入
    private ProductDao productDao; //业务层依赖dao层 但最好依赖接口 便于切换

    public ProductBizImpl() {
        System.out.println("ProductBizImpl的构造");
    }
    @Override
    public void takein() {
        productDao.find();
        int i = 1;
        if(i == 1){
            productDao.update();
        }else {
            productDao.add();
        }


    }
}
