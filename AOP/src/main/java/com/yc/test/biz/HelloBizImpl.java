package com.yc.test.biz;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class HelloBizImpl implements HelloBiz{
    public void showAll(){
        System.out.println("showAll.......");
    }

    public int addUser(String name){
        Random r = new Random();
        int a = r.nextInt(2);
        if(a == 0){
            //throw new RuntimeException("异常 没有正常返回");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("add:" + name);
        return 99999;
    }
}
