package com.xx.my.test_IOC;

import com.xx.my.test_IOC.service.StudentService;

public class Test {
    public static void main(String[] args) {
        StudentService service = new StudentService();

        System.out.println(service.findStudentById(1));
        System.out.println(service.findStudentById(1));
    }
}
