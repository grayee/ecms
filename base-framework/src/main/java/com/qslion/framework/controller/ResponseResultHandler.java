package com.qslion.framework.controller;

import com.qslion.framework.bean.ErrorResult;
import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.bean.RestResult;
import com.qslion.framework.interceptor.ResponseResultInterceptor;
import com.qslion.framework.util.RequestContextHolderUtil;
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
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        ResponseResult responseResultAnn = (ResponseResult) RequestContextHolderUtil.getRequest()
            .getAttribute(ResponseResultInterceptor.RESPONSE_RESULT);
        boolean supported = false;
        if (responseResultAnn != null) {
            supported = true;
        }
        return supported;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
        Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ResponseResult responseResultAnn = (ResponseResult) RequestContextHolderUtil.getRequest()
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
