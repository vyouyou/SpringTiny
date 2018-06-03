package com.springtiny.helper;

import com.springtiny.annotation.Controller;
import com.springtiny.annotation.Service;
import com.springtiny.utils.ClassUtils;

import java.util.Set;

public class ClassHelper {
    private static Set<Class<?>> CLASS_SET;

    static {
        CLASS_SET = ClassUtils.getClassSet(ConfigHelper.getAppBasePackage());
    }

    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    /**
     * 获取controller
     * @return
     */
    public static Set<Class<?>> getControllerSet() {
        return (Set<Class<?>>) CLASS_SET.stream().filter(clazz -> {
           return clazz.isAnnotationPresent(Controller.class);
        });
    }

    /**
     * 获取service
     * @return
     */
    public static Set<Class<?>> getServiceSet(){
        return (Set<Class<?>>) CLASS_SET.stream().filter(clazz->{
           return  clazz.isAnnotationPresent(Service.class);
        });
    }

    /**
     * 获取bean
     * @return
     */
    public static Set<Class<?>> getBeanClassSet(){
        return (Set<Class<?>>) CLASS_SET.stream().filter(clazz->{
            return  clazz.isAnnotationPresent(Service.class)||clazz.isAnnotationPresent(Controller.class);
        });
    }
}
