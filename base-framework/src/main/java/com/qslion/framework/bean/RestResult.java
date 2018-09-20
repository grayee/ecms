package com.qslion.framework.bean;

import com.qslion.framework.enums.ResultCode;
import java.util.LinkedHashMap;

/**
 * 结果对象
 *
 * @author Gray.Z
 * @date 2018/9/20.
 */
public class RestResult extends LinkedHashMap<String, Object> {

  public static <T> RestResult success(Pager<T> pager) {
    RestResult result = new RestResult();
    result.put("code", ResultCode.SUCCESS.getCode());
    result.put("msg", ResultCode.SUCCESS.getDesc());
    result.put("page", pager);
    return result;
  }

  public static RestResult success() {
    RestResult result = new RestResult();
    result.put("code", ResultCode.SUCCESS.getCode());
    result.put("msg", ResultCode.SUCCESS.getDesc());
    return result;
  }

  public static RestResult fail(ResultCode resultCode) {
    RestResult result = new RestResult();
    result.put("code", resultCode.getCode());
    result.put("msg", resultCode.getDesc());
    return result;
  }
}
