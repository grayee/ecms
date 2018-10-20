package com.qslion.security;

import com.qslion.framework.util.JSONUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * 自定义登录成功处理类,登录成功后跳转
 *
 * @author Gray.Z
 * @date 2018/10/20 10:23.
 */
public class AuAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final Logger logger = LogManager.getLogger();

    @Value("${login.type}")
    private String loginType;

    public AuAuthenticationSuccessHandler(String defaultSuccessUrl) {
        super.setDefaultTargetUrl(defaultSuccessUrl);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException, ServletException {
        logger.info("登录成功...");
        //判断是json 格式返回 还是 view 格式返回
        if ("json".equals(loginType)) {
            //将authention 信息打包成json格式返回
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(JSONUtils.writeValueAsString(authentication));
        } else {
            //返回view
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }

}
