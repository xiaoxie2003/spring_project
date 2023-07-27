package com.xx.spring.test5_import;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@Import(Banana.class) //可以快速导入类（可以多个导入（数组））
//beanid:是该类的全路径名 并非类对象
public class AppConfigTest5 {

}
