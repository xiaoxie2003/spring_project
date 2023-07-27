package com.xx.springtest3.user;

import com.xx.springtest3.system.ContainerFiter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * 学生Bmi指数筛选实现类
 */
@Component("bmiContainerFilter")  //命名
public class ContainerFilterImpl implements ContainerFiter {
    @Override
    public boolean doFilter(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Student)) {
            return false;
        }
        Student s = (Student) obj;
        if (s.getName() == null || "".equalsIgnoreCase(s.getName())) {
            return false;
        }
        if (s.getHeight() < 1 || s.getHeight() > 2.7) {
            return false;
        }
        if (s.getWeight() < 30 || s.getWeight() > 500) {
            return false;
        }
        return true;
    }
}
