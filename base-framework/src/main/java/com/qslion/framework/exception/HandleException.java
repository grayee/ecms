
package com.qslion.framework.exception;

import com.qslion.framework.enums.ResultCode;
import org.springframework.core.NestedRuntimeException;

/**
 * 自定义异常
 *
 * @author Gray.Z
 * @date 2018/9/18.
 */
public class HandleException extends NestedRuntimeException {

    private static final long serialVersionUID = 1L;

    private static final String DETAIL_MESSAGE_FORMAT = "%s:%s";
    private ResultCode resultCode;

    public HandleException(ResultCode resultCode) {
        super(String.format(DETAIL_MESSAGE_FORMAT, resultCode.getCode(), resultCode.getDesc()));
        this.resultCode = resultCode;
    }

    public HandleException(ResultCode resultCode, Throwable e) {
        super(String.format(DETAIL_MESSAGE_FORMAT, resultCode.getCode(), resultCode.getDesc()), e);
        this.resultCode = resultCode;
    }

    public HandleException(ResultCode resultCode, String args) {
        super(String.format(DETAIL_MESSAGE_FORMAT, resultCode.getCode(), resultCode.getDesc(args)));
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
