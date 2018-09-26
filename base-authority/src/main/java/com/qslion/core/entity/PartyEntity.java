package com.qslion.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qslion.framework.entity.BaseEntity;
import com.qslion.framework.enums.EnableStatus;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

/**
 * 实体类 - 团体基础类
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@MappedSuperclass
public class PartyEntity extends BaseEntity<Long> {

    @JsonIgnore
    protected AuParty auParty;
    protected EnableStatus enableStatus;
    protected Date enableDate;


    /**
     * 根据两张表的主键关联
     */
    @OneToOne(targetEntity = AuParty.class, fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "party_id", referencedColumnName = "id")
    public AuParty getAuParty() {
        return this.auParty;
    }

    public void setAuParty(AuParty auParty) {
        this.auParty = auParty;
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

}
