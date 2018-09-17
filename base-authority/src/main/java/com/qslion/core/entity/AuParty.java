package com.qslion.core.entity;

import com.qslion.core.constant.AuPartyType;
import com.qslion.framework.entity.BaseEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    private String isInherit;
    private String isReal;
    private String name;
    private String email;
    private String ownerOrg;
    private String remark;
    private String enableStatus;
    private Date enableDate;

    @Column(name = "type_id", nullable = false)
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
    public String getIsInherit() {
        return this.isInherit;
    }

    public void setIsInherit(String isInherit) {
        this.isInherit = isInherit;
    }

    @Column(name = "is_real", nullable = false, length = 1)
    public String getIsReal() {
        return this.isReal;
    }

    public void setIsReal(String isReal) {
        this.isReal = isReal;
    }

    @Column(name = "name", length = 300)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "email", length = 300)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "owner_org", length = 300)
    public String getOwnerOrg() {
        return this.ownerOrg;
    }

    public void setOwnerOrg(String ownerOrg) {
        this.ownerOrg = ownerOrg;
    }

    @Column(name = "remark", length = 1000)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "enable_status", nullable = false, length = 1)
    public String getEnableStatus() {
        return this.enableStatus;
    }

    public void setEnableStatus(String enableStatus) {
        this.enableStatus = enableStatus;
    }

    @Column(name = "enable_date", length = 19)
    public Date getEnableDate() {
        return this.enableDate;
    }

    public void setEnableDate(Date enableDate) {
        this.enableDate = enableDate;
    }
}