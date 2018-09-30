package com.qslion.framework.controller;

import com.qslion.framework.util.JSONUtils;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异常控制类
 *
 * @author Gray.Z
 * @date 2018/9/19.
 */
@Controller
public class BaseErrorController extends BasicErrorController {


    protected final Logger logger = LogManager.getLogger(this.getClass());

    public BaseErrorController(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }

    /**
     * 覆盖默认的Json响应
     *
     * @param request 请求对象
     * @return 异常结果描述
     */
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request,
            isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);

        //输出自定义的Json格式
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("status", status.value());
        map.put("message", body.get("message"));

        logger.error("error info:{}", JSONUtils.writeValueAsString(map));
        return new ResponseEntity<>(map, status);
    }


    /**
     * 覆盖默认的HTML响应
     *
     * @param request 请求对象
     * @param response 响应对象
     * @return 页面视图
     */
    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        //请求的状态
        HttpStatus status = getStatus(request);
        response.setStatus(getStatus(request).value());

        Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(request,
            isIncludeStackTrace(request, MediaType.TEXT_HTML)));
        response.setStatus(status.value());
        ModelAndView modelAndView = resolveErrorView(request, response, status, model);
        //指定自定义的视图
        return modelAndView != null ? modelAndView : new ModelAndView("error", model);
    }
}
