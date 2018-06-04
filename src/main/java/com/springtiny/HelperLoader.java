package com.springtiny;

import com.springtiny.helper.*;
import com.springtiny.utils.ClassUtils;

import java.util.Arrays;

public class HelperLoader {
    public static void init() {
        Class<?>[] classList = {
                BeanHelper.class,
                ClassHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        Arrays.stream(classList).forEach(aClass -> {
            ClassUtils.loadClass(aClass.getName(),false);
        });
    }
}
