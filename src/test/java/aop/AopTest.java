package aop;

import com.springtiny.aop.DynmicProxy;
import com.springtiny.aop.Hello;
import com.springtiny.aop.HelloImpl;
import com.springtiny.controller.TestController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class AopTest {
    @Test
    public void TestHello(){
        Hello hello = new HelloImpl();
        DynmicProxy dynmicProxy = new DynmicProxy(hello);
        Hello helloProxy = (Hello) Proxy.newProxyInstance(hello.getClass().getClassLoader(),hello.getClass().getInterfaces(),dynmicProxy);
        helloProxy.sayHello();
    }

    @Test
    public void testField(){
       Field[] fields = TestController.class.getFields();
        System.out.println(fields.length);
        Arrays.stream(fields).forEach(field -> {
            System.out.println(field.getName()+field.getAnnotatedType());
        });
    }

}
