package com.xx.springtest2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 升级: 用线程池 ThreadPoolExecutor 完成这个工作.
 * corePoolSize：核心线程数
 * maximumPoolSize：最大线程数
 * ArrayBlockingQueue：阻塞队列
 * keepAliveTime：新创建的线程什么时候销毁
 * new ThreadPoolExecutor.DiscardOldestPolicy：销毁原则
 *
 * 任务-->corePoolSize 不够-->ArrayBlockingQueue 满了-->maximumPoolSize 满了-->ThreadPoolExecutor.DiscardOldestPolicy销毁
 */
public class V2 {
    public static void main(String[] args) {
        //创建线程池
        ExecutorService executorService = new ThreadPoolExecutor(3, 5, 0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100), new ThreadPoolExecutor.DiscardOldestPolicy());

        for(int i = 0;i<5;i++){
            //线程池的用法
            executorService.submit(()->{
                //相当于任务类
                while (true){
                    DateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    System.out.println(Thread.currentThread().getName() + "的时间为：" + time.format(new Date()));
                    Thread.sleep(1000);
                }
            });
        }
    }
}
