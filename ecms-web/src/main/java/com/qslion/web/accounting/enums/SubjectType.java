package com.qslion.web.accounting.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qslion.framework.enums.IEnum;

import java.util.*;

/**
 * 科目分类
 *
 * @author Gray.Z
 * @date 2019/8/24 13:58.
 */
public enum SubjectType implements IEnum<Integer> {
    ASSETS(0, "资产类"),
    DEBTS(1, "负债类"),
    COMMON(2, "共同类"),
    EQUITY(3, "所有者权益类"),
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

    public static List<Map<String, Object>> getMapList() {
        List<Map<String, Object>> sType = Lists.newArrayList();
        for (SubjectType subjectType : SubjectType.values()) {
            Map<String, Object> sMap = Maps.newHashMapWithExpectedSize(2);
            sMap.put("value", subjectType.getId());
            sMap.put("text", subjectType.getName());
            sType.add(sMap);
        }
        return sType;
    }
    @Override
    public String getDisplayName() {
        return name;
    }
}
