package com.xx.springtest3.system;

import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 容器：1.能存大量的对象
 */
//要用spring完成托管
@Component
public class Container<T> {
    private List<T> objs = new ArrayList<T>();

    //TODO:要用spring完成DI
    /*
    @Autowired
    @Qualifier(value = "bmiMeasure")
    */

    @Resource(name = "bmiMeasure") //按名字注入
    private Measure measure;
    @Resource(name = "bmiContainerFilter")
    private ContainerFiter containerFiter;

    private T max;
    private T min;
    private double avg;
    private double sum;

    /**
     * 添加对象的方法
     *
     * @param t
     */
    public void add(T t) {
        //判断t是否合格   调用筛选的实现
        if(containerFiter != null){
            //表示过滤失败 是无效数据
            if(containerFiter.doFilter(t) == false){
                return;
            }
        }
        //添加到objs集合里
        objs.add(t);
        //判断大小 记录max min 计算avg  => 调用measure的实现类
        if(objs.size() == 1){
            max = t;
            min = t;
        }else {
            //测出值判断大小
            double val = this.measure.doMeasure(t);
            double maxval = this.measure.doMeasure(max);
            double minval = this.measure.doMeasure(min);
            if( val > maxval){
                max = t;
            }
            if( val < minval){
                min = t;
            }
        }
        sum += measure.doMeasure(t);
        avg = sum/objs.size();
    }

    /**
     * 有效的测量 统计对象有多少个
     *
     * @return
     */
    public int size() {
        return objs.size();
    }

    /**
     * 清空
     */
    public void clearAll() {
        objs = new ArrayList<T>();
        measure = null;
        containerFiter = null;
        max = null;
        min = null;
        avg = 0;
    }

    public List<T> getObjs() {
        return objs;
    }

    public void setObjs(List<T> objs) {
        this.objs = objs;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }

    public ContainerFiter getContainerFiter() {
        return containerFiter;
    }

    public void setContainerFiter(ContainerFiter containerFiter) {
        this.containerFiter = containerFiter;
    }

    public T getMax() {
        return max;
    }

    public void setMax(T max) {
        this.max = max;
    }

    public T getMin() {
        return min;
    }

    public void setMin(T min) {
        this.min = min;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }
}
