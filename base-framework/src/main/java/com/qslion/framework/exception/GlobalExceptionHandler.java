package com.qslion.framework.exception;

import com.qslion.framework.bean.RestResult;
import com.qslion.framework.enums.ResultCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  @ExceptionHandler(Exception.class)
  public ResponseEntity<RestResult> handlerException(Exception ex) {
    logger.error(ex.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RestResult.fail(ResultCode.FAIL));
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<RestResult> handlerRuntimeException(RuntimeException ex) {
    logger.error(ex.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RestResult.fail(ResultCode.FAIL));
  }

  @ExceptionHandler(HandleException.class)
  public ResponseEntity<RestResult> handlerException(HandleException ex) {
    logger.error(ex.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RestResult.fail(ResultCode.FAIL));
  }

}
