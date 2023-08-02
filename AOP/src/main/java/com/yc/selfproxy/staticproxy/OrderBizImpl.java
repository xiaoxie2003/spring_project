package com.yc.selfproxy.staticproxy;

//目标类
public class OrderBizImpl implements OrderBiz{
    @Override
    public void addOrder(int pid, int num) {
        System.out.println("添加订单：" + pid + "，数量为：" + num);

    }

    @Override
    public void findOrderId() {
        System.out.println("查询订单" );
    }
}
