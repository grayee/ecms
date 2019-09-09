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
public class BaseTree<T, ID extends Serializable> extends BaseEntity<ID> implements NestTreeable<ID> {
    private static final long serialVersionUID = 1L;
    private String name;
    private ID parentId;
    private List<T> children = new ArrayList<>();

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

    @JsonIgnore
    @Transient
    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    @JsonIgnore
    @Transient
    public BaseTree<T, ID> newTree() {
        BaseTree<T, ID> treeNode = new BaseTree<>();
        treeNode.setParentId(getParentId());
        treeNode.setId(getId());
        treeNode.setName(getName());
        return treeNode;
    }
}
