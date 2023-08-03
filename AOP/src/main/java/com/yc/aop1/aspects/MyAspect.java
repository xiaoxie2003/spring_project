package com.yc.aop1.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Aspect
public class MyAspect {
    //切入点表达式：正则表达式 筛选 目标类中哪些方法加增强
    //*：表示所有
    //..：表示 0或者n  所有以make开头的方法
    @Pointcut("execution(* com.yc.aop1.biz.*.make*(..))")
    private void a() {
    }

    @Before("a()") //在a()方法代表的切点方法运行之前运行
    public void recordTime() {
        Date d = new Date();
        System.out.println("========下单的时间为：" + d);
    }

    @AfterReturning("a()") //正常返回 不管有没有返回值 这个增强才起作用 在连接点方法运行完之后运行
    public void sendEmail() {
        System.out.println("调用数据库查询此下单用户的email，包装信息，发到信息中间件 kafka/mq .");
    }

    /**
     * 记录参数
     * @param jp
     */
    @AfterReturning("a()")
    public void recordParams(JoinPoint jp) {//记录连接点make*（）中的参数 make*（）就称为JointPoint
        //从jp中可以取出一些信息 make*（）方法的信息
        System.out.println("增强的方法为：" + jp.getSignature());
        System.out.println("增强的目标类为：" + jp.getTarget());
        System.out.println("参数：");
        Object[] params = jp.getArgs();
        for (Object p : params) {
            System.out.println(p);
        }
    }

    @Pointcut("execution(* com.yc.aop1.biz.*.findOrderId*(..))")
    private void b() {}

    //TODO:正常是要访问redis  商品名 次数
    private Map<String, Long> map = new ConcurrentHashMap<String, Long>();

    //统计每个商品被查询的次数
    @AfterReturning("b()")
    public void recordPnameCount(JoinPoint jp){
        Object[] objs = jp.getArgs();
        String name = (String)objs[0];
        Long num = 0L;
        if(map.containsKey(name)){
            num = map.get(name);
            num++;
        }
        map.put(name,num);
        System.out.println("统计结果：" + map);
    }

    //如何获取方法的返回值
    @Pointcut("execution(int com.yc.aop1.biz.*.findPid(String))")
    private void c(){}

    private Map<String,Long> map2 = new ConcurrentHashMap<String ,Long>();
    //统计每个商品名_编号_被查询的次数
    @AfterReturning(pointcut = "c()",returning = "retValue") //正常返回 有返回值  这个增强才起作用 在连接点方法运行完之后运行
    public void recordPname2(JoinPoint jp,int retValue){//DI方式注入
        Object[] objs = jp.getArgs();
        String name = (String)objs[0];
        Long num = 1L;
        if(map2.containsKey(name)){
            num = map2.get(name);
            num++;
        }
        map2.put(name + ":" + retValue,num);  //retValue:8   name:"apple"  num:1
        System.out.println("统计结果：" + map2);
    }

    //对异常进行处理
    @AfterThrowing(pointcut = "a()",throwing = "ex")  //有异常才执行 在原方法之后
    public void recordException(JoinPoint jp,RuntimeException ex){
        System.out.println("********异常了********");
        System.out.println(ex.getMessage());
        System.out.println(jp.getArgs()[0] + "\t" + jp.getArgs()[1]);
        System.out.println("******");
    }

    //查询方法特慢 想统计一下查询时长  查询方法都是 find*
    @Pointcut("execution(* com.yc.aop1.biz.*.find*(..))")
    private void d(){}

    @Around("d()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object retVal = pjp.proceed();  //返回值find*（）
        long end = System.currentTimeMillis();
        System.out.println("方法的执行时长为：" + (end - start));
        return retVal;
    }
}

