package com.xx.spring.test2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.concurrent.*;

/**
 * 相当于一个beans.xml文件
 */
@Configuration  //此注解表示这个类是一个配置类 相当于beans.xml文件
public class AppConfig {

    @Bean  //<bean id="a" class="com.xx.spring.test2.Apple">
    public Apple a(){
        Apple apple =  new Apple();
        apple.setId(100);
        return apple;
    }
    //apple对象以a做键名 存到ApplicationContext容器里

    @Bean
    public ThreadPoolExecutor tpe(){
        int corePllSize = Runtime.getRuntime().availableProcessors();
        //核心线程最大的线程数
        int maxPoolSize = corePllSize * 2;
        //线程最大空闲时间
        long keepAliveTime = 10;
        //时间单位
        TimeUnit unit = TimeUnit.SECONDS;  //enum枚举 常量
        //阻塞队列 容量为2  最多允许两个空闲任务
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(maxPoolSize * 4);
        //这里创建线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePllSize,maxPoolSize,keepAliveTime,unit,workQueue);
        //预启动所有核心线程 提升效率
        executor.prestartAllCoreThreads();
        System.out.println("线程池启动，参数：" + executor.toString());
        return executor;
    }
}
