package com.qslion.web.accounting.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qslion.framework.enums.IEnum;

import java.util.*;

/**
 * 借贷方向
 *
 * @author Gray.Z
 * @date 2019/8/24 16:23.
 */
public enum AccountDir implements IEnum<Integer> {

    DEBIT(0, "借方"),
    CREDIT(1, "贷方");

    private Integer id;
    private String name;

    AccountDir(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Optional<AccountDir> get(Integer id) {
        return Arrays.stream(AccountDir.values()).filter(bd -> Objects.equals(bd.getId(), id)).findFirst();
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
        for (AccountDir balanceDir : AccountDir.values()) {
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
