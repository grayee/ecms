package com.qslion.framework.enums;

import com.google.common.collect.Lists;
import com.qslion.framework.util.Localize;

/**
 * 响应码
 *
 * @author Gray.Z
 * @date 2018/9/20.
 */
public enum ResultCode {

  /**
   * 未知
   */
  UNKNOWN(-1, "result_unknown"),
  /**
   * 处理成功
   */
  SUCCESS(0, "result_success"),
  /**
   * 失败
   */
  FAIL(1, "result_fail"),
  /**
   * 未认证（签名错误）
   */
  UNAUTHORIZED(401, "result_unauthorized"),
  /**
   * 接口不存在
   */
  NOT_FOUND(404, "result_not_found"),
  /**
   * //服务器内部错误
   */
  INTERNAL_SERVER_ERROR(500, "result_internal_server_error");

  private final int code;

  private String msg;

  ResultCode(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public int getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }

  public String getDesc() {
    return Localize.getMessage(msg);
  }

  public static ResultCode getByCode(int code) {
    return Lists.newArrayList(ResultCode.values()).stream().filter(t -> t.getCode() == code).findFirst()
        .orElse(ResultCode.UNKNOWN);
  }
}
