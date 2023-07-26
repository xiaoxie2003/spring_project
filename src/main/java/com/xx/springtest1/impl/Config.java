package com.xx.springtest1.impl;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration //此类是一个配置类（也被spring托管） 用来声明容器运行时的一些配置信息（1.扫描的路径，这个路径下所有带有@Component，@Repository，@Service，@Controller）
//spring托管
@ComponentScan(basePackages = {"com.xx.springtest1"})
public class Config {

}
