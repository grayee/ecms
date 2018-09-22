package com.qslion.framework.exception;

import com.qslion.framework.enums.ResultCode;

/**
 * 数据未找到异常
 *
 * @author Gray.Z
 * @date 2018/9/22.
 */
public class DataNotFoundException extends BusinessException {

    public DataNotFoundException(ResultCode resultCode) {
        super(resultCode);
    }

    public DataNotFoundException(ResultCode resultCode, Throwable e) {
        super(resultCode, e);
    }

    public DataNotFoundException(ResultCode resultCode, String msg) {
        super(resultCode, msg);
    }
}
