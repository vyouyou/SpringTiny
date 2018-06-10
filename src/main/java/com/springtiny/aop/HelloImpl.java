package com.springtiny.aop;

public class HelloImpl implements Hello{
    @Override
    public void sayHello() {
        System.out.printf("say hello");
    }

    public void sayByebye() {System.out.printf("say bye bye");}
}
