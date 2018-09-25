package com.qslion.core.entity;

import com.qslion.core.enums.AuPartyType;
import com.qslion.custom.entity.AuCompany;
import com.qslion.custom.entity.AuDepartment;
import com.qslion.custom.entity.AuEmployee;
import com.qslion.custom.entity.AuPosition;
import com.qslion.framework.entity.BaseEntity;
import com.qslion.framework.enums.EnableStatus;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    private String ownerOrg;
    private String remark;
    private EnableStatus enableStatus;
    private Date enableDate;

    private AuCompany auCompany;
    private AuDepartment auDepartment;
    private AuPosition auPosition;
    private AuEmployee auEmployee;

    /*    private AuRole auRole;
     private AuUser auUser;
     private Set<AuAuthorize> auAuthorizes = new HashSet<AuAuthorize>(0);
     private Set<AuUserProfile> auUserprofiles = new HashSet<AuUserProfile>(0);
 */

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
/*
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "auParty")
    public Set<AuAuthorize> getAuAuthorizes() {
        return auAuthorizes;
    }

    public void setAuAuthorizes(Set<AuAuthorize> auAuthorizes) {
        this.auAuthorizes = auAuthorizes;
    }*/

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "auParty")
    public AuCompany getAuCompany() {
        return this.auCompany;
    }

    public void setAuCompany(AuCompany auCompany) {
        this.auCompany = auCompany;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "auParty")
    public AuPosition getAuPosition() {
        return this.auPosition;
    }

    public void setAuPosition(AuPosition auPosition) {
        this.auPosition = auPosition;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "auParty")
    public Set<AuPartyRelation> getAuPartyRelations() {
        return this.auPartyRelations;
    }

    public void setAuPartyRelations(Set<AuPartyRelation> auPartyRelations) {
        this.auPartyRelations = auPartyRelations;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "auParty")
    public AuDepartment getAuDepartment() {
        return this.auDepartment;
    }

    public void setAuDepartment(AuDepartment auDepartment) {
        this.auDepartment = auDepartment;
    }

   /* @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "auParty")
    public Set<AuUserProfile> getAuUserProfiles() {
        return this.auUserprofiles;
    }

    public void setAuUserProfiles(Set<AuUserProfile> auUserprofiles) {
        this.auUserprofiles = auUserprofiles;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "auParty")
    public AuUser getAuUser() {
        return this.auUser;
    }

    public void setAuUser(AuUser auUser) {
        this.auUser = auUser;
    }*/

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "auParty")
    public AuEmployee getAuEmployee() {
        return this.auEmployee;
    }

    public void setAuEmployee(AuEmployee auEmployee) {
        this.auEmployee = auEmployee;
    }

 /*   @OneToOne(fetch = FetchType.LAZY, mappedBy = "auParty")
    public AuRole getAuRole() {
        return auRole;
    }

    public void setAuRole(AuRole auRole) {
        this.auRole = auRole;
    }*/
}