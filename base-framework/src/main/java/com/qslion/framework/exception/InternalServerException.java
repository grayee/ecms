package com.qslion.framework.exception;

import com.qslion.framework.enums.ResultCode;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2018/9/22.
 */
public class InternalServerException extends BusinessException {

    public InternalServerException(ResultCode resultCode) {
        super(resultCode);
    }

    public InternalServerException(ResultCode resultCode, Throwable e) {
        super(resultCode, e);
    }

    public InternalServerException(ResultCode resultCode, String msg) {
        super(resultCode, msg);
    }
}
