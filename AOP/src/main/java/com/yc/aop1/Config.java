package com.yc.aop1;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.yc.aop1")
@EnableAspectJAutoProxy  //表示启用 AspectJ支持
public class Config {
}
