package com.qiyouyou.domain.util;

import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CollenctionUtil {
    public static Map<String,Object> ObjectToMap(Object object){
        Field[] fields = object.getClass().getDeclaredFields();
        Map<String,Object> map = new HashMap<>();
        Arrays.stream(fields).forEach(field -> {
            Object obj = getValueByFieldName(field.getName(),object);
            if(obj != null){
                map.put(field.getName(),obj);
            };
        });
        return map;
    }

    public static Object getValueByFieldName(String fieldName,Object object){
        String firstLetter = fieldName.substring(0,1).toUpperCase();
        String getter = "get" + firstLetter + fieldName.substring(1);
        try {
            Method getMethod = object.getClass().getMethod(getter);
            Object value = getMethod.invoke(object);
            return value;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
