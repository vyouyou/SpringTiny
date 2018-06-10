package com.springtiny.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Slf4j
public class AspectProxy implements Proxy{
    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result = null;
        Class<?> clazz = proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethodParams();
        
        begin();
        try{
            if(intercept(clazz,method,params)){
                before(clazz,method,params);
                result = proxyChain.doProxyChain();
                after(clazz,method,params);
            }else{
                result = proxyChain.doProxyChain();
            }
        }catch(Exception e){
            log.error("proxy failure",e);
            error(clazz,method,params,e);
            throw e;
        }finally {
            end();
        }
        
        
        return result;
    }

    public void end() {
    }

    public void error(Class<?> clazz, Method method, Object[] params, Exception e) {
    }

    public void after(Class<?> clazz, Method method, Object[] params) throws Throwable {
    }

    public void before(Class<?> clazz, Method method, Object[] params)throws Throwable {
    }

    public boolean intercept(Class<?> clazz, Method method, Object[] params) throws Throwable {
        return true;
    }

    public void begin()  {
    }
}
