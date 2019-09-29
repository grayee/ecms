package com.qslion.authority.security;

import com.qslion.framework.bean.RestResult;
import com.qslion.framework.enums.ResultCode;
import com.qslion.framework.util.JSONUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * 权限拒绝异常处理器
 *
 * @author Gray.Z
 * @date 2018/11/28.
 */
public class AuAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response,
        AccessDeniedException e) throws IOException, ServletException {
        // 返回指定格式的错误信息
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        RestResult restResult = RestResult.getResult(ResultCode.PERMISSION_NO_ACCESS.getCode(),
            ResultCode.PERMISSION_NO_ACCESS.getDesc());
        response.getWriter().write(StringUtils.defaultString(JSONUtils.writeValueAsString(restResult)));
        response.getWriter().flush();
    }
}
