package com.springtiny.controller;

import com.springtiny.annotation.Aspect;
import com.springtiny.proxy.AspectProxy;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Slf4j
@Aspect(TestController.class)
public class HahahaProxy extends AspectProxy{
    @Override
    public void before(Class<?> clazz, Method method, Object[] params) throws Throwable {
        log.info("=============> haha before");
    }

    @Override
    public void after(Class<?> clazz, Method method, Object[] params) throws Throwable {
        log.info("=============> haha after");
    }
}
