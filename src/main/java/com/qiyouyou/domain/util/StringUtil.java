package com.qiyouyou.domain.util;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {
    public static boolean isEmpty(String str){
        if(str != null){
            str = str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
