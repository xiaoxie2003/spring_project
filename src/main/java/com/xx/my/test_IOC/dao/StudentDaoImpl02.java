package com.xx.my.test_IOC.dao;

import com.xx.my.test_IOC.Student;

public class StudentDaoImpl02 implements StudentDao{
    @Override
    public Student findById(int id) {
        System.out.println("新方法！！！");
        return new Student(id,"程序员","上海");
    }
}
