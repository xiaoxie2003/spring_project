package com.xx.my.test_IOC.dao;

import com.xx.my.test_IOC.Student;

public class StudentDaoImpl implements StudentDao{

    public StudentDaoImpl() {
    }

    public StudentDaoImpl(int a) {
    }

    @Override
    public Student findById(int id) {
        return new Student(id,"程序员","北京");
    }
}
