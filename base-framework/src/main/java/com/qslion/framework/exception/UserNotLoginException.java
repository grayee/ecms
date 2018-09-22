package com.qslion.framework.exception;

import com.qslion.framework.enums.ResultCode;

/**
 * 用户未登录
 *
 * @author Gray.Z
 * @date 2018/9/22.
 */
public class UserNotLoginException extends BusinessException {

    public UserNotLoginException(ResultCode resultCode) {
        super(resultCode);
    }

    public UserNotLoginException(ResultCode resultCode, Throwable e) {
        super(resultCode, e);
    }

    public UserNotLoginException(ResultCode resultCode, String msg) {
        super(resultCode, msg);
    }
}
