package com.springtiny;

import com.springtiny.bean.Data;
import com.springtiny.bean.Handler;
import com.springtiny.bean.RequestParam;
import com.springtiny.bean.View;
import com.springtiny.enums.MethodEnum;
import com.springtiny.helper.BeanHelper;
import com.springtiny.helper.ConfigHelper;
import com.springtiny.helper.ControllerHelper;
import com.springtiny.utils.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        /**
         * 初始化loader
         */
        HelperLoader.init();
        ServletContext servletContext = servletConfig.getServletContext();
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestMethod = req.getMethod().toLowerCase();
        String requestPath = req.getPathInfo();
        Handler handler = ControllerHelper.getHandler(MethodEnum.getByCode(requestMethod), requestPath);
        if (handler == null) {
            return;
        }
        //获取controller的实例
        Class<?> controllerClass = handler.getControllerClass();
        Object controllerBean = BeanHelper.getBean(controllerClass);
        //获取请求的 参数对象
        Map<String, Object> paramMap = new HashMap<>();
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String parameterValue = req.getParameter(parameterName);
            paramMap.put(parameterName, parameterValue);
        }
        //获取body
        String body = CodeUtil.decodeUrl(SteamUtil.getString(req.getInputStream()));
        if (StringUtil.isNotEmpty(body)) {
            String[] params = body.split("&");
            Arrays.stream(params).forEach(param -> {
                String[] arr = param.split("=");
                paramMap.put(arr[0], arr[1]);
            });
        }
        RequestParam requestParam = new RequestParam(paramMap);
        Method method = handler.getMethod();
        Object result = ReflectionUtil.invokeMethod(controllerBean, method, requestParam);
        //处理jsp
        if (result instanceof View) {
            View view = (View) result;
            String path = view.getPath();
            if (path.startsWith("/")) {
                resp.sendRedirect(req.getContextPath() + path);
            } else {
                Map<String, Object> model = view.getModel();
                model.forEach((key,value)->{
                    req.setAttribute(key,value);
                });
                req.getRequestDispatcher(ConfigHelper.getAppJspPath()+path).forward(req,resp);
            }
        }
        //处理json
        else if (result instanceof Data) {
            Data data = (Data) result;
            Object model = data.getModel();
            if (model == null) return;
            resp.setContentType("application/json");
            resp.setContentType("UTF-8");
            PrintWriter printWriter = resp.getWriter();
            String json = JsonUtil.toJson(model);
            printWriter.write(json);
            printWriter.flush();
            printWriter.close();
        }
    }
}
