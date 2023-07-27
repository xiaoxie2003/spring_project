package com.xx.springtest3.system;

/***
 * 测量接口
 */
public interface Measure {

    /**
     * obj:待测量的对象
     * @param obj
     * @return 测量的值
     */
    public double doMeasure(Object obj);
}
