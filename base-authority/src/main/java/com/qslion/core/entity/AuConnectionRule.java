package com.qslion.core.entity;

import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.core.enums.AuPartyType;
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

    private AuPartyType parentPartyType;
    private AuPartyType childPartyType;
    private AuPartyRelationType auPartyRelationType;
    private String name;
    private String remark;
    private Short level;

    @Enumerated
    @Column(name = "parent_party_type")
    public AuPartyType getParentPartyType() {
        return this.parentPartyType;
    }

    public void setParentPartyType(AuPartyType auPartyTypeByParentPartyTypeId) {
        this.parentPartyType = auPartyTypeByParentPartyTypeId;
    }

    @Enumerated
    @Column(name = "child_party_type")
    public AuPartyType getChildPartyType() {
        return this.childPartyType;
    }

    public void setChildPartyType(AuPartyType auPartyTypeByChildPartyTypeId) {
        this.childPartyType = auPartyTypeByChildPartyTypeId;
    }

    @Enumerated
    @Column(name = "relation_type")
    public AuPartyRelationType getAuPartyRelationType() {
        return this.auPartyRelationType;
    }

    public void setAuPartyRelationType(AuPartyRelationType auPartyRelationType) {
        this.auPartyRelationType = auPartyRelationType;
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

    @Column(name = "level")
    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }
}