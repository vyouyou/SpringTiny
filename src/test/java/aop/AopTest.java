package aop;

import com.springtiny.aop.DynmicProxy;
import com.springtiny.aop.Hello;
import com.springtiny.aop.HelloImpl;
import org.junit.Test;

import java.lang.reflect.Proxy;

public class AopTest {
    @Test
    public void TestHello(){
        Hello hello = new HelloImpl();
        DynmicProxy dynmicProxy = new DynmicProxy(hello);
        Hello helloProxy = (Hello) Proxy.newProxyInstance(hello.getClass().getClassLoader(),hello.getClass().getInterfaces(),dynmicProxy);
        helloProxy.sayHello();
    }
}
