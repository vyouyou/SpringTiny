package com.springtiny.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibProxy implements MethodInterceptor{
    private static CGLibProxy instance;


    public <T> T getProxy(Class<T> cls){
        return (T) Enhancer.create(cls,this);
    }

    public static CGLibProxy getInstance(){
        if(instance==null){
            instance = new CGLibProxy();
        }
        return instance;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(o,objects);
        after();
        return result;
    }

    private void before() {
        System.out.printf("cglib proxy before");
    }

    private void after() {
        System.out.printf("cglib proxy after");

    }

}


