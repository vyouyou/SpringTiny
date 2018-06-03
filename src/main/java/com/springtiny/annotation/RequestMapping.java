package com.springtiny.annotation;

import com.springtiny.enums.MethodEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    /**
     *value
     * @return
     */
    String value();

    MethodEnum method();
}
