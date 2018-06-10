package com.springtiny.controller;

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
    @RequestMapping(value = "/test",method = MethodEnum.GET)
    public Data getMethod(RequestParam requestParam){
        Temp temp = new Temp();
        temp.setA("aaaaa");
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
