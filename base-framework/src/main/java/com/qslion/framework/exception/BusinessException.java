package com.qslion.framework.exception;

import com.qslion.framework.enums.ResultCode;

/**
 * 业务异常
 *
 * @author Gray.Z
 * @date 2018/9/22.
 */
public class BusinessException extends HandleException {

    private String code;
    private String message;
    /**
     * 异常抛出时，向上传递一些附加信息
     */
    private Object data;

    public BusinessException(ResultCode resultCode) {
        super(resultCode);
        code = String.valueOf(resultCode.getCode());
        message = resultCode.getDesc();
    }

    public BusinessException(ResultCode resultCode, Throwable e) {
        super(resultCode, e);
        code = String.valueOf(resultCode.getCode());
        message = resultCode.getDesc();
    }

    public BusinessException(ResultCode resultCode, String args) {
        super(resultCode, args);
        code = String.valueOf(resultCode.getCode());
        message = resultCode.getDesc();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
