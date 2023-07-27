package com.xx.my.test_IOC02;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 扫描包下类的工具类
 */
public abstract class ScannerClass {
    /**
     *
     * @param packageName 要扫描包的完整路径 要扫描包的名称
     * @return 该包下面的使用类的全名称
     */
    public static List<String> parseClassName(String packageName){
        List<String> array = new ArrayList<>();
        String packagePath = resovlePackagePath(packageName);
        File root = new File(packagePath);
        resolveFile(root,packageName,array);
        return array;
    }

    /**
     *
     * @param packageName 包名
     * @return 该包的全路径
     */
    private static String resovlePackagePath(String packageName) {
        //扫描所有的包并把其放入到访问关系和方法放入到内存中
        File f = new File(ScannerClass.class.getResource("/").getPath());
        String totalPath = f.getAbsolutePath();

        String pageName = ScannerClass.class.getPackage().getName().replace(".", "\\");
        totalPath = totalPath.replace(pageName, "");
        String packagePath = packageName.replace(".", "\\");
        totalPath = totalPath + "\\" + packagePath;
        return totalPath;
    }

    /**
     * 扫描文件
     * @param root
     * @param packageName
     * @param classNames
     */
    private static void resolveFile(File root,String packageName,List<String>classNames){
        if(!root.exists()){
            return;
        }
        File[] childs = root.listFiles();
        if(childs != null && childs.length>0){
            for(File child:childs){
                if(child.isDirectory()){
                    String parentPath = child.getParent();
                    String childPath = child.getAbsolutePath();
                    String temp = childPath.replace(parentPath,"");
                    temp = temp.replace("\\","");
                    resolveFile(child,packageName+"."+temp,classNames);
                }else {
                    String fileName = child.getName();
                    if(fileName.endsWith(".class")){
                        String name = fileName.replace(".class","");
                        String className = packageName + "." + name;
                        classNames.add(className);
                    }
                }
            }
        }
    }
}

