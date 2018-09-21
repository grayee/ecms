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

    private static final String CODE = "code";
    private static final String MESSAGE = "message";

    private static RestResult getRestResult(int code, String msg) {
        RestResult result = new RestResult();
        result.put(CODE, code);
        result.put(MESSAGE, msg);
        return result;
    }

    public static RestResult success() {
        RestResult result = getRestResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getDesc());
        return result;
    }

    public static <T> RestResult success(Pager<T> pager) {
        RestResult result = success();
        result.put("page", pager);
        return result;
    }

    public static RestResult fail(ResultCode resultCode, String msg) {
        return getRestResult(resultCode.getCode(), String.format("%s:%s", resultCode.getDesc(), msg));
    }

    public static RestResult get(ResultCode resultCode) {
        return getRestResult(resultCode.getCode(), resultCode.getDesc());
    }
}
