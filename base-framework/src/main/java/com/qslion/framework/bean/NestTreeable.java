package com.qslion.framework.bean;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Transient;

public interface NestTreeable<ID> {
    /**
     * 默认树左边属性名称
     */
    String DEF_LEFT_NAME = "lft";
    /**
     * 默认树右边属性名称
     */
    String DEF_RIGHT_NAME = "rgt";
    /**
     * 默认父节点属性名称
     */
    String DEF_PARENT_NAME = "parent";
    /**
     * 实体类别名
     */
    String ENTITY_ALIAS = "bean";

    /**
     * 获得树左边属性名称
     */
    @JsonIgnore
    @Transient
    default String getLftName() {
        return DEF_LEFT_NAME;
    }

    /**
     * 获得树右边属性名称
     */
    @JsonIgnore
    @Transient
    default String getRgtName() {
        return DEF_RIGHT_NAME;
    }

    /**
     * 获得父节点属性名称
     */
    @JsonIgnore
    @Transient
    default String getParentName() {
        return DEF_PARENT_NAME;
    }


    /**
     * 获得父节点ID
     *
     * @return 如果没有父节点，则返回null。
     */
    ID getParentId();

    /**
     * 获得树ID
     */
    ID getId();

    /**
     * 获得附加条件 <p> 通过附加条件可以维护多棵树相互独立的树，附加条件使用hql语句，实体别名为bean。例如：bean.website.id=5
     *
     * @return 为null则不添加任何附加条件
     */
    @JsonIgnore
    @Transient
    default String getTreeCondition() {
        return ENTITY_ALIAS + "=?";
    }

    String getName();
}
