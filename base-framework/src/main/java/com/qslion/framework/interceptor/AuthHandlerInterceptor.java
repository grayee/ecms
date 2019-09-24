package com.qslion.framework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局权限拦截器
 *
 * @author Gray.Z
 * @date 2018/9/18.
 */
public class AuthHandlerInterceptor implements HandlerInterceptor {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private static final String HEADER_TOKEN_NAME = "X-TOKEN";
    private static final String HEADER_CLIENT_NAME = "X-CLIENT";
    private static final String HEADER_APP_VERSION = "APP-VERSION";
    private static final String HEADER_API_VERSION = "API-VERSION";
    private static final String HEADER_LANG_NAME = "X-LANG";
    private static final String TENANT_HEADER_NAME = "X-TENANT-ID";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
        @Nullable Exception ex) throws Exception {

    }
}
