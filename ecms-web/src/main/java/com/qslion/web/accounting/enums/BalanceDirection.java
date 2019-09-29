package com.qslion.web.accounting.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.qslion.framework.enums.IEnum;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * 余额方向
 *
 * @author Gray.Z
 * @date 2019/8/24 16:23.
 */
public enum BalanceDirection implements IEnum<Integer> {

    DEBIT(0, "借方"),
    CREDIT(1, "贷方");

    private Integer id;
    private String name;

    BalanceDirection(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Optional<BalanceDirection> get(Integer id) {
        return Arrays.stream(BalanceDirection.values()).filter(bd -> Objects.equals(bd.getId(), id)).findFirst();
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
}
