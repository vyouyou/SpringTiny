package com.springtiny.helper;

import com.springtiny.annotation.AutoWired;
import com.springtiny.utils.ReflectionUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

@Slf4j
public class IocHelper {
    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (beanMap != null && !beanMap.isEmpty()) {
            beanMap.forEach((key, value) -> {
                Class<?> beanClass = key;
                Object beanInstance = value;
                Field[] fields = beanClass.getDeclaredFields();
                Arrays.stream(fields).forEach(field -> {
                    field.setAccessible(true);
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
