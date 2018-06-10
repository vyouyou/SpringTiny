package com.springtiny.aop;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String [] args){
        Hello hello = new HelloImpl();
        DynmicProxy dynmicProxy = new DynmicProxy(hello);
        ((Hello)dynmicProxy.getProxyInstance()).sayHello();
    }
}
