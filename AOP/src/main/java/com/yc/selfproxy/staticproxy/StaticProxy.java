package com.yc.selfproxy.staticproxy;

/**
 * 静态代理类 面向接口开发
 */
public class StaticProxy implements OrderBiz{

    //目标类的引用,利用setXxx 或构造方法注入
    private OrderBiz orderBiz;
    public void setOrderBiz(OrderBiz orderBiz) {
        this.orderBiz = orderBiz;
    }

    public StaticProxy(OrderBiz orderBiz) {
        this.orderBiz = orderBiz;
    }

    @Override
    public void addOrder(int pid, int num) {
        //前置增强 增加功能
        showHolle();
        this.orderBiz.addOrder(pid,num);
        //后置增强
        showBye();
    }

    @Override
    public void findOrderId() {
        System.out.println("查询");
    }

    //增强
    public void showHolle(){
        System.out.println("hello");
    }
    //增强
    public void showBye(){
        System.out.println("Bye");
    }
}
