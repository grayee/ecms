package com.qslion.security.filter;

import com.qslion.framework.bean.RestResult;
import com.qslion.framework.enums.ResultCode;
import com.qslion.framework.util.JSONUtils;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

/**
 * oauth 自定义异常处理器
 *
 * 认证失败返回401的时候调用此异常处理器，通过异常处理器结合refresh_token进行token的刷新，
 *
 * 刷新成功则通过请求转发（request.getRequestDispatcher）的方式再次访问受拦截资源
 *
 * @author Gray.Z
 * @date 2018/11/16.
 */
public class AuAuthenticationEntryPoint extends OAuth2AuthenticationEntryPoint {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    private WebResponseExceptionTranslator exceptionTranslator = new DefaultWebResponseExceptionTranslator();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) {
        try {
            //解析异常，如果是401则处理
            ResponseEntity<OAuth2Exception> result = exceptionTranslator.translate(authException);
            if (result.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                logger.error("客户端错误：{}", Objects.requireNonNull(result.getBody()).getSummary());
                // 返回指定格式的错误信息
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
                RestResult restResult = RestResult.getResult(ResultCode.FAIL.getCode(), ResultCode.FAIL.getDesc());
                response.getWriter().write(StringUtils.defaultString(JSONUtils.writeValueAsString(restResult)));
                response.getWriter().flush();
            } else {
                //如果不是401异常，则以默认的方法继续处理其他异常
                super.commence(request, response, authException);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
