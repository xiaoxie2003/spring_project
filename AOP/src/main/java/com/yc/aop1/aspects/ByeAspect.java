package com.yc.aop1.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
@Aspect
//@Order(value = 1)
public class ByeAspect implements Ordered {

    @Pointcut("execution(* com.yc.aop1.biz.*.findPid(..))")
    private void a(){}

    //环绕增强
    @Around("a()")
    public Object show(ProceedingJoinPoint jp){
        System.out.println("byeAspect的show的前面..."); //增强语句
        Object obj = null;
        try {
            obj = jp.proceed(); //主方法执行语句
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
        System.out.println("byeAspect的show 的后面***");  //增强语句
        return obj;
    }


    @Override
    public int getOrder() {
        return 1;
    }
}
