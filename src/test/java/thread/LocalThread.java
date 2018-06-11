package thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class LocalThread {
    class MyThreadLocal<T>{
        Map<Thread,T> map = Collections.synchronizedMap(new HashMap<>());

        public void set(T t){
            map.put(Thread.currentThread(),t);
        }

        public void get(){
            map.get(Thread.currentThread());
        }
    }

    private static ThreadLocal<Integer> numberContainer = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    class ClientThread extends Thread{
        private Sequence squence;

        public ClientThread(){
            this.squence = new Sequence();
        }
        @Override
        public void run() {
            for(int i=0;i<3;i++){
                log.info(Thread.currentThread().getName() +"=>" +squence.getNumber());
            }
        }
    }

    class Sequence{

        public int getNumber(){
            int temp = numberContainer.get() +1;
            numberContainer.set(temp);
            return temp;
        }
    }

    @Test
    public void startTest(){
        ClientThread clientThread1 = new ClientThread();
        ClientThread clientThread2 = new ClientThread();
        ClientThread clientThread3 = new ClientThread();
        clientThread1.start();
        clientThread2.start();
        clientThread3.start();
    }
}
