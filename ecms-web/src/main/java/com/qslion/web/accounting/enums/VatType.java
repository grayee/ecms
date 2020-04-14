package com.qslion.web.accounting.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qslion.framework.enums.IEnum;

import java.util.*;

/**
 * 增值税类型 value add tax
 *
 * @author Gray.Z
 * @date 2019/8/24 16:23.
 */
public enum VatType implements IEnum<Integer> {

    SMALL(0, "小规模纳税人"),
    COMMON(1, "一般纳税人");

    private Integer id;
    private String name;

    VatType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Optional<VatType> get(Integer id) {
        return Arrays.stream(VatType.values()).filter(bd -> Objects.equals(bd.getId(), id)).findFirst();
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
        for (VatType balanceDir : VatType.values()) {
            Map<String, Object> sMap = Maps.newHashMapWithExpectedSize(2);
            sMap.put("value", balanceDir.getId());
            sMap.put("text", balanceDir.getName());
            sType.add(sMap);
        }
        return sType;
    }
    @Override
    public String getDisplayName() {
        return name;
    }

    @Override
    public String getIdKey() {
        return "vatTypeId";
    }
}
