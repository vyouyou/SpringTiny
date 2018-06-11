package com.springtiny.helper;

import com.springtiny.annotation.Controller;
import com.springtiny.annotation.Service;
import com.springtiny.utils.ClassUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
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
     *
     * @return
     */
    public static Set<Class<?>> getControllerSet() {
        Set<Class<?>> classSet = CLASS_SET.stream().filter(clazz ->
                clazz.isAnnotationPresent(Controller.class)
        ).collect(Collectors.toSet());
        return classSet;
    }

    /**
     * 获取service
     *
     * @return
     */
    public static Set<Class<?>> getServiceSet() {
        return CLASS_SET.stream().filter(clazz ->
                clazz.isAnnotationPresent(Service.class)
        ).collect(Collectors.toSet());
    }

    /**
     * 获取bean
     *
     * @return
     */
    public static Set<Class<?>> getBeanClassSet() {
        return CLASS_SET.stream().filter(clazz ->
                clazz.isAnnotationPresent(Service.class) || clazz.isAnnotationPresent(Controller.class)
        ).collect(Collectors.toSet());
    }

    /**
     * 获取基础包下某类的所有子类或者实现类
     * @param superClass
     * @return
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass){
        Set<Class<?>> classSet = new HashSet<>();
        CLASS_SET.stream().forEach(clazz->{
            if(superClass.isAssignableFrom(clazz)&&!superClass.equals(clazz)){
                classSet.add(clazz);
            }
        });
        return classSet;
    }

    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotionClass){
        Set<Class<?>> classSet = new HashSet<>();
        for (Class clazz:
             CLASS_SET) {
            if(clazz.isAnnotationPresent(annotionClass)){
                classSet.add(clazz);
            }
        }
        return classSet;
    }

    public static Collection<? extends Class<?>> getClassSetByClass(Class<? extends Annotation> cls) {
        Set<Class<?>> classSet = new HashSet<>();
        for (Class clazz:
                CLASS_SET) {
            if(clazz.equals(cls)){
                classSet.add(clazz);
            }
        }
        return classSet;
    }
}
