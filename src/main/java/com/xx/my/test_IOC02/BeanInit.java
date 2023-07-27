//package com.xx.my.test_IOC02;
//
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//public class BeanInit {
//    public static void init(String packageName) throws ClassNotFoundException {
//        List<String> classNames = ScannerClass.parseClassName(packageName);
//        MyIOCCache instance = MyIOCCache.getInstance();
//        if(classNames!=null){
//            for(String className:classNames){
//                Class clazz = Class.forName(className);
//                Component component = (Component) clazz.getAnnotation(Component.class);
//                if(component!=null){
//                    instance.put(component.value(),clazz);
//                }
//
//            }
//
//        }
//    }
//}
