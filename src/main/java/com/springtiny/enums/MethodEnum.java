package com.springtiny.enums;


public enum MethodEnum {
    GET("get"),
    POST("post"),
    PUT("put"),
    DELETE("delete");

    private String code;

    MethodEnum(String code){
        this.code =code;
    }

    String getCode(){
        return code;
    }

    public static MethodEnum getByCode(String code){
       for(MethodEnum methodEnum:MethodEnum.values()){
           if(methodEnum.getCode().equals(code)){
               return methodEnum;
           }
       }
       return null;
    }
}
