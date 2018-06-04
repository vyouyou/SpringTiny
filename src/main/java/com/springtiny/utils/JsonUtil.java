package com.springtiny.utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static  <T> String toJson(T obj){
        String json = "";
        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static <T> T fromJson(String json,Class<T> clazz){
        T pojo=null;
        try {
            pojo = OBJECT_MAPPER.readValue(json,clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pojo;
    }
}
