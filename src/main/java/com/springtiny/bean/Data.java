package com.springtiny.bean;

import lombok.Getter;

@Getter
public class Data {
    private Object model;

    public Data(Object model){
        this.model = model;
    }
}
