/*
 * Copyright (c) 2018. Tianyi AIDOC Company.Inc. All Rights Reserved.
 */

/**
 *
 */
package com.qslion.authority.custom.service;


import com.qslion.authority.core.entity.AuResource;
import com.qslion.framework.service.IGenericService;
import java.util.List;

/**
 * 修改备注：
 */

public interface AuResourceService extends IGenericService<AuResource,Long> {


    List<AuResource> queryByCondition(String hql);

    /**
     * 取得当前菜单节点下的所有子节点
     */
    List<AuResource> getChildrens(AuResource auResource);

    /**
     * 取得当前菜单节点的父节点下的所有子节点
     */
    List<AuResource> getParentChilds(AuResource auResource);

    /**
     * 取得当前菜单节点的父节点
     */
    AuResource getParent(AuResource auResource);

    /**
     * 检查功能节点名字是否重复
     */
    boolean checkUnique(AuResource auResource);

    List<AuResource> getAll();

}
