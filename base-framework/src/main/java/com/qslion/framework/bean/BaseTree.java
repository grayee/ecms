package com.qslion.framework.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 树
 *
 * @author Gray.Z
 * @date 2018/9/22 22:05.
 */
public class BaseTree<E, ID extends Serializable> implements Serializable {
    private static final long serialVersionUID = 1L;
    private ID id;
    private ID parentId;
    private List<E> children = new ArrayList<E>();

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public ID getParentId() {
        return parentId;
    }

    public void setParentId(ID parentId) {
        this.parentId = parentId;
    }

    public List<E> getChildren() {
        return children;
    }

    public void setChildren(List<E> children) {
        this.children = children;
    }
}
