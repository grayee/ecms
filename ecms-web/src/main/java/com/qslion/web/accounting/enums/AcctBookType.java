package com.qslion.web.accounting.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.qslion.framework.enums.IEnum;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;


/**
 * 账页格式
 *
 * @author Gray.Z
 * @date 2019/8/26 21:09.
 */
public enum AcctBookType implements IEnum<Integer> {

    MULTI_COLUMN(1, "多栏式"),
    THREE_COLUMN(2, "三栏式"),
    AMOUNT(3, "数量金额式");

    private Integer id;
    private String name;

    AcctBookType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Optional<AcctBookType> get(Integer id) {
        return Arrays.stream(AcctBookType.values()).filter(bt -> Objects.equals(bt.getId(), id)).findFirst();
    }

    @Override
    @JsonValue
    public Integer getId() {
        return id;
    }

    @Override
    public String getDisplayName() {
        return name;
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
    public String getIdKey() {
        return "bookTypeId";
    }

}
