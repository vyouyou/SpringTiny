package reflect;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
public class ReflectTest {
    class Test{
        public void getA(int a,Test b){

        }
    }

    @org.junit.Test
    public void getParams(){
        Method method= Test.class.getMethods()[0];
        Arrays.stream(method.getGenericParameterTypes()).forEach(methodTypeVariable -> {
            log.info(methodTypeVariable.getTypeName());
        });
    }

    @org.junit.Test
    public void getTypeName(){
        log.info(Test.class.getName() + Test.class.getTypeName() + Test.class.getSimpleName());
    }
}
