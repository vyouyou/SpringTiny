package com.springtiny.bean;

import com.springtiny.enums.MethodEnum;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
public class Request {
    private MethodEnum method;

    private String path;

    public Request(MethodEnum method,String path){
        this.method = method;
        this.path = path;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this,obj);
    }
}
