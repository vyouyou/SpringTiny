package com.springtiny.utils;

import java.lang.reflect.Field;

public class ReflectionUtil {
    public static void setField(Object beanInstance, Field field,Object beanFieldInstance){
        try {
            field.set(beanInstance,beanFieldInstance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
