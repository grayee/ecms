package com.qslion.authority.core.entity;

import com.qslion.authority.core.enums.AuOrgType;
import com.qslion.authority.core.enums.AuOrgRelationType;
import com.qslion.framework.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * 连接规则
 *
 * @author Gray.Z
 * @date 2018/5/1 19:45.
 */
@Entity
@Table(name = "au_connection_rule")
public class AuConnectionRule extends BaseEntity<Long> {

    private AuOrgType curOrgType;
    private AuOrgType subOrgType;
    private AuOrgRelationType orgRelationType;
    private String name;
    private String remark;

    @Enumerated
    @Column(name = "cur_org_type")
    public AuOrgType getCurOrgType() {
        return this.curOrgType;
    }

    public void setCurOrgType(AuOrgType curOrgType) {
        this.curOrgType = curOrgType;
    }

    @Enumerated
    @Column(name = "sub_org_type")
    public AuOrgType getSubOrgType() {
        return this.subOrgType;
    }

    public void setSubOrgType(AuOrgType subOrgType) {
        this.subOrgType = subOrgType;
    }

    @Enumerated
    @Column(name = "relation_type")
    public AuOrgRelationType getOrgRelationType() {
        return this.orgRelationType;
    }

    public void setOrgRelationType(AuOrgRelationType orgRelationType) {
        this.orgRelationType = orgRelationType;
    }

    @Column(name = "name", nullable = false, length = 64)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "remark")
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}