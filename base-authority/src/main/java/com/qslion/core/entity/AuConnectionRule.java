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

    private AuPartyType curPartyType;
    private AuPartyType subPartyType;
    private AuPartyRelationType partyRelationType;
    private String name;
    private String remark;
    private Short level;

    @Enumerated
    @Column(name = "cur_party_type")
    public AuPartyType getCurPartyType() {
        return this.curPartyType;
    }

    public void setCurPartyType(AuPartyType curPartyType) {
        this.curPartyType = curPartyType;
    }

    @Enumerated
    @Column(name = "sub_party_type")
    public AuPartyType getSubPartyType() {
        return this.subPartyType;
    }

    public void setSubPartyType(AuPartyType subPartyType) {
        this.subPartyType = subPartyType;
    }

    @Enumerated
    @Column(name = "relation_type")
    public AuPartyRelationType getPartyRelationType() {
        return this.partyRelationType;
    }

    public void setPartyRelationType(AuPartyRelationType partyRelationType) {
        this.partyRelationType = partyRelationType;
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