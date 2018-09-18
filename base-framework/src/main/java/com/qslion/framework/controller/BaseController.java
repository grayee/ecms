
package com.qslion.framework.controller;

import com.google.common.net.HttpHeaders;
import com.qslion.framework.util.JxlsUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Controller 基类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
public class BaseController<T, ID>  /*extends BasicErrorController */{

    protected final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * 错误消息
     */
    protected static final String ERROR_MESSAGE = "common.message.error";

    /**
     * 成功消息
     */
    protected static final String SUCCESS_MESSAGE = "common.message.success";


    /**
     * 导出EXCEL
     *
     * @param dataModel 数据
     * @param fileName 导出文件名称
     * @param templatePath 模板路径
     */
    protected void exportToExcel(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataModel, String fileName,
        String templatePath) {
        try (OutputStream os = response.getOutputStream()) {
            setResponseParam(response, getLocalFileName(fileName, request.getHeader("User-Agent")));
            InputStream is = getClass().getClassLoader().getResourceAsStream(templatePath);
            JxlsUtils.exportExcel(is, os, dataModel);
        } catch (FileNotFoundException e) {
            logger.error("export excel happened file not fund exception", e);
        } catch (IOException e) {
            logger.error("export excel happened IO exception", e);
        } catch (Exception e) {
            logger.error("export excel happened exception", e);
        }
    }

    private void setResponseParam(HttpServletResponse response, String fileName) {
        response.setCharacterEncoding(Charset.forName("UTF-8").name());
        response.setContentType("application/vnd.ms-excel");
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
    }

    private String getLocalFileName(String fileName, String userAgent) throws UnsupportedEncodingException {
        if (userAgent.toUpperCase().contains("MSIE") || userAgent.contains("Trident/7.0")) {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } else if (userAgent.toUpperCase().contains("MOZILLA") ||
            userAgent.toUpperCase().contains("CHROME")) {
            fileName = new String(fileName.getBytes(), "ISO-8859-1");
        } else {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        }
        return fileName;
    }

/*
    *//**
     * 覆盖默认的Json响应
     *//*
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request,
            isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);

        //输出自定义的Json格式
        Map<String, Object> map = new HashMap<>(2);
        map.put("code", status.value());
        map.put("msg", body.get("message"));

        return new ResponseEntity<>(map, status);
    }

    *//**
     * 覆盖默认的HTML响应
     *//*
    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        //请求的状态
        HttpStatus status = getStatus(request);
        response.setStatus(getStatus(request).value());

        Map<String, Object> model = getErrorAttributes(request,
            isIncludeStackTrace(request, MediaType.TEXT_HTML));
        ModelAndView modelAndView = resolveErrorView(request, response, status, model);
        //指定自定义的视图
        return (modelAndView == null ? new ModelAndView("error", model) : modelAndView);
    }*/

}
