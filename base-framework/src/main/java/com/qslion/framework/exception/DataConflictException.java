package com.qslion.framework.exception;

import com.qslion.framework.enums.ResultCode;

/**
 * 数据冲突异常
 *
 * @author Gray.Z
 * @date 2018/9/22.
 */
public class DataConflictException extends BusinessException {

    public DataConflictException(ResultCode resultCode) {
        super(resultCode);
    }

    public DataConflictException(ResultCode resultCode, Throwable e) {
        super(resultCode, e);
    }

    public DataConflictException(ResultCode resultCode, String msg) {
        super(resultCode, msg);
    }
}
