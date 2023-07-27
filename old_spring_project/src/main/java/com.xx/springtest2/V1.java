package com.xx.springtest2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 创建五个线程Thread输出当前的时间.
 */
public class V1 {
    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        TimeTask[] task = new TimeTask[threads.length];
        for(int i = 0; i < threads.length; i++){
            task[i] = new TimeTask();
            Thread t = new Thread(task[i]);
            t.start();
            System.out.println("线程：" + t.getName() + "启动了");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("程序结束.....");
    }


    private static class TimeTask implements Runnable{
        @Override
        public void run() {
            DateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(time.format(new Date()));
        }
    }
}
