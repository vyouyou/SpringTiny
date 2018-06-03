package com.springtiny.helper;

import com.springtiny.annotation.AutoWired;
import com.springtiny.utils.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

public class IocHelper {
    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (beanMap != null && !beanMap.isEmpty()) {
            beanMap.forEach((key, value) -> {
                Class<?> beanClass = key;
                Object beanInstance = value;
                Field[] fields = beanClass.getFields();
                Arrays.stream(fields).forEach(field -> {
                    if(field.isAnnotationPresent(AutoWired.class));
                    Class<?> fieldClass = field.getType();
                    Object fieldObj = beanMap.get(fieldClass);
                    if(fieldObj!=null){
                        ReflectionUtil.setField(beanInstance,field,fieldObj);
                    }
                });
            });
        }
    }
}
