package com.qslion.framework.entity;

import com.qslion.framework.bean.NestTreeable;

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

    /**
     * 改进前序遍历树,左值和右值
     */
    private Integer lft;
    private Integer rgt;

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    @Column(name = "parent_id", length = 300)
    public Long getParentId() {
        return this.parentId;
    }

    @Column(name = "lft", length = 5)
    public Integer getLft() {
        return lft;
    }

    public void setLft(Integer lft) {
        this.lft = lft;
    }

    @Column(name = "rgt", length = 5)
    public Integer getRgt() {
        return rgt;
    }

    public void setRgt(Integer rgt) {
        this.rgt = rgt;
    }

    @Override
    @Transient
    public String getLftName() {
        return DEF_LEFT_NAME;
    }

    @Override
    @Transient
    public String getParentName() {
        return DEF_PARENT_NAME;
    }

    @Override
    @Transient
    public String getRgtName() {
        return DEF_RIGHT_NAME;
    }

    @Override
    @Transient
    public String getTreeCondition() {
        return null;
    }

    @Override
    public int hashCode() {
        return id == null ? System.identityHashCode(this) : id.hashCode();
    }
}