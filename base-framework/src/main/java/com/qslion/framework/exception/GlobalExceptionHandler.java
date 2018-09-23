package com.qslion.framework.exception;

import com.qslion.framework.bean.ErrorResult;
import com.qslion.framework.bean.RestResult;
import com.qslion.framework.enums.ResultCode;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controller级全局异常处理
 *
 * @author Gray.Z
 * @date 2018/9/18.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends BaseGlobalExceptionHandler {


    /**
     * 违反约束异常
     *
     * @param ex ConstraintViolationException
     * @return error message
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResult handlerConstraintViolationException(ConstraintViolationException ex) {
        return handleConstraintViolationException(ex);
    }


    /**
     * 参数封装错误异常
     *
     * @param ex HttpMessageNotReadableException
     * @return error message
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResult handlerHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return handleHttpMessageNotReadableException(ex);
    }

    /**
     * 自定义业务异常
     *
     * @param ex BusinessException
     * @return error message
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResult> handlerBusinessException(BusinessException ex) {
        return handleBusinessException(ex);
    }

    /**
     * 运行时异常
     *
     * @param ex RuntimeException
     * @return error message
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResult handlerRuntimeException(RuntimeException ex) {
        return handleRuntimeException(ex);
    }

    /**
     * 参数检验违反约束（数据校验@Validated）
     *
     * @param ex BindException
     * @return error message
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public RestResult handlerBindException(BindException ex) {
        return handleBindException(ex);
    }

    /**
     * 参数检验违反约束（数据校验@Valid）
     *
     * @param ex BindException
     * @return error message
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResult handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return handleMethodArgumentNotValidException(ex);
    }

    /**
     * 自定义异常
     *
     * @param ex HandleException
     * @return error message
     */
    @ExceptionHandler(HandleException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResult handlerException(HandleException ex) {
        logger.error("GlobalExceptionHandler handle HandleException:{}", ex);
        return ErrorResult.failure(ResultCode.INTERNAL_SERVER_ERROR, ex, HttpStatus.INTERNAL_SERVER_ERROR,
            ex.getMessage());
    }

    /**
     * 服务器异常
     *
     * @param ex Exception
     * @return error message
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResult handlerException(Exception ex) {
        logger.error("GlobalExceptionHandler handle Exception:{}", ex);
        return ErrorResult.failure(ResultCode.INTERNAL_SERVER_ERROR, ex, HttpStatus.INTERNAL_SERVER_ERROR,
            ex.getMessage());
    }
}
