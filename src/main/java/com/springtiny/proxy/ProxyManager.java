package com.springtiny.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.util.List;

public class ProxyManager {
    public static <T> T createProxy(final Class<?> targetClass,final List<Proxy> proxyList){
        return (T) Enhancer.create(targetClass,
                (MethodInterceptor) (o, method, objects, methodProxy) -> new ProxyChain(targetClass,o,method,methodProxy,objects,proxyList).doProxyChain());
    }
}
