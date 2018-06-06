package com.springtiny.helper;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class BeanHelper {
    static private HashMap<Class<?>,Object> BEAN_MAP = new HashMap<>();

    static {
        ClassHelper.getBeanClassSet().stream().forEach(bean->{
            try {
                Object obj = bean.newInstance();
                BEAN_MAP.put(bean,obj);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    public static HashMap<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    public static <T> T getBean(Class<T> clazz){
        return (T)BEAN_MAP.get(clazz);
    }
}
