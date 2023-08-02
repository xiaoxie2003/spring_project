package com.yc.test.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 统计一个方法执行的时长
 */
@Component
@Aspect
@Order(100)
public class RateAspect {
    @Pointcut("execution(* com.yc.test.biz..add*(..))")
    private void abc(){}

    @Around("abc()")  //环绕增强
    public Object show(ProceedingJoinPoint pjp) throws Throwable {  //ProceedingJoinPoint代表连接点 一定要有
        System.out.println("RateAspect进来了..."); //原方法之前执行

        long startTime = System.currentTimeMillis();
        Object obj = pjp.proceed();  //连接点->原方法所在
        long endTime = System.currentTimeMillis();

        System.out.println("方法运行了：" + (endTime - startTime) ); //原方法之后执行
        return obj;
    }
}
