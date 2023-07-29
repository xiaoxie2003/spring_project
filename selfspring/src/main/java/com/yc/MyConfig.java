package com.yc;

import org.ycframework.annotation.YcComponentScan;
import org.ycframework.annotation.YcConfiguration;
import org.ycframework.annotation.YcPropertiesRsouce;

@YcConfiguration
@YcComponentScan(basePackages = {"com.yc","com.yc2","com.yc3"})
@YcPropertiesRsouce(value = "db.properties")
public class MyConfig {
}
