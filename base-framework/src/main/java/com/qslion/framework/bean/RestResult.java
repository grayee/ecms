package com.qslion.framework.bean;

import com.qslion.framework.enums.ResultCode;
import java.io.Serializable;

/**
 * REST结果对象
 *
 * @author Gray.Z
 * @date 2018/9/20.
 */
public class RestResult implements Serializable {


    /**
     * 内部自定义的返回值编码，{@link ResultCode} 它是对错误更加详细的编码
     */
    private Integer code;

    /**
     * 异常堆栈的精简信息
     */
    private String message;

    private Object data;

    public static RestResult getResult(int code, String msg) {
        RestResult result = new RestResult();
        result.code = code;
        result.message = msg;
        return result;
    }

    public static RestResult success() {
        return getResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getDesc());
    }

    public static RestResult success(Object data) {
        RestResult result = success();
        result.setData(data);
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
