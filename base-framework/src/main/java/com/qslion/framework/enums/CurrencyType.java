package com.qslion.framework.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;

/**
 * 货币种类
 *
 * @author Gray.Z
 * @date 2018/9/20.
 */
public enum CurrencyType implements IEnum<Integer> {
    CNY(0, "人民币"),
    USD(1, "美元"),
    EUR(2, "欧元"),
    GBP(3, "英磅"),
    CAD(4, "加拿大元"),
    AUD(5, "澳元"),
    RUB(6, "卢布"),
    HKD(7, "港币"),
    TWD(8, "新台币"),
    KRW(9, "韩元"),
    SGD(10, "新加坡元"),
    NZD(11, "新西兰元"),
    JPY(12, "日元"),
    MYR(13, "马元"),
    CHF(14, "瑞士法郎"),
    SEK(15, "瑞典克朗"),
    DKK(16, "丹麦克朗"),
    PLZ(17, "兹罗提"),
    NOK(18, "挪威克朗"),
    HUF(19, "福林"),
    CSK(20, "捷克克朗"),
    MOP(21, "葡币");
    private Integer id;
    private String name;

    CurrencyType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Optional<CurrencyType> get(Integer id) {
        return Arrays.stream(CurrencyType.values()).filter(menuStatus -> Objects.equals(menuStatus.getId(), id)).findFirst();
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
        for (CurrencyType currencyType : CurrencyType.values()) {
            Map<String, Object> sMap = Maps.newHashMapWithExpectedSize(2);
            sMap.put("value", currencyType.getId());
            sMap.put("text", currencyType.getName());
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
        return "currencyTypeId";
    }
}
