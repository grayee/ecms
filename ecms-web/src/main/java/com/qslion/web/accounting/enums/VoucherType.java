package com.qslion.web.accounting.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qslion.framework.enums.IEnum;

import java.util.*;

/**
 * 凭证类型
 *
 * @author Gray.Z
 * @date 2019/8/24 16:23.
 */
public enum VoucherType implements IEnum<Integer> {

    ACCOUNTING(0, "记"),
    RECEIVE(1, "收"),
    PAY(0, "付"),
    TRANSFER(1, "转"),;


    private Integer id;
    private String name;

    VoucherType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Optional<VoucherType> get(Integer id) {
        return Arrays.stream(VoucherType.values()).filter(bd -> Objects.equals(bd.getId(), id)).findFirst();
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
        for (VoucherType balanceDir : VoucherType.values()) {
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
        return "voucherTypeId";
    }
}
