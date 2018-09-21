package com.qslion.framework.exception;

import com.qslion.framework.bean.RestResult;
import com.qslion.framework.enums.ResultCode;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
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
public class GlobalExceptionHandler {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * 服务器异常
     *
     * @param ex Exception
     * @return error message
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResult handlerException(Exception ex) {
        logger.error("GlobalExceptionHandler Exception:{}", ex.getMessage());
        return RestResult.fail(ResultCode.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    /**
     * 运行时异常
     *
     * @param ex RuntimeException
     * @return error message
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResult handlerRuntimeException(RuntimeException ex) {
        logger.error("GlobalExceptionHandler RuntimeException:{}", ex.getMessage());
        return RestResult.fail(ResultCode.FAIL, ex.getMessage());
    }

    /**
     * 自定义异常
     *
     * @param ex HandleException
     * @return error message
     */
    @ExceptionHandler(HandleException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResult handlerException(HandleException ex) {
        logger.error("GlobalExceptionHandler HandleException:{}", ex.getMessage());
        return RestResult.fail(ex.getResultCode(), ex.getMessage());
    }

    /**
     * 参数检验违反约束（数据校验@Validated）
     *
     * @param ex BindException
     * @return error message
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public RestResult handleConstraintViolationException(BindException ex) {
        logger.error("GlobalExceptionHandler BindException:{}", ex.getMessage());
        return RestResult.fail(ResultCode.PARAMETER_ERROR, ex.getBindingResult().getAllErrors()
            .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(","))
        );
    }

    /**
     * 参数检验违反约束（数据校验@Valid）
     *
     * @param ex BindException
     * @return error message
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public RestResult handleException(MethodArgumentNotValidException ex) {
        logger.error("GlobalExceptionHandler MethodArgumentNotValidException:{}", ex.getMessage());
        return RestResult.fail(ResultCode.PARAMETER_ERROR, ex.getBindingResult().getAllErrors()
            .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(",")));
    }
}
