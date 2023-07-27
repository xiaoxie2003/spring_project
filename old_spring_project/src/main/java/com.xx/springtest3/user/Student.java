package com.xx.springtest3.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //get set tostring方法等
@AllArgsConstructor //带所有参数的构造函数
@NoArgsConstructor //无参构造函数
public class Student {
    private String name;
    private double height;
    private double weight;

}
