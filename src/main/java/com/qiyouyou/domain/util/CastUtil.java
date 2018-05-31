package com.qiyouyou.domain.util;

public class CastUtil {
    public static String castString(Object object) {
        return castString(object, "");
    }

    public static String castString(Object object, String defaultValue) {
        return object == null ? "" : String.valueOf(object);
    }

    public static double castDouble(Object obj) {
        return castDouble(obj, 0);
    }

    public static double castDouble(Object obj, double defaultValue) {
        double value = defaultValue;
        if (obj == null) {
            return value;
        }
        String str = castString(obj);
        if (StringUtil.isEmpty(str)) {
            return value;
        }
        try {
            value = Double.parseDouble(str);
        } catch (NumberFormatException e) {

        } finally {
            return value;
        }
    }

    public static int castInt(Object obj){
        return castInt(obj,0);
    }

    public static int castInt(Object obj,int defaultValue){
        int value = defaultValue;
        if (obj == null) {
            return value;
        }
        String str = castString(obj);
        if (StringUtil.isEmpty(str)) {
            return value;
        }
        try {
            value = Integer.parseInt(str);
        } catch (NumberFormatException e) {

        } finally {
            return value;
        }
    }

    public static long castLong(Object obj){
        return castLong(obj,0);
    }

    public static long castLong(Object obj,long defaultValue){
        long value = defaultValue;
        if (obj == null) {
            return value;
        }
        String str = castString(obj);
        if (StringUtil.isEmpty(str)) {
            return value;
        }
        try {
            value = Long.parseLong(str);
        } catch (NumberFormatException e) {

        } finally {
            return value;
        }
    }

    public static boolean castBoolean(Object obj){
        return castBoolean(obj,false);
    }

    public static boolean castBoolean(Object obj,boolean defaultValue){
        boolean value = defaultValue;
        if (obj == null) {
            return value;
        }
        return Boolean.parseBoolean(castString(obj));
    }

}
