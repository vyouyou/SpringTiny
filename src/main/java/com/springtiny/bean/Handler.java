package com.springtiny.bean;

import lombok.Getter;

import java.lang.reflect.Method;

@Getter
public class Handler {
    Class<?> controllerClass;

    Method method;

    public Handler(Class<?> clazz,Method method){
        this.controllerClass = clazz;
        this.method = method;
    }
}
