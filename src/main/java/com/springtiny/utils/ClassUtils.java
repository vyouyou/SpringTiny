package com.springtiny.utils;

import lombok.extern.slf4j.Slf4j;

import javax.print.DocFlavor;
import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@Slf4j
public class ClassUtils {
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }


    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className,isInitialized,getClassLoader());
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage());
            throw new RuntimeException();
        }
        return clazz;
    }


    public static Set<Class<?>> getClassSet(String packageName) {
        HashSet<Class<?>> classSet = new HashSet<>();
        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".","/"));
            while (urls.hasMoreElements()){
                URL url = urls.nextElement();
                if(url==null)continue;
                String protocol = url.getProtocol();
                if(protocol.equals("file")){
                    String packagePath = url.getPath().replaceAll("%20"," ");
                    addClass(classSet,packagePath,packageName);
                }else if(protocol.equals("jar")){
                    JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                    if (jarURLConnection==null)continue;
                    JarFile jarFile = jarURLConnection.getJarFile();
                    if (jarFile==null)continue;
                    Enumeration<JarEntry> jarEntities = jarFile.entries();
                    while (jarEntities.hasMoreElements()){
                        JarEntry jarEntry = jarEntities.nextElement();
                        String jarEntryName = jarEntry.getName();
                        if (jarEntryName.endsWith(".class")){
                            String className = jarEntryName.substring(0,jarEntryName.lastIndexOf("."));
                            className = className.replace("/",".");
                            doAddClass(classSet,className);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classSet;
    }

    public static void addClass(HashSet<Class<?>> classSet,String packagePath,String packageName){
        File[] files = new File(packagePath).listFiles((file)->{
            return (file.isFile() && file.getName().endsWith(".class"))||file.isDirectory();
        });
        Arrays.stream(files).forEach(file -> {
            String fileName = file.getName();
            String className = fileName.substring(0,fileName.lastIndexOf("."));
            if(StringUtil.isNotEmpty(className)){
                className = packageName + "." + className;
                doAddClass(classSet,className);
            }else{
                //递归
                String subPackagePath = fileName;
                if(StringUtil.isNotEmpty(fileName)){
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = fileName;
                if(StringUtil.isNotEmpty(packageName)){
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet,subPackagePath,subPackageName);
            }
        });
    }

    private static void doAddClass(HashSet<Class<?>> classSet,String className){
        Class clazz = loadClass(className,false);
        classSet.add(clazz);
    }


}
