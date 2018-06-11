package com.springtiny;

import com.springtiny.helper.*;
import com.springtiny.utils.ClassUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class HelperLoader {
    // 用于helper有次序的初始化
    public static void init() {
        Class<?>[] classList = {
                BeanHelper.class,
                ClassHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        Arrays.stream(classList).forEach(aClass -> {
            ClassUtils.loadClass(aClass.getName(),true);
        });
    }
}
