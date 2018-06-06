package com.springtiny.helper;

import com.springtiny.annotation.RequestMapping;
import com.springtiny.bean.Handler;
import com.springtiny.bean.Request;
import com.springtiny.enums.MethodEnum;
import com.springtiny.utils.CollectionUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
public class ControllerHelper {
    private static final Map<Request, Handler> REQUEST_MAP = new HashMap<>();

    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerSet();
        if (CollectionUtil.isNotEmpty(controllerClassSet)) {
            for (Class controllerClass:
                    controllerClassSet ) {
                Method[] methods = controllerClass.getMethods();
                if (methods == null) {
                    continue;
                }
                for (Method method:
                     methods) {
                    if (method.isAnnotationPresent(RequestMapping.class)){
                        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                        Request request = new Request(requestMapping.method(),requestMapping.value());
                        Handler handler = new Handler(controllerClass,method);
                        REQUEST_MAP.put(request,handler);
                    }
                }
            }
        }
    }

    /**
     * 获取handler
     * @param methodEnum
     * @param requestPath
     * @return
     */
    public static Handler getHandler(MethodEnum methodEnum,String requestPath){
        Request request = new Request(methodEnum,requestPath);
        return REQUEST_MAP.get(request);
    };
}
