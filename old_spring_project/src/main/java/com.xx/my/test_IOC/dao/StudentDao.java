package com.xx.my.test_IOC.dao;

import com.xx.my.test_IOC.Student;

public interface StudentDao {
    /**
     * 根据id查询学生
     * @param id
     * @return
     */
    Student findById(int id);
}
