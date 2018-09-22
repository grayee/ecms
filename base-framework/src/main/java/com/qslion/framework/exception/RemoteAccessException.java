package com.qslion.framework.exception;

import com.qslion.framework.enums.ResultCode;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2018/9/22.
 */
public class RemoteAccessException extends BusinessException {

    public RemoteAccessException(ResultCode resultCode) {
        super(resultCode);
    }

    public RemoteAccessException(ResultCode resultCode, Throwable e) {
        super(resultCode, e);
    }

    public RemoteAccessException(ResultCode resultCode, String msg) {
        super(resultCode, msg);
    }
}
