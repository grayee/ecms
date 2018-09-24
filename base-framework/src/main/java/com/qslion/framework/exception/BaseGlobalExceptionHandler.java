package com.qslion.framework.exception;

import com.qslion.framework.bean.ErrorResult;
import com.qslion.framework.enums.ResultCode;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 * 全局异常处理基础类
 *
 * @author Gray.Z
 * @date 2018/9/22.
 */
public class BaseGlobalExceptionHandler {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * 违反约束异常
     */
    protected ErrorResult handleConstraintViolationException(ConstraintViolationException e) {
        logger.error("GlobalExceptionHandler handle ConstraintViolationException , caused by: ", e);

        String errors = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
            .collect(Collectors.joining(","));

        return ErrorResult.failure(ResultCode.PARAMETER_IS_INVALID, e, HttpStatus.BAD_REQUEST, errors);
    }

    /**
     * 处理验证参数封装错误时异常
     */
    protected ErrorResult handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("GlobalExceptionHandler handle HttpMessageNotReadableException , caused by: ", e);
        return ErrorResult.failure(ResultCode.PARAMETER_IS_INVALID, e, HttpStatus.BAD_REQUEST);
    }

    /**
     * 处理参数绑定时异常（返400错误码）
     */
    protected ErrorResult handleBindException(BindException e) {
        logger.error("GlobalExceptionHandler handle BindException , caused by: ", e);

        String errors = e.getBindingResult().getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(","));

        return ErrorResult.failure(ResultCode.PARAMETER_IS_INVALID, e, HttpStatus.BAD_REQUEST, errors);
    }

    /**
     * 处理使用@Validated注解时，参数验证错误异常（返400错误码）
     */
    protected ErrorResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("GlobalExceptionHandler handle MethodArgumentNotValidException , caused by: ", e);

        List<FieldError> errors = e.getBindingResult().getFieldErrors().stream()
            .map(fieldError -> new FieldError(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());

        return ErrorResult.failure(ResultCode.PARAMETER_IS_INVALID, e, HttpStatus.BAD_REQUEST, errors);
    }

    /**
     * 处理通用自定义业务异常
     */
    protected ResponseEntity<ErrorResult> handleBusinessException(BusinessException e) {
        logger.info("GlobalExceptionHandler handle BusinessException , exception:{}, caused by: {}", e.getClass(), e);

        ErrorResult errorResult = ErrorResult.failure(e);
        return ResponseEntity.status(HttpStatus.valueOf(errorResult.getStatus())).body(errorResult);
    }

    /**
     * 处理运行时系统异常（反500错误码）
     */
    protected ErrorResult handleRuntimeException(RuntimeException e) {
        logger.error("GlobalExceptionHandler handle RuntimeException , caused by: ", e);
        //TODO 可通过邮件、微信公众号等方式发送信息至开发人员、记录存档等操作
        logger.info("start send email to sys admin....");
        return ErrorResult.failure(ResultCode.INTERNAL_SYSTEM__ERROR, e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public class FieldError {

        private String field;
        private String message;

        public FieldError(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}
