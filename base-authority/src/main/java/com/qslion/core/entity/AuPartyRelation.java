package com.qslion.core.entity;

import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.framework.entity.NestTreeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * 实体类 - 团体关系
 * Accountability
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "au_party_relation")
public class AuPartyRelation extends NestTreeEntity {

    private AuPartyRelationType auPartyRelationType;
    private AuParty auParty;

    private String name;
    private String remark;

    /**
     * 层级
     */
    private Short level;
    /**
     * 排序码
     */
    private String orderCode;
    /**
     * 是否叶子节点
     */
    private boolean leaf;
    /**
     * 是否继承
     */
    private boolean isInherit;

    @Enumerated
    @Column(name = "relation_type")
    public AuPartyRelationType getAuPartyRelationType() {
        return this.auPartyRelationType;
    }

    public void setAuPartyRelationType(AuPartyRelationType auPartyRelationType) {
        this.auPartyRelationType = auPartyRelationType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id")
    public AuParty getAuParty() {
        return this.auParty;
    }

    public void setAuParty(AuParty auParty) {
        this.auParty = auParty;
    }

    @Column(name = "remark")
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "name", length = 100)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "order_code", length = 2)
    public String getOrderCode() {
        return this.orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    @Column(name = "level", precision = 3, scale = 0)
    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    @Column(name = "is_leaf", nullable = false, length = 1)
    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        leaf = leaf;
    }

    @Column(name = "is_inherit", length = 1)
    public boolean isInherit() {
        return isInherit;
    }

    public void setInherit(boolean inherit) {
        isInherit = inherit;
    }
}