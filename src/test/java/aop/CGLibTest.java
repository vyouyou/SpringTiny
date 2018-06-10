package aop;

import com.springtiny.aop.CGLibProxy;
import com.springtiny.aop.HelloImpl;
import org.junit.Test;

public class CGLibTest {

    @Test
    public void sayHelloTest(){
        CGLibProxy cgLibProxy = CGLibProxy.getInstance();
        HelloImpl helloProxy = cgLibProxy.getProxy(HelloImpl.class);
        helloProxy.sayByebye();
    }

}
