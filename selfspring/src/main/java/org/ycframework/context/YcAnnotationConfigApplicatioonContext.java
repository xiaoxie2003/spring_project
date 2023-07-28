package org.ycframework.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.ycframework.annotation.YcComponentScan;

import java.util.*;

public class YcAnnotationConfigApplicatioonContext implements YcApplicationContext{
    private Logger logger = LoggerFactory.getLogger(YcAnnotationConfigApplicatioonContext.class);

    //存每个 待托管的Bean的定义信息
    private Map<String, YcBeanDefinition> beanDefinitionMap = new HashMap<>();
    //存每个 实例化后的bean
    private Map<String,Object> beanMap = new HashMap<>();
    //存系统属性，db.properties
    private Properties pros;

    public YcAnnotationConfigApplicatioonContext(Class...configClasses){
        //读取系统的属性 存好
        pros = System.getProperties();
        //存储要扫描的包的路径
        List<String> toScanPackagePath = new ArrayList<>();
        //扫描配置文件
        for(Class cls:configClasses){
            if(cls.isAnnotationPresent(YcComponentScan.class) == false){
                continue;
            }
            //扫描配置类上的@YcComponentScan注解 读取要扫描的包
            if(cls.isAnnotationPresent(YcComponentScan.class)){
                //如果有 则说明此配置类上有@YcComponentScan 则读取basePackagePath
                YcComponentScan ycComponentScan = (YcComponentScan) cls.getAnnotation(YcComponentScan.class);
                //获取basePackagePath里的值
                String[] basePackages = ycComponentScan.basePackages();
                if(basePackages == null || basePackages.length<=0){
                    basePackages = new String[1];
                    basePackages[0] = cls.getPackage().getName();
                }
                logger.info(cls.getName() + "类上有@YcComponentScan注解，它要扫描的路径为：" + basePackages[0]);
            }
            //将这些包中带有IOC注解的类 加载到一个map中
        }


    }

    @Override
    public Object getBean(String beanid) {

        return null;
    }
}
