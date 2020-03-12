package com.qslion.framework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qslion.framework.bean.NestTreeable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 树
 *
 * @author Gray.Z
 * @date 2018/9/22 22:05.
 */
@MappedSuperclass
public abstract class AbstractTree<ID extends Serializable> extends BaseEntity<ID> implements NestTreeable<ID> {
    private static final long serialVersionUID = 1L;
    /**
     * 节点名称
     */
    private String name;
    /**
     * 父节点ID
     */
    private ID parentId;
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
     * 子节点
     */
    private List<? extends AbstractTree<ID>> children = new ArrayList<>();

    private String remark;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "parent_id")
    public ID getParentId() {
        return parentId;
    }

    public void setParentId(ID parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "level", nullable = true)
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Column(name = "order_code", length = 2)
    public Long getOrderCode() {
        return this.orderCode;
    }

    public void setOrderCode(Long orderCode) {
        this.orderCode = orderCode;
    }


    @Column(name = "is_leaf", nullable = false, length = 1)
    public Boolean getLeaf() {
        return isLeaf;
    }

    public void setLeaf(Boolean leaf) {
        isLeaf = leaf;
    }

    @JsonIgnore
    @Transient
    public List<? extends AbstractTree<ID>> getChildren() {
        return children;
    }

    public void setChildren(List<? extends AbstractTree<ID>> children) {
        this.children = children;
    }

    @Column(name = "remark")
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
