package com.qslion.framework.bean;

import com.qslion.framework.enums.ExceptionEnum;
import com.qslion.framework.enums.ResultCode;
import com.qslion.framework.exception.BusinessException;
import com.qslion.framework.util.RequestContextHolderUtil;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;

/**
 * 默认全局错误返回结果
 * {@link org.springframework.boot.web.reactive.error.DefaultErrorAttributes}
 *
 * @author Gray.Z
 * @date 2018/9/22.
 */
public class ErrorResult extends RestResult {

    /**
     * HTTP响应状态码 {@link org.springframework.http.HttpStatus}
     */
    private Integer status;

    /**
     * HTTP响应状态码的英文提示
     */
    private String error;

    /**
     * 调用接口路径
     */
    private String path;

    /**
     * 异常的名字
     */
    private String exception;

    /**
     * 异常的错误传递的数据
     */
    private Object errors;

    /**
     * 时间戳
     */
    private Date timestamp;

    public static ErrorResult failure(ResultCode resultCode, Throwable e, HttpStatus httpStatus, Object errors) {
        ErrorResult result = ErrorResult.failure(resultCode, e, httpStatus);
        result.setErrors(errors);
        return result;
    }

    public static ErrorResult failure(ResultCode resultCode, Throwable e, HttpStatus httpStatus) {
        ErrorResult result = new ErrorResult();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMsg());

        result.setStatus(httpStatus.value());
        result.setError(httpStatus.getReasonPhrase());
        result.setException(e.getClass().getName());
        result.setPath(RequestContextHolderUtil.getRequest().getRequestURI());
        result.setTimestamp(new Date());
        return result;
    }

    public static ErrorResult failure(BusinessException e) {
        ExceptionEnum ee = ExceptionEnum.getByEClass(e.getClass());
        if (ee != null) {
            return ErrorResult.failure(ee.getResultCode(), e, ee.getHttpStatus(), e.getData());
        }

        ErrorResult errorResult = ErrorResult.failure(e.getResultCode() == null ? ResultCode.SUCCESS :
            e.getResultCode(), e, HttpStatus.OK, e.getData());

        if (StringUtils.isNotEmpty(e.getMessage())) {
            errorResult.setMessage(e.getMessage());
        }
        return errorResult;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
