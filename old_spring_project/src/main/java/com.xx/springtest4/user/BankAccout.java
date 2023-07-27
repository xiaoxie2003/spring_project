package com.xx.springtest4.user;

import lombok.Data;

@Data   //lombok需要按照插件
public class BankAccout {
    private int id;
    private double balance; //余额
}
