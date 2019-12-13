package com.qslion.web.accounting.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qslion.framework.enums.IEnum;

import java.util.*;

/**
 * 公认会计原则类型 generally accepted accounting principle
 *
 * @author Gray.Z
 * @date 2019/8/24 16:23.
 */
public enum GaapType implements IEnum<Integer> {

    SMALL(0, "小企业会计准则"),
    COMMON(1, "一般企业会计准则"),
    NON_GOV_ORG(2, "民间非营利组织会计制度");

    private Integer id;
    private String name;

    GaapType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Optional<GaapType> get(Integer id) {
        return Arrays.stream(GaapType.values()).filter(bd -> Objects.equals(bd.getId(), id)).findFirst();
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
        for (GaapType balanceDir : GaapType.values()) {
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
}
