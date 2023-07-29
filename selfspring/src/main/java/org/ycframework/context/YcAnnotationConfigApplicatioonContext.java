package org.ycframework.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.ycframework.annotation.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class YcAnnotationConfigApplicatioonContext implements YcApplicationContext {
    private Logger logger = LoggerFactory.getLogger(YcAnnotationConfigApplicatioonContext.class);

    //存每个 待托管的Bean的定义信息
    private Map<String, YcBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    //存每个 实例化后的bean
    private Map<String, Object> beanMap = new ConcurrentHashMap<>();
    //存系统属性，db.properties
    private Properties pros;

    public YcAnnotationConfigApplicatioonContext(Class... configClasses) {
        try {
            //读取系统的属性 存好
            pros = System.getProperties();
            //存储要扫描的包的路径
            List<String> toScanPackagePath = new ArrayList<>();
            //扫描配置文件
            for (Class cls : configClasses) {
                if (cls.isAnnotationPresent(YcComponentScan.class) == false) {
                    continue;
                }
                String[] basePackages = null;
                //扫描配置类上的@YcComponentScan注解 读取要扫描的包
                if (cls.isAnnotationPresent(YcComponentScan.class)) {
                    //如果有 则说明此配置类上有@YcComponentScan 则读取basePackagePath
                    YcComponentScan ycComponentScan = (YcComponentScan) cls.getAnnotation(YcComponentScan.class);
                    //获取basePackagePath里的值
                    basePackages = ycComponentScan.basePackages();
                    if (basePackages == null || basePackages.length <= 0) {
                        basePackages = new String[1];
                        basePackages[0] = cls.getPackage().getName();
                    }
                    logger.info(cls.getName() + "类上有@YcComponentScan注解，它要扫描的路径为：" + basePackages[0]);
                }
                //开始扫描这些basepackages包下的bean，并加载包装成beandefinition对象 存到beanDefinitionMap里
                recursiveLoadBeanDefinition(basePackages);

            }
            //循环beanDefinitionMap 创建bean（是否懒加载 是否单例） 存到beanMap
            createBean();
            //判断循环所有托管的beanMap中的bean 看属性和方法上是否有@Autowired @Resource @value... 考虑DI
            diDi();
            System.out.println("成功啦");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }



    }

    /**
     * 循环beanDefinition 创建bean
     */
    private void createBean() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        for (Map.Entry<String, YcBeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanId = entry.getKey();  //键
            YcBeanDefinition ybd = entry.getValue(); //值
            if (!ybd.isLazy() && !ybd.getScope().equalsIgnoreCase("prototype")) {
                String classInfo = ybd.getClassInfo();
                //通过反射机制实例化
                Object obj = Class.forName(classInfo).newInstance(); //obj是要注入的dao的bean
                beanMap.put(beanId, obj);
                logger.trace("spring容器托管了：" + beanId + "=>" + classInfo);
            }
        }

    }

    /**
     * 判断循环所有托管的beanMap中的bean 看属性和方法上是否有@Autowired @Resource @value... 考虑DI
     */
    private void diDi() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //循环的是 beanMapp 这是托管Bean
        //集合增加了元素后，会改变计数器的值 此时线程变得不安全 会抛出异常 使用ConcurrentHashMap
        for(Map.Entry<String,Object>entry: beanMap.entrySet()){
            String beanId = entry.getKey();
            Object beanObj = entry.getValue();
            //情况一：属性上有注解
            Field[] fields = beanObj.getClass().getDeclaredFields();
            for(Field field : fields){
                if(field.isAnnotationPresent(YcResouce.class)){
                    YcResouce ycResouce = field.getAnnotation(YcResouce.class);
                    String toDiBeanId = ycResouce.name();
                    //从beanMap中找 是否单例 是否懒加载Lazy
                    Object obj = getToDiBean(toDiBeanId);
                    //注入
                    field.setAccessible(true); //因为属性是private的 所以要将它accessible设为ture
                    field.set(beanObj,obj);
                }
            }
            //情况二：方法上有注解

            //情况三：构造方法上有注解
        }
    }

    /**
     * 从beanMap中找 是否单例 是否懒加载Lazy
     * @param toDiBeanId
     * @return
     */
    private Object getToDiBean(String toDiBeanId) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        if(beanMap.containsKey(toDiBeanId)){
            return beanMap.get(toDiBeanId);
        }
        //判断beanMap中没有此bean是否是因为懒加载Lazy
        if(!beanDefinitionMap.containsKey(toDiBeanId)){
            throw new RuntimeException("spring容器中没有加载此class：" + toDiBeanId);
        }
        YcBeanDefinition bd = beanDefinitionMap.get(toDiBeanId);
        if(bd.isLazy()){
            //是因为懒 所以没有托管
            String classPath = bd.getClassInfo();
            Object beanObj = Class.forName(classPath).newInstance();
            beanMap.put(toDiBeanId,beanObj);
            return beanObj;
        }
        if(bd.getScope().equalsIgnoreCase("prototype")){
            //是因为多例 所以没有托管
            String classPath = bd.getClassInfo();
            Object beanObj = Class.forName(classPath).newInstance();
            //beanMap.put(toDiBeanId,beanObj); 原型模式下 每次getBean会创建一次bean 所以beanMap中不存
            return beanObj; //beanObj是业务层的bean
        }
        return null;
    }

    /**
     * 开始扫描这些basepackages包下的bean，并加载包装成beandefinition对象 存到beanDefinitionMap里
     *
     * @param basePackages com.yc
     */
    private void recursiveLoadBeanDefinition(String[] basePackages) {
        for (String basePackage : basePackages) {
            //将包名中的点 . 替换成路径中的 / com/yc
            String packagePath = basePackage.replaceAll("\\.", "/");
            //调用当前线程的类加载器 加载packagePath下的class文件 target/classes/com/yc
            //Enumeration是一个集合 枚举  URL是com/yc下每个资源的路径
            Enumeration<URL> files = null;
            try {
                files = Thread.currentThread().getContextClassLoader().getResources(packagePath);
                //循环files 看是否是我要加载的资源
                while (files.hasMoreElements()) {
                    URL url = files.nextElement();
                    logger.trace("当前正在递归加载的类：" + url.getFile());
                    //查找这个包下的类  前面的参数是全路径/D:/yuanchen/aThree_javaSpring/spring/spring_project/spring_project/selfspring/target/classes/com/yc 后面是只是包名com/yc
                    findPackagesClasses(url.getFile(), basePackage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 查找扫描包下的类
     *
     * @param packagePath
     * @param basePackage
     */
    private void findPackagesClasses(String packagePath, String basePackage) {
        //路径异常的处理 前面有/，则去掉它
        if (packagePath.startsWith("/")) {
            packagePath = packagePath.substring(1);
        }
        //取这个文件下字节码文件（因为目录下可能有其它的资源）
        File file = new File(packagePath);
        //只取后缀名为.class的字节码
        //写法1：接口的匿名内部类的写法
//        File[] classFile = file.listFiles(new FileFilter() {
//            @Override
//            public boolean accept(File pathname) {
//                if(pathname.getName().endsWith(".class") || pathname.isDirectory()){
//                    return true;
//                }
//                return false;
//            }
//        });
        //写法2： lambda写法
        //pathname：D:\yuanchen\aThree_javaSpring\spring\spring_project\spring_project\selfspring\target\classes\com\yc\dao
        File[] classFiles = file.listFiles((pathname) -> {
            if (pathname.getName().endsWith(".class") || pathname.isDirectory()) {
                return true;
            }
            return false;
        });
        //循环此classFiles
        if (classFiles == null || classFiles.length <= 0) {
            return;
        }
        for (File cf : classFiles) {
            if (cf.isDirectory()) {
                //继续递归
                logger.trace("递归：" + cf.getAbsolutePath() + ",它对应的包名为：" + (basePackage + "." + cf.getName()));
                findPackagesClasses(cf.getAbsolutePath(), basePackage + "." + cf.getName());
            } else {
                //是.class文件 则取出文件 判断此文件对应的class中是否有@Component注解
                URLClassLoader uc = new URLClassLoader(new URL[]{});
                //cf.getName()-->UserDaoImpl.class
                Class cls = null;// class com.yc.MyConfig
                try {
                    //TODO:可以支持YcComponent子注解
                    cls = uc.loadClass(basePackage + "." + cf.getName().replaceAll(".class", ""));
                    if (cls.isAnnotationPresent(YcComponent.class) || cls.isAnnotationPresent(YcRepository.class) ||
                            cls.isAnnotationPresent(YcService.class) || cls.isAnnotationPresent(YcController.class) ||
                            cls.isAnnotationPresent(YcConfiguration.class)
                    ) {
                        logger.info("加载到一个待托管的类：" + cls.getName());
                        //包装成BeanDefinition
                        YcBeanDefinition db = new YcBeanDefinition();
                        //懒加载
                        if (cls.isAnnotationPresent(YcLazy.class)) {
                            db.setLazy(true);
                        }
                        //单例
                        if (cls.isAnnotationPresent(YcScope.class)) {
                            YcScope ycScope = (YcScope) cls.getAnnotation(YcScope.class);
                            String scope = ycScope.value();
                            db.setScope(scope);
                        }
                        //classInfo保存这个待托管类的包路径 -> com.yc.dao.UserDaoImpl
                        db.setClassInfo(cls.getName().replaceAll(".class", ""));
                        //存到beanDefinitionMap中  “beanid”-》“beanDefinition对象”
                        String beanId = getBeanID(cls);//MyConfig
                        this.beanDefinitionMap.put(beanId, db);
                        logger.info("加入到beanDefinitionMap中的类：" + beanId);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 获取一个待托管类的BeanID
     * 1.@Compone 直接用类名 首字母小写
     * 2.@Compone（"beanID"）
     * <p>
     * 另外YcConfiguration注解用类的全路径做beanId
     *
     * @param cls
     * @return
     */
    private String getBeanID(Class cls) {

        YcComponent ycComponent = (YcComponent) cls.getAnnotation(YcComponent.class);
        YcRepository ycRepository = (YcRepository) cls.getAnnotation(YcRepository.class);
        YcService ycService = (YcService) cls.getAnnotation(YcService.class);
        YcController ycController = (YcController) cls.getAnnotation(YcController.class);

        YcConfiguration ycConfiguration = (YcConfiguration) cls.getAnnotation(YcConfiguration.class);
        if (ycConfiguration != null) {
            return cls.getSimpleName();  //全路径名 class com.yc.MyConfig
        }
        String beanId = null;
        if (ycComponent != null) {
            beanId = ycComponent.value();
        } else if (ycRepository != null) {
            beanId = ycRepository.value();
        } else if (ycService != null) {
            beanId = ycService.value();
        } else if (ycController != null) {
            beanId = ycController.value();
        }
        if (beanId == null || "".equalsIgnoreCase(beanId)) {
            String typename = cls.getSimpleName(); //UserDaoImpl
            beanId = typename.substring(0, 1).toLowerCase() + typename.substring(1); //userDaoImpl
        }

        return beanId;
    }


    @Override
    public Object getBean(String beanid) {
        YcBeanDefinition bd = this.beanDefinitionMap.get(beanid);
        if(bd == null){
            throw new RuntimeException("容器中没有加载此bean");
        }
        String scope = bd.getScope();
        if(scope.equalsIgnoreCase("prototype")){
            //原型模式 每次getBean创建
            Object obj = null;
            try {
                obj = Class.forName(bd.getClassInfo()).newInstance();
                //这种原型模式创建的bean不能保存到beanMap中
                return obj;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        if(this.beanMap.containsKey(beanid)){
            return this.beanMap.get(beanid);
        }
        if(bd.isLazy()){
            Object obj = null;
            try {
                obj = Class.forName(bd.getClassInfo()).newInstance();
                this.beanMap.put(beanid,obj);
                return obj;
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
