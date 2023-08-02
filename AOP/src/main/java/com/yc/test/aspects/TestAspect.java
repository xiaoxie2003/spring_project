package com.yc.test.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 测试增强
 */
@Component
@Aspect
@Order(10) //高优先级进来的时候先运行 出去的时候后运行
public class TestAspect {
    @Pointcut("execution(* com.yc.test.biz..add*(..))")
    private void abc(){}

//    @AfterReturning("abc()")  //正常返回 不管有没有返回值 这个增强才起作用 在连接点方法运行完之后运行
//    public void show(){
//        System.out.println("returning");
//    }

//    @AfterReturning(pointcut = "abc()",returning = "retVal" )  //正常返回 有返回值  这个增强才起作用 在连接点方法运行完之后运行
//    public void show(Object retVal){
//        System.out.println("returning,被增强的返回的结果为：" + retVal);
//    }

//    @AfterThrowing(value = "abc()",throwing = "ex")  //有异常才起作用
//    public void show(RuntimeException ex){
//        System.out.println("AfterThrowing,被增强的方法有异常抛出" + ex);
//    }

//    @After("abc()")  //只要方法执行完即可，不管正常还是异常
//    public void show(){
//        System.out.println("After.....");
//    }

    @Around("abc()")  //环绕增强
    public Object show(ProceedingJoinPoint pjp) throws Throwable {  //ProceedingJoinPoint代表连接点 一定要有
        System.out.println("在原方法前面的代码...");
        Object obj = pjp.proceed();
        System.out.println("在原方法后面的代码....");
        return obj;
    }

}
