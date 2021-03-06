package com.qslion.framework.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * 启用状态
 *
 * @author Gray.Z
 * @date 2018/9/20.
 */
public enum EnableStatus implements IEnum<Integer> {

    /**
     * 禁用
     */
    DISABLE(0, "禁用"),
    /**
     * 启用
     */
    ENABLE(1, "启用"),;
    private Integer id;
    private String name;

    EnableStatus(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Optional<EnableStatus> get(Integer id) {
        return Arrays.stream(EnableStatus.values()).filter(menuStatus -> Objects.equals(menuStatus.getId(), id)).findFirst();
    }

    @Override
    @JsonValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDisplayName() {
        return name;
    }

    @Override
    public String getIdKey() {
        return "statusCode";
    }
}
