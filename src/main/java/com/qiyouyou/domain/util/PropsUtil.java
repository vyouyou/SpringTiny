package com.qiyouyou.domain.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsUtil {

    public static Properties loadProps(String fileName) {
        Properties properties = null;
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (is == null) {
                throw new FileNotFoundException(fileName + "not found");
            }
            properties = new Properties();
            properties.load(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }

    public static String getString(Properties props, String key) {
        return getString(props, key, "");
    }

    public static String getString(Properties props, String key, String defaultValue) {
        return props.getProperty(key,defaultValue);
    }

    public static Integer getInt(Properties props,String key){
        return getInt(props,key,0);
    }

    public static Integer getInt(Properties props,String key,Integer defaultValue){
        Integer value = defaultValue;
        if (props.containsKey(key)){
            CastUtil.castInt(props.get(key));
        }
        return value;
    }

    public static boolean getBoolean(Properties props, String key){
        return getBoolean(props,key,false);
    }

    public static boolean getBoolean(Properties props,String key,boolean defaultValue){
        boolean value = defaultValue;
        if(props.containsKey(key)){
            CastUtil.castBoolean(props.get(key));
        }
        return value;
    }
}
