package com.springtiny.bean;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class View {
    private String path;

    private Map<String,Object> model;

    public View(String path){
        this.path = path;
        model = new HashMap<>();
    }

    public View addModel(String path,Object object){
        model.put(path,object);
        return this;
    }
}
