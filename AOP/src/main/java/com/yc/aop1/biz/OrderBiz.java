package com.yc.aop1.biz;

public interface OrderBiz {
    public void makeOrder(int pid,int num);

    public int findOrderId(String name);

    public int findPid(String name);

}
