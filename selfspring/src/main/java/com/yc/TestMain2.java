package com.yc;

import com.yc.service.UserBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ycframework.context.YcAnnotationConfigApplicatioonContext;
import org.ycframework.context.YcApplicationContext;

public class TestMain2 {
    public static void main(String[] args) {
//        Logger logger = LoggerFactory.getLogger(TestMain2.class);
//        logger.error("error");
//        logger.warn("warn");
//        logger.info("info");
//        logger.debug("debug");
//        logger.trace("trace");

        YcApplicationContext ac = new YcAnnotationConfigApplicatioonContext(MyConfig.class);
        UserBiz ub = (UserBiz) ac.getBean("ub");
        ub.add("张三");
    }
}
