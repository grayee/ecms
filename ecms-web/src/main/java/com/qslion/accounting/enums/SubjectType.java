package com.qslion.accounting.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.qslion.framework.enums.IEnum;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2019/8/24 13:58.
 */
public enum SubjectType implements IEnum<Integer> {
    ASSETS(1, "资产类"),
    DEBTS(2, "负债类"),
    EQUITY(3, "权益类"),
    COMMON(4, "共同类"),
    COSTS(5, "成本类"),
    GAINS(6, "损益类");

    private Integer id;
    private String name;

    SubjectType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Optional<SubjectType> get(Integer id) {
        return Arrays.stream(SubjectType.values()).filter(st -> Objects.equals(st.getId(), id)).findFirst();
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
