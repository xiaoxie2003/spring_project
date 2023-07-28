package com.yc;

import org.ycframework.annotation.YcComponentScan;
import org.ycframework.annotation.YcConfiguration;
import org.ycframework.annotation.YcPropertiesRsouce;

@YcConfiguration
@YcComponentScan
@YcPropertiesRsouce(value = "db.properties")
public class MyConfig {
}
