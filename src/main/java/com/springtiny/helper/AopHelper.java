package com.springtiny.helper;

import com.springtiny.annotation.Aspect;
import com.springtiny.annotation.Service;
import com.springtiny.proxy.AspectProxy;
import com.springtiny.proxy.Proxy;
import com.springtiny.proxy.ProxyManager;
import com.springtiny.proxy.TransactionProxy;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.*;

@Slf4j
public class AopHelper {
    static{
        try {
            Map<Class<?>,Set<Class<?>>> proxyMap = createProxyMap();
            Map<Class<?>,List<Proxy>> targetMap = createTargetMap(proxyMap);
            for (Map.Entry<Class<?>,List<Proxy>> targetEntry:targetMap.entrySet()){
                Class<?> targetClass = targetEntry.getKey();
                List<Proxy> proxyList = targetEntry.getValue();
                Object proxy = ProxyManager.createProxy(targetClass,proxyList);
                BeanHelper.setBean(targetClass,proxy);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }

    }


    /**
     * 获取aspect作用的目标class
     * @param aspect
     * @return
     * @throws Exception
     */
    private static Set<Class<?>> createTargetClassSet(Aspect aspect) throws Exception{
        Set<Class<?>> targetClassSet = new HashSet<>();
        Class<? extends Annotation> clazz = aspect.value();
        //这个 aspect.value() 的作用目标，获取这些class
        if (clazz != null && !clazz.equals(Aspect.class)){
            targetClassSet.addAll(ClassHelper.getClassSetByClass(clazz));
        }
        return targetClassSet;
    }

    /**
     * 创建aspectproxy子类和 子类所要作用的类的对应关系
     * @return
     * @throws Exception
     */
    private static Map<Class<?>,Set<Class<?>>> createProxyMap() throws Exception {
        Map<Class<?>,Set<Class<?>>> proxyMap = new HashMap<>();
        //获取AspectProxy的子类
        addAspectProxy(proxyMap);
        addTransactionProxy(proxyMap);
        return proxyMap;
    }

    /**
     * 创建aspect和它作用的class
     * @param proxyMap
     * @throws Exception
     */
    private static void addAspectProxy(Map<Class<?>,Set<Class<?>>> proxyMap) throws Exception {
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        for(Class<?> proxyClass:proxyClassSet){
            if(proxyClass.isAnnotationPresent(Aspect.class)){
                Aspect aspect = proxyClass.getAnnotation(Aspect.class);
                Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                proxyMap.put(proxyClass,targetClassSet);
            }
        }
    }

    /**
     * 获取所有service
     * @param proxyMap
     */
    private static void addTransactionProxy(Map<Class<?>,Set<Class<?>>> proxyMap){
        proxyMap.put(TransactionProxy.class,ClassHelper.getClassSetByAnnotation(Service.class));
    }

    /**
     * 建立aspectProxy和目标对象之间的对应关系
     * @param proxyMap 上个函数 aspectproxy和它的作用类
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private static Map<Class<?>,List<Proxy>> createTargetMap(Map<Class<?>,Set<Class<?>>> proxyMap) throws IllegalAccessException, InstantiationException {
        //用来存放
        Map<Class<?>,List<Proxy>> targetMap = new HashMap<Class<?>,List<Proxy>>();
        for (Map.Entry<Class<?>,Set<Class<?>>> proxyEntry:proxyMap.entrySet()){
            Class<?> proxyClass = proxyEntry.getKey();
            Set<Class<?>> targetClassSet = proxyEntry.getValue();
            for(Class<?> targetClass : targetClassSet){
                Proxy proxy = (Proxy) proxyClass.newInstance();
                if(targetMap.containsKey(targetClass)){
                    targetMap.get(targetClass).add(proxy);
                }else{
                    List<Proxy> proxyList = new ArrayList<>();
                    proxyList.add(proxy);
                    targetMap.put(targetClass,proxyList);
                }
            }
        }
        return targetMap;
    }
}
