package com.springtiny.utils;

import com.springtiny.bean.RequestParam;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtil {
    public static void setField(Object beanInstance, Field field,Object beanFieldInstance){
        try {
            field.set(beanInstance,beanFieldInstance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Object invokeMethod(Object bean, Method method, RequestParam param){
        Object result = null;
        try {
            String a ="aaa";
            result = method.invoke(bean,param);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }
}
