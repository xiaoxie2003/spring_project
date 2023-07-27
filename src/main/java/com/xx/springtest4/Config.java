package com.xx.springtest4;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
//@PropertySource 加载指定的属性文件（*.properties）到 Spring 的 Environment 中。
@PropertySource(value = "classpath:db.properties")  //spring启动时 PropertySource扫描db.properties
public class Config {
}
