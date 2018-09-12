package com.qslion.framework.util;

import javax.servlet.http.HttpServletRequest;

public class ParameterUtil {

    public static String getParameter(HttpServletRequest request, String parameterName) {
        return request.getParameter(parameterName) == null ? "" : request.getParameter(parameterName);
    }

    public static String getParameter(HttpServletRequest request, String parameterName, String defaultValue) {
        return request.getParameter(parameterName) == null ? defaultValue : request.getParameter(parameterName);
    }

}
