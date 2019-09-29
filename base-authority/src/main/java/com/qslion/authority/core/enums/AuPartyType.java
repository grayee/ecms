package com.qslion.authority.core.enums;


import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import com.qslion.framework.enums.IEnum;
import com.qslion.framework.util.Localize;

import java.util.List;
import java.util.Set;

/**
 * 团体类型
 *
 * @author Gray.Z
 * @date 2018/5/1 19:45.
 */
public enum AuPartyType implements IEnum<Integer> {
    /**
     * 公司
     */
    COMPANY(1, "party_type_company", "party_type_category_organization"),
    /**
     * 部门
     */
    DEPARTMENT(2, "party_type_department", "party_type_category_organization"),
    /**
     * 岗位
     */
    POSITION(3, "party_type_position", "party_type_category_organization"),
    /**
     * 员工
     */
    EMPLOYEE(4, "party_type_employee", "party_type_category_person"),
    /**
     * 角色
     */
    ROLE(5, "party_type_role", "party_type_category_role"),
    /**
     * 代理
     */
    PROXY(6, "party_type_proxy", "party_type_category_other"),;

    private int id;
    private String name;
    private String category;

    AuPartyType(int id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getName() {
        return Localize.getMessage(name);
    }

    public String getCategory() {
        return Localize.getMessage(category);
    }

    public static List<AuPartyType> getAllValues() {
        return Lists.newArrayList(AuPartyType.values());
    }

    public static Set<String> getCategories() {
        Set<String> categories = Sets.newHashSet();
        for (AuPartyType auPartyType : AuPartyType.values()) {
            categories.add(auPartyType.getCategory());
        }
        return categories;
    }

    public static AuPartyType getPartyType(int id) {
        for (AuPartyType auPartyType : AuPartyType.values()) {
            if (auPartyType.getId() == id) {
                return auPartyType;
            }
        }
        return null;
    }
}