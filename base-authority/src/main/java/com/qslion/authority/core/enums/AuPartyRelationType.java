package com.qslion.authority.core.enums;

import com.qslion.framework.enums.IEnum;

/**
 * 团体关系类型
 * Accountability Type
 *
 * @author Gray.Z
 * @date 2018-09-16
 */
public enum AuPartyRelationType implements IEnum<Integer> {

    /**
     * 行政关系
     */
    ADMINISTRATIVE(1, "au_administrative_relation", "行政关系"),
    /**
     * 角色关系
     */
    ROLE(2, "au_role_relation", "角色关系"),
    /**
     * 代理关系
     */
    PROXY(3, "au_proxy_relation", "代理关系"),
    /**
     * 销售关系
     */
    SALE(4, "au_sale_relation", "销售关系");

    private int id;
    private String name;
    private String value;

    AuPartyRelationType(int id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public static AuPartyRelationType getPartyRelationType(int id) {
        for (AuPartyRelationType auPartyRelationType : AuPartyRelationType.values()) {
            if (auPartyRelationType.getId() == id) {
                return auPartyRelationType;
            }
        }
        return null;
    }
    @Override
    public String getDisplayName() {
        return value;
    }
}