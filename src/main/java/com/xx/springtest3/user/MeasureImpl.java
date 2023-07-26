package com.xx.springtest3.user;

import com.xx.springtest3.system.Measure;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * 学生的bmi指数测量的实现类
 */
@Component("bmiMeasure")
public class MeasureImpl implements Measure {
    @Override
    public double doMeasure(Object obj) {
        //算法由用户决定
        if(obj == null){
            return 0;
        }
        if(! (obj instanceof Student)){
            throw new RuntimeException("待测数据异常");
        }
        Student s = (Student) obj;

        return s.getWeight()/(s.getHeight()*s.getHeight());
    }
}
