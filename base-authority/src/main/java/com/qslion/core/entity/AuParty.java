package com.qslion.core.entity;

import com.qslion.core.enums.AuPartyType;
import com.qslion.framework.entity.BaseEntity;
import com.qslion.framework.enums.EnableStatus;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * 实体类 - 团体
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "au_party")
public class AuParty extends BaseEntity<Long> {

    private AuPartyType auPartyType;
    private String typeKeyword;
    private boolean inherit;
    private boolean real;
    private String name;
    private String ownerOrg;
    private String remark;
    private EnableStatus enableStatus;

    private Date enableDate;

    private Set<AuPartyRelation> auPartyRelations = new HashSet<AuPartyRelation>(0);

    @Column(name = "party_type_id", nullable = false)
    public AuPartyType getAuPartyType() {
        return this.auPartyType;
    }

    public void setAuPartyType(AuPartyType auPartyType) {
        this.auPartyType = auPartyType;
    }

    @Column(name = "type_keyword", length = 1)
    public String getTypeKeyword() {
        return this.typeKeyword;
    }

    public void setTypeKeyword(String partyTypeKeyword) {
        this.typeKeyword = typeKeyword;
    }

    @Column(name = "is_inherit", nullable = false, length = 1)
    public boolean isInherit() {
        return this.inherit;
    }

    public void setInherit(boolean inherit) {
        this.inherit = inherit;
    }

    @Column(name = "is_real", length = 1)
    public boolean isReal() {
        return this.real;
    }

    public void setReal(boolean real) {
        this.real = real;
    }

    @Column(name = "name", length = 64)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "owner_org")
    public String getOwnerOrg() {
        return this.ownerOrg;
    }

    public void setOwnerOrg(String ownerOrg) {
        this.ownerOrg = ownerOrg;
    }

    @Column(name = "remark")
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Enumerated
    @Column(name = "enable_status", length = 1)
    public EnableStatus getEnableStatus() {
        return this.enableStatus;
    }

    public void setEnableStatus(EnableStatus enableStatus) {
        this.enableStatus = enableStatus;
    }

    @Column(name = "enable_date")
    public Date getEnableDate() {
        return this.enableDate;
    }

    public void setEnableDate(Date enableDate) {
        this.enableDate = enableDate;
    }
}