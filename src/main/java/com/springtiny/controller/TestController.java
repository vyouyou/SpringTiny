package com.springtiny.controller;

import com.springtiny.annotation.AutoWired;
import com.springtiny.annotation.Controller;
import com.springtiny.annotation.RequestMapping;
import com.springtiny.bean.Data;
import com.springtiny.bean.RequestParam;
import com.springtiny.enums.MethodEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

@Controller
@Slf4j
public class TestController implements Annotation{
    @AutoWired
    TestService testService;

    @RequestMapping(value = "/test",method = MethodEnum.GET)
    public Data getMethod(RequestParam requestParam){
        Temp temp = new Temp();
        testService.helloService();
        temp.setA("aaaaa");
        return new Data(temp);
    }

    @RequestMapping(value="/test-no-param",method = MethodEnum.GET)
    public Data noParam(){
        Temp temp = new Temp();
        temp.setA("bbb");
        return new Data(temp);
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    @Setter
    @Getter
    class Temp{
        private String a;
    }
}
