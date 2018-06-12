package com.springtiny.proxy;

import com.springtiny.annotation.Transaction;
import com.springtiny.helper.DatabaseHelper;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Slf4j
public class TransactionProxy implements Proxy{
    ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal<Boolean>(){
        @Override
        protected Boolean initialValue() {
            return true;
        }
    };

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        boolean flag = FLAG_HOLDER.get();
        Object result = null;
        Method method = proxyChain.getTargetMethod();
        if(flag&&method.isAnnotationPresent(Transaction.class)){
            try{
                log.info("transaction begin");
                DatabaseHelper.beginTransaction();
                result = proxyChain.doProxyChain();
                DatabaseHelper.commitTransaction();
                log.info("transaction commit");
            }catch(Exception e){
                log.error(e.getMessage());
                DatabaseHelper.rollbackTransaction();
                throw e;
            }finally {
                FLAG_HOLDER.remove();
            }
        }else{
            result = proxyChain.doProxyChain();
        }
        return result;
    }
}
