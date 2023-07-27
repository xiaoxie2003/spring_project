//package com.xx.my.test_IOC02;
//
//import com.xx.my.test_IOC02.Autower;
//import com.xx.my.test_IOC02.BeanDefinition;
//
//import java.lang.reflect.Field;
//import java.util.HashMap;
//import java.util.Map;
//
//public class MyIOCCache {
//
//    private Map<String,BeanDefinition> defineMap = new HashMap<>();
//    private Map<String,Object> beanMap = new HashMap<>();
//
//    private MyIOCCache(){
//
//    }
//
//    public void put(String name,Class clazz){
//        if(name==null||name.equals("")){
//            name = clazz.getInterfaces()[0].getSimpleName();
//        }
//        if(name==null)
//            name = clazz.getSimpleName();
//        if(name.startsWith("I")){
//            name = name.substring(1);
//        }
//        char temp = name.charAt(0);
//        if(temp>='A'&&temp<='Z'){
//            name = name.substring(0,1).toLowerCase()+name.substring(1);
//        }
//
//        BeanDefinition beanDefinition = new BeanDefinition();
//        beanDefinition.setBeanClass(clazz);
//        beanDefinition.setFields(clazz.getDeclaredFields());
//        defineMap.put(name,beanDefinition);
//    }
//
//    public <T> T getBean(Class clazz){
//        return getBean("",clazz);
//    }
//
//    public <T> T getBean(String name,Class clazz){
//        try {
//            if (name == null || name.equals(""))
//                name = clazz.getSimpleName();
//            Object bean = null;
//            bean = beanMap.get(name);
//            if (bean != null)
//                return (T) bean;
//            bean = resolveBean(name);
//            beanMap.put(name,bean);
//            return (T) bean;
//        }catch (Exception e){
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    private Object resolveBean(String name)throws Exception{
//        Object bean =  beanMap.get(name);
//        if(bean==null){
//            BeanDefinition beanDefinition =  defineMap.get(name);
//            Class beanClass = beanDefinition.getBeanClass();
//            Field[] fields = beanDefinition.getFields();
//            bean = beanClass.newInstance();
//            if(fields!=null){
//                for(Field field:fields){
//                    Autower autower = field.getAnnotation(Autower.class);
//                    if(autower==null)
//                        continue;;
//                    String autowerName = autower.value();
//                    if(autowerName==null||autowerName.equals(""))
//                        autowerName = field.getName();
//                    System.out.println(autowerName);
//                    Object attrBean = resolveBean(autowerName);
//                    field.setAccessible(true);
//                    field.set(bean,attrBean);
//                }
//            }
//            beanMap.put(name,bean);
//        }
//
//        return bean;
//    }
//
//    public static MyIOCCache getInstance(){
//        return Inner.instance;
//    }
//
//    private static class Inner{
//        private static MyIOCCache instance = new MyIOCCache();
//    }
//}
