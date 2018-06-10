package com.springtiny.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynmicProxy implements InvocationHandler{
    private Object target;

    public DynmicProxy(Object target){
        this.target = target;
    }

    public <T> T getProxyInstance(){
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target,args);
        after();
        return result;
    }

    private void after() {
        System.out.printf("after");
    }

    private void before() {
        System.out.printf("before");
    }


}
