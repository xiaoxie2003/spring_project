package com.xx.spring.test4_annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//ComponentScan用来指定spring容器扫描类的路径
@ComponentScan(basePackages = "com.xx.spring.test4_annotation")
public class AppConfigTest4 {

    //@Bean 原来的通过创建Bean 交给spring托管

}
