package com.xx.spring.test5_import;

import com.xx.spring.test3_factoryBean.Pear;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.function.Predicate;

public class FruitImportSelector implements ImportSelector {
    @Override  //回调方法
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //TODO：扫描一些jar包  配置文件 -> 指定第三方的类的路径
        return new String[]{Pear.class.getName()};
    }

    @Override
    public Predicate<String> getExclusionFilter() {
        return ImportSelector.super.getExclusionFilter();
    }
}
