package com.qslion.security;

import com.qslion.framework.util.JSONUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * 定义登录失败处理器
 *
 * @author Gray.Z
 * @date 2018/10/20 10:24.
 */
public class AuAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private static final Logger logger = LogManager.getLogger();

    @Value("${login.type}")
    private String loginType;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
        throws IOException, ServletException {
        logger.info("登录失败...");

        //如果是json 格式
        if ("json".equals(loginType)) {
            //设置状态码
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            //将 登录失败 信息打包成json格式返回
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(JSONUtils.writeValueAsString(e));
        } else {
            //如果不是json格式，返回view
            super.onAuthenticationFailure(request, response, e);
        }
    }
}
