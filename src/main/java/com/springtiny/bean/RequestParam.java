package com.springtiny.bean;

import lombok.Getter;

import java.util.Map;

@Getter
public class RequestParam {
    private Map<String,Object> paramMap;

    public RequestParam(Map<String,Object> paramMap){
        this.paramMap = paramMap;
    }


}
