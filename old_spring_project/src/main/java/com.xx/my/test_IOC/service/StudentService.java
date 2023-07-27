package com.xx.my.test_IOC.service;

import com.xx.my.test_IOC.Container;
import com.xx.my.test_IOC.Student;
import com.xx.my.test_IOC.dao.StudentDao;

public class StudentService {
    public Student findStudentById(int id){
        //从容器取对象
        StudentDao dao = (StudentDao) Container.getBean("studentDao");
        System.out.println(dao.hashCode());
        return dao.findById(id);
    }
}
