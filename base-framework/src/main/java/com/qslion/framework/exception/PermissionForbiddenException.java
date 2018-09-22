package com.qslion.framework.exception;

import com.qslion.framework.enums.ResultCode;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2018/9/22.
 */
public class PermissionForbiddenException extends BusinessException {

    public PermissionForbiddenException(ResultCode resultCode) {
        super(resultCode);
    }

    public PermissionForbiddenException(ResultCode resultCode, Throwable e) {
        super(resultCode, e);
    }

    public PermissionForbiddenException(ResultCode resultCode, String msg) {
        super(resultCode, msg);
    }
}
