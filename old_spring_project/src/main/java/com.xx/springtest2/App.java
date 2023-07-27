package com.xx.springtest2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;

public class App {
    public static void main(String[] args) {
        ApplicationContext container = new AnnotationConfigApplicationContext(Config.class);
        ExecutorService es = (ExecutorService) container.getBean("threadPoolExecutor");
        for(int i = 0;i<5;i++){
            es.submit(()->{
                while (true){
                    DateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    System.out.println(Thread.currentThread().getName() + "的时间为：" + time.format(new Date()));
                    Thread.sleep(1000);
                }
            });
        }
    }
}
