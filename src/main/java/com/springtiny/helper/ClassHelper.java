package com.springtiny.helper;

import com.springtiny.annotation.Controller;
import com.springtiny.annotation.Service;
import com.springtiny.utils.ClassUtils;
import lombok.extern.slf4j.Slf4j;

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
}
