package com.qslion.framework.entity;

import com.qslion.framework.bean.DisplayField;
import com.qslion.framework.bean.NestTreeable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 树
 *
 * @author Gray.Z
 * @date 2018/9/22 22:05.
 */
@MappedSuperclass
public abstract class BaseTree<ID extends Serializable> extends BaseEntity<ID> implements NestTreeable<ID> {
    private static final long serialVersionUID = 1L;
    /**
     * 节点名称
     */
    @DisplayField(order = 1, title = "%s名称",width = "15")
    private String name;
    /**
     * 父节点ID
     */
    @DisplayField(hidden = true)
    private ID parentId;
    /**
     * 层级
     */
    private Integer level;

    /**
     * 排序码
     */
    @DisplayField(order = 3.5, title = "排序码",width = "6")
    private Integer orderCode;
    /**
     * 是否叶子节点
     */
    private Boolean isLeaf;


    private String remark;

    @Override
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    @Column(name = "parent_id")
    public ID getParentId() {
        return parentId;
    }

    public void setParentId(ID parentId) {
        this.parentId = parentId;
    }

    @Override
    @Basic
    @Column(name = "level", nullable = true)
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    @Column(name = "order_code", length = 2)
    public Integer getOrderCode() {
        return this.orderCode;
    }

    public void setOrderCode(Integer orderCode) {
        this.orderCode = orderCode;
    }

    @Override
    @Column(name = "is_leaf", nullable = false, length = 1)
    public Boolean getLeaf() {
        return isLeaf;
    }

    public void setLeaf(Boolean leaf) {
        isLeaf = leaf;
    }

    @Override
    @Column(name = "remark")
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
