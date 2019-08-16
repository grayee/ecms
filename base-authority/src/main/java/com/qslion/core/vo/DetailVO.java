package com.qslion.core.vo;


import java.util.Map;

/**
 * 通用明细VO
 *
 * @author Gray.Z
 * @date 2018/4/3 20:25.
 */
public class DetailVO<T> {

    private T content;

    /**
     * 自定义数据
     */
    private Map<String, Object> extras;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public Map<String, Object> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, Object> extras) {
        this.extras = extras;
    }
}
