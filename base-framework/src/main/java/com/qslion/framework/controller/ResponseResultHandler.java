package com.qslion.framework.controller;

import com.qslion.framework.bean.ErrorResult;
import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.bean.RestResult;
import com.qslion.framework.interceptor.ResponseResultInterceptor;
import com.qslion.framework.util.RequestContextUtil;
import javax.annotation.Nonnull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 接口响应体处理器
 *
 * @author Gray.Z
 * @date 2018/9/22 22:10.
 */
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(@Nonnull MethodParameter returnType, @Nonnull Class<? extends HttpMessageConverter<?>> converterType) {
        ResponseResult responseResultAnn = (ResponseResult) RequestContextUtil.getRequest()
            .getAttribute(ResponseResultInterceptor.RESPONSE_RESULT);
        boolean supported = false;
        if (responseResultAnn != null) {
            supported = true;
        }
        return supported;
    }

    @Override
    @Nonnull
    public Object beforeBodyWrite(@Nonnull Object body, @Nonnull MethodParameter returnType, @Nonnull MediaType selectedContentType,
        @Nonnull Class<? extends HttpMessageConverter<?>> selectedConverterType, @Nonnull ServerHttpRequest request, @Nonnull
        ServerHttpResponse response) {
        ResponseResult responseResultAnn = (ResponseResult) RequestContextUtil.getRequest()
            .getAttribute(ResponseResultInterceptor.RESPONSE_RESULT);

        Class<RestResult> resultClazz = responseResultAnn.value();

        if (resultClazz.isAssignableFrom(RestResult.class)) {
            if (body instanceof ErrorResult) {
                return body;
            }
            return RestResult.success(body);
        }
        return body;
    }

}
