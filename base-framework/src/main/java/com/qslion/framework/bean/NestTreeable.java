package com.qslion.framework.bean;


public interface NestTreeable<T> {
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
    String getLftName();

    /**
     * 获得树右边属性名称
     */
    String getRgtName();

    /**
     * 获得父节点属性名称
     */
    String getParentName();


    /**
     * 获得父节点ID
     *
     * @return 如果没有父节点，则返回null。
     */
    T getParentId();

    /**
     * 获得树ID
     */
    T getId();

    /**
     * 获得附加条件 <p> 通过附加条件可以维护多棵树相互独立的树，附加条件使用hql语句，实体别名为bean。例如：bean.website.id=5
     *
     * @return 为null则不添加任何附加条件
     */
    String getTreeCondition();
}
