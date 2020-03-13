package com.qslion.authority.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qslion.authority.core.enums.AuOrgRelationType;
import com.qslion.authority.core.enums.AuOrgType;
import com.qslion.framework.bean.TreeNode;
import com.qslion.framework.entity.BaseTree;

import javax.persistence.*;


/**
 * 实体类 - 组织关系
 * Accountability
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "au_org_relation")
public class AuOrgRelation extends BaseTree<Long> {

    private AuOrgRelationType relationType;
    private Long orgId;
    private AuOrgType orgType;
    
    @Enumerated
    @Column(name = "relation_type")
    public AuOrgRelationType getRelationType() {
        return this.relationType;
    }

    public void setRelationType(AuOrgRelationType relationType) {
        this.relationType = relationType;
    }

    @JoinColumn(name = "org_id")
    public Long getOrgId() {
        return this.orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    @Enumerated
    @Column(name = "org_type", nullable = false)
    public AuOrgType getOrgType() {
        return orgType;
    }

    public AuOrgRelation setOrgType(AuOrgType orgType) {
        this.orgType = orgType;
        return this;
    }

    @Override
    @JsonIgnore
    @Transient
    public TreeNode getTreeNode() {
        return new TreeNode(String.valueOf(getId()), this.getName());
    }
}