package com.qslion.authority.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qslion.framework.bean.DisplayField;
import com.qslion.framework.entity.BaseEntity;
import com.qslion.framework.enums.EnableStatus;

import javax.persistence.*;
import java.util.Date;

/**
 * 实体类 - 团体基础类
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@MappedSuperclass
public abstract class BaseOrg extends BaseEntity<Long> implements IOrg {

    private static final long serialVersionUID = 4656704236281853404L;

    @DisplayField(order = 4.20, title = "组织机构")
    protected Long parentId;

    protected String remark;
    protected EnableStatus enableStatus;
    protected Date enableDate;

    @Transient
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "enable_status", length = 1)
    @Enumerated
    public EnableStatus getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(EnableStatus enableStatus) {
        this.enableStatus = enableStatus;
    }

    @Basic
    @Column(name = "enable_date")
    public Date getEnableDate() {
        return enableDate;
    }

    public void setEnableDate(Date enableDate) {
        this.enableDate = enableDate;
    }

    @Basic
    @Column(name = "remark")
    @Override
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @JsonIgnore
    @Transient
    @Override
    public Long getOrgId() {
        return id;
    }

    @Transient
    @Override
    public Long getParentRelId() {
        return parentId;
    }
}
