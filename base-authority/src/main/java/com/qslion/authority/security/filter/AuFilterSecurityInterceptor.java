/*
 * Copyright (c) 2018. Tianyi AIDOC Company.Inc. All Rights Reserved.
 */

package com.qslion.authority.security.filter;

import com.qslion.authority.security.AuAccessDecisionManager;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

/**
 * spring security 自定义过滤器：资源访问第一个要过的filte
 *
 * http://elim.iteye.com/blog/2211966
 *
 * AbstractSecurityInterceptor是一个实现了对受保护对象的访问进行拦截的抽象类，其中有几个比较重要的方法。
 * beforeInvocation()方法实现了对访问受保护对象的权限校验，内部用到了AccessDecisionManager和AuthenticationManager；
 * finallyInvocation()方法用于实现受保护对象请求完毕后的一些清理工作，主要是如果在beforeInvocation()中改变了SecurityContext，
 * 则在finallyInvocation()中需要将其恢复为原来的SecurityContext，该方法的调用应当包含在子类请求受保护资源时的finally语句块中；
 * afterInvocation()方法实现了对返回结果的处理，在注入了AfterInvocationManager的情况下默认会调用其decide()方法。
 *
 * AbstractSecurityInterceptor只是提供了这几种方法，并且包含了默认实现，具体怎么调用将由子类负责。每一种受保护对象都拥有继承自AbstractSecurityInterceptor的拦截器类，
 * MethodSecurityInterceptor将用于调用受保护的方法，而FilterSecurityInterceptor将用于受保护的Web请求。
 * 它们在处理受保护对象的请求时都具有一致的逻辑，具体的逻辑如下。
 * 1、先将正在请求调用的受保护对象传递给beforeInvocation()方法进行权限鉴定。
 * 2、权限鉴定失败就直接抛出异常了。
 * 3、鉴定成功将尝试调用受保护对象，调用完成后，不管是成功调用，还是抛出异常，都将执行finallyInvocation()。
 * 4、如果在调用受保护对象后没有抛出异常，则调用afterInvocation()。
 *
 *
 * spring security 自定义认证过滤器：资源访问第一个要过的filter
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Component
public class AuFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    private static Logger logger = LogManager.getLogger();

    private static final String FILTER_APPLIED = "__Spring_Security_AuthorizationSecurityInterceptor_FilterApplied";

    private boolean observeOncePerRequest = true;

    @Autowired
    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    @Autowired
    public void setMyAccessDecisionManager(AuAccessDecisionManager auAccessDecisionManager) {
        super.setAccessDecisionManager(auAccessDecisionManager);
    }

    @Override
    public Class<? extends Object> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        invoke(fi);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    /**
     * fi包含一个被拦截的url，会调用AuInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
     * 之后调用AuAccessDecisionManager的decide方法来校验用户的权限是否足够
     *
     * @param fi 过滤器调用
     * @throws IOException IO异常
     * @throws ServletException Servlet 异常
     */
    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        if (fi.getRequest() != null && fi.getRequest().getAttribute(FILTER_APPLIED) != null && this.observeOncePerRequest) {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } else {
            if (fi.getRequest() != null) {
                fi.getRequest().setAttribute(FILTER_APPLIED, Boolean.TRUE);
            }

            InterceptorStatusToken token = super.beforeInvocation(fi);

            try {
                //执行下一个拦截器
                fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
            } finally {
                super.finallyInvocation(token);
            }

            super.afterInvocation(token, (Object) null);
        }
    }

    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }

    public boolean isObserveOncePerRequest() {
        return this.observeOncePerRequest;
    }

    public void setObserveOncePerRequest(boolean observeOncePerRequest) {
        this.observeOncePerRequest = observeOncePerRequest;
    }
}
