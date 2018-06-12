package com.springtiny.helper;

import com.sun.deploy.net.HttpRequest;
import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServeletHelper {
    private static ThreadLocal<ServeletHelper>  SERVELET_HELPER_HOLDER = new ThreadLocal<>();

    public HttpServletRequest request;

    public HttpServletResponse response;

    private ServeletHelper(HttpServletRequest request,HttpServletResponse response){
        this.request = request;
        this.response = response;
    }

    public static void init(HttpServletRequest request,HttpServletResponse response){
        SERVELET_HELPER_HOLDER.set(new ServeletHelper(request,response));
    }

    public static HttpServletRequest getRequest() {
        return SERVELET_HELPER_HOLDER.get().request;
    }

    public static HttpServletResponse getResponse() {
        return SERVELET_HELPER_HOLDER.get().response;
    }

    public static void destory(){
        SERVELET_HELPER_HOLDER.remove();
    }
}
