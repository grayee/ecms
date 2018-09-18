
package com.qslion.framework.exception;

/**
 * 自定义异常
 *
 * @author Gray.Z
 * @date 2018/9/18.
 */
public class HandleException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private String msg;
  private int code;

  public HandleException(String msg) {
    super(msg);
    this.msg = msg;
  }

  public HandleException(String msg, Throwable e) {
    super(msg, e);
    this.msg = msg;
  }

  public HandleException(String msg, int code) {
    super(msg);
    this.msg = msg;
    this.code = code;
  }

  public HandleException(String msg, int code, Throwable e) {
    super(msg, e);
    this.msg = msg;
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }


}
