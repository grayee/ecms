package com.qslion.exception;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
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

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Map<String, Object> handlerException(Exception ex) {
    Map<String, Object> result = new HashMap<>(2);
    result.put("msg", ex.getMessage());
    result.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
    return result;
  }

  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Map<String, Object> handlerRuntimeException(RuntimeException ex) {
    Map<String, Object> result = new HashMap<>(2);
    result.put("msg", ex.getMessage());
    result.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
    return result;
  }

}
