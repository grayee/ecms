package com.qslion.web.accounting.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.qslion.framework.enums.IEnum;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * 科目分类
 *
 * @author Gray.Z
 * @date 2019/8/24 13:58.
 */
public enum SubjectType implements IEnum<Integer> {
    ASSETS(0, "资产类"),
    DEBTS(1, "负债类"),
    EQUITY(2, "权益类"),
    COMMON(3, "共同类"),
    COSTS(4, "成本类"),
    GAINS(5, "损益类");

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
