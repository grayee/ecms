package com.qslion.framework.exception;

import com.qslion.framework.enums.ResultCode;

/**
 * 参数无效异常
 *
 * @author Gray.Z
 * @date 2018/9/22.
 */
public class ParameterInvalidException extends BusinessException {


    public ParameterInvalidException(ResultCode resultCode) {
        super(resultCode);
    }

    public ParameterInvalidException(ResultCode resultCode, Throwable e) {
        super(resultCode, e);
    }

    public ParameterInvalidException(ResultCode resultCode, String msg) {
        super(resultCode, msg);
    }
}
