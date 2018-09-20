package com.qslion.framework.validator;

import com.qslion.framework.enums.ResultCode;
import com.qslion.framework.exception.HandleException;
import org.apache.commons.lang.StringUtils;

/**
 * 断言校验
 *
 * @author Gray.Z
 * @date 2018/4/14 10:13.
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new HandleException(ResultCode.PARAMETER_ERROR, message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new HandleException(ResultCode.PARAMETER_ERROR, message);
        }
    }
}
