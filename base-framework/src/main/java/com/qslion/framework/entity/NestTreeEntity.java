package com.qslion.framework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qslion.framework.bean.NestTreeable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * 实体类 - 嵌套树基类
 */
@MappedSuperclass
public class NestTreeEntity extends BaseEntity<Long> implements NestTreeable<Long> {

    private static final long serialVersionUID = -6718838800112233445L;

    private Long parentId;

    private String name;

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @JsonIgnore
    @Transient
    @Override
    public String getLftName() {
        return null;
    }
    @JsonIgnore
    @Transient
    @Override
    public String getRgtName() {
        return null;
    }
    @JsonIgnore
    @Transient
    @Override
    public String getParentName() {
        return null;
    }

    @Override
    @Column(name = "parent_id")
    public Long getParentId() {
        return this.parentId;
    }

    @JsonIgnore
    @Transient
    @Override
    public String getTreeCondition() {
        return null;
    }


    @Override
    public int hashCode() {
        return id == null ? System.identityHashCode(this) : id.hashCode();
    }

    @Override
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    @Transient
    public NestTreeEntity getTree() {
        NestTreeEntity treeEntity = new NestTreeEntity();
        treeEntity.setParentId(getParentId());
        treeEntity.setId(getId());
        treeEntity.setName(getName());
        return treeEntity;
    }
}