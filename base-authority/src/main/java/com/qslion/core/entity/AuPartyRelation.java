package com.qslion.core.entity;

import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.core.enums.AuPartyType;
import com.qslion.framework.entity.NestTreeEntity;

import javax.persistence.*;


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

    private AuPartyRelationType relationType;
    private Long partyId;
    private AuPartyType partyType;
    private String name;
    private String remark;

    /**
     * 层级
     */
    private Integer level;
    /**
     * 排序码
     */
    private Long orderCode;
    /**
     * 是否叶子节点
     */
    private Boolean isLeaf;
    /**
     * 是否继承
     */
    private Boolean isInherit;

    @Enumerated
    @Column(name = "relation_type")
    public AuPartyRelationType getRelationType() {
        return this.relationType;
    }

    public void setRelationType(AuPartyRelationType relationType) {
        this.relationType = relationType;
    }

    @JoinColumn(name = "party_id")
    public Long getPartyId() {
        return this.partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    @Enumerated
    @Column(name = "party_type_id", nullable = false)
    public AuPartyType getPartyType() {
        return partyType;
    }

    public AuPartyRelation setPartyType(AuPartyType partyType) {
        this.partyType = partyType;
        return this;
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
    public Long getOrderCode() {
        return this.orderCode;
    }

    public void setOrderCode(Long orderCode) {
        this.orderCode = orderCode;
    }

    @Column(name = "level", precision = 3, scale = 0)
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Column(name = "is_leaf", nullable = false, length = 1)
    public Boolean getLeaf() {
        return isLeaf;
    }

    public void setLeaf(Boolean leaf) {
        isLeaf = leaf;
    }

    @Column(name = "is_inherit", length = 1)
    public Boolean getInherit() {
        return isInherit;
    }

    public void setInherit(Boolean inherit) {
        isInherit = inherit;
    }
}