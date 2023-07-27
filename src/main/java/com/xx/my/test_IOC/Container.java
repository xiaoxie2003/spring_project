package com.xx.my.test_IOC;

import jdk.internal.util.xml.impl.Input;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 容器管理类
 */
public class Container {
    static Map<String,Object> map = new HashMap<>();

    static {
        //读取配置文件
        InputStream is = Container.class.getClassLoader().getResourceAsStream("bean.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //遍历配置文件的所有配置
        Enumeration<Object> keys = properties.keys();
        while (keys.hasMoreElements()){
            String key = keys.nextElement().toString();
            String value = properties.getProperty(key);
            try {
                Object o = Class.forName(value).newInstance();
                map.put(key,o);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 从容器中获取对象
     * @param key
     * @return
     */
    public static Object getBean(String key){
        return map.get(key);
    }
}
