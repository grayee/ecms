package com.qslion.framework.exception;

import com.qslion.framework.bean.RestResult;
import com.qslion.framework.enums.ResultCode;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    public ResponseEntity<RestResult> handlerException(Exception ex) {
        logger.error("Exception:{}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(RestResult.fail(ResultCode.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }

    /**
     * 运行时异常
     *
     * @param ex RuntimeException
     * @return error message
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<RestResult> handlerRuntimeException(RuntimeException ex) {
        logger.error("RuntimeException:{}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RestResult.fail(ResultCode.FAIL, ex.getMessage()));
    }

    /**
     * 自定义异常
     *
     * @param ex HandleException
     * @return error message
     */
    @ExceptionHandler(HandleException.class)
    public ResponseEntity<RestResult> handlerException(HandleException ex) {
        logger.error("HandleException:{}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RestResult.fail(ex.getResultCode(), ex.getMessage()));
    }

    /**
     * 参数检验违反约束（数据校验）
     *
     * @param ex BindException
     * @return error message
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<RestResult> handleConstraintViolationException(BindException ex) {
        logger.debug(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RestResult.fail(ResultCode.PARAMETER_ERROR,
            ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(",")))
        );
    }
}
