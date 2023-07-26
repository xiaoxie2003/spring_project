package com.xx.spring.test6_conditional;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(SystemCondition.class)  //Conditional:满足此类里面的条件才会加载下面的NetConnection
public class NetConnection {

}
