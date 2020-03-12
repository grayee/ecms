package com.qslion.authority.core.enums;


import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import com.qslion.framework.enums.IEnum;
import com.qslion.framework.util.Localize;

import java.util.List;
import java.util.Set;

/**
 * 组织类型
 *
 * @author Gray.Z
 * @date 2018/5/1 19:45.
 */
public enum AuOrgType implements IEnum<Integer> {
    /**
     * 公司
     */
    COMPANY(1, "org_type_company", "org_type_category_organization"),
    /**
     * 部门
     */
    DEPARTMENT(2, "org_type_department", "org_type_category_organization"),
    /**
     * 岗位
     */
    POSITION(3, "org_type_position", "org_type_category_organization"),
    /**
     * 员工
     */
    EMPLOYEE(4, "org_type_employee", "org_type_category_person"),
    /**
     * 角色
     */
    ROLE(5, "org_type_role", "org_type_category_role"),
    /**
     * 代理
     */
    PROXY(6, "org_type_proxy", "org_type_category_other"),;

    private int id;
    private String name;
    private String category;

    AuOrgType(int id, String name, String category) {
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

    public static List<AuOrgType> getAllValues() {
        return Lists.newArrayList(AuOrgType.values());
    }

    public static Set<String> getCategories() {
        Set<String> categories = Sets.newHashSet();
        for (AuOrgType auOrgType : AuOrgType.values()) {
            categories.add(auOrgType.getCategory());
        }
        return categories;
    }

    public static AuOrgType getOrgType(int id) {
        for (AuOrgType auOrgType : AuOrgType.values()) {
            if (auOrgType.getId() == id) {
                return auOrgType;
            }
        }
        return null;
    }
    @Override
    public String getDisplayName() {
        return Localize.getMessage(name);
    }
}