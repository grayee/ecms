package com.qslion.authority.core.entity;

import com.qslion.authority.core.enums.AuOrgType;

import javax.persistence.Transient;

/**
 * 组织机构接口
 * Created by Administrator
 * on 2020/3/12.
 */
public interface IOrg {

    /**
     * 组织ID
     *
     * @return OrgId
     */
    @Transient
    Long getOrgId();

    /**
     * 组织类型
     *
     * @return AuOrgType
     */
    @Transient
    AuOrgType getOrgType();

    /**
     * 组织名称
     *
     * @return orgName
     */
    @Transient
    String getOrgName();

    /**
     * 上级关系ID
     *
     * @return ParentRelId
     */
    Long getParentRelId();

    /**
     * 其他信息
     *
     * @return remark
     */
    String getRemark();
}
