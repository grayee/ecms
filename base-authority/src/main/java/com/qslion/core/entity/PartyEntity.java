package com.qslion.core.entity;

import com.qslion.framework.entity.BaseEntity;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Created by zhangruigang on 2017/9/26.
 */
@MappedSuperclass
public abstract class PartyEntity extends BaseEntity {

    protected AuParty auParty;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    public AuParty getAuParty() {
        return this.auParty;
    }

    public void setAuParty(AuParty auParty) {
        this.auParty = auParty;
    }
}
