package com.springtiny.controller;

import com.springtiny.annotation.Controller;
import com.springtiny.annotation.RequestMapping;
import com.springtiny.bean.Data;
import com.springtiny.bean.RequestParam;
import com.springtiny.enums.MethodEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TestController {
    @RequestMapping(value = "/test",method = MethodEnum.GET)
    public Data getMethod(RequestParam requestParam){
        Temp temp = new Temp();
        temp.setA("aaaaa");
        return new Data(temp);
    }

    @Setter
    @Getter
    class Temp{
        private String a;
    }
}
