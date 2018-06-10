package com.springtiny.proxy;

import lombok.Getter;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ProxyChain {
    private final Class<?> targetClass;

    private final Object targetObject;

    private final Method targetMethod;

    /**
     * 方法代理对象 会在doProxyChain中用到
     */
    private final MethodProxy methodProxy;

    private final Object[] methodParams;

    private List<Proxy> proxyList = new ArrayList<Proxy>();

    private int proxyIndex = 0;

    public ProxyChain(Class<?> targetClass,Object targetObject,Method targetMethod,MethodProxy methodProxy,Object[] methodParams,List<Proxy> proxyList){
        this.targetClass =targetClass;
        this.targetObject=targetObject;
        this.targetMethod = targetMethod;
        this.methodProxy = methodProxy;
        this.methodParams =methodParams;
        this.proxyList = proxyList;
    }

    public Object doProxyChain() throws Throwable {
        Object methodResult;
        if(proxyIndex < proxyList.size()){
            methodResult = proxyList.get(proxyIndex++).doProxy(this);
        }else{
            methodResult = methodProxy.invokeSuper(targetObject,methodParams);
        }
        return methodResult;
    }
}
