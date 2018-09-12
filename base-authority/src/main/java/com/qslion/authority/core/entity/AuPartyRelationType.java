package com.qslion.authority.core.entity;

public enum AuPartyRelationType {

    ADMINISTRATIVE(1, "au_administrative_relation", "行政关系"),
    ROLE(2, "au_role_relation", "角色关系"),
    PROXY(3, "au_proxy_relation", "代理关系");


    private int id;
    private String name;
    private String value;

    AuPartyRelationType(int id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public int getId() {
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
}