/*
 * Copyright (c) 2018. Tianyi AIDOC Company.Inc. All Rights Reserved.
 */

/**
 *
 */
package com.qslion.authority.custom.service.impl;


import com.qslion.authority.core.entity.AuResource;
import com.qslion.authority.custom.dao.AuResourceRepository;
import com.qslion.authority.custom.service.AuResourceService;
import com.qslion.framework.service.impl.GenericServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 修改备注：
 */
@Service
public class AuResourceServiceImpl extends GenericServiceImpl<AuResource,Long> implements AuResourceService {

    @Autowired
    public AuResourceRepository resourceRepository;


    public List<AuResource> queryByCondition(String paramString) {
        // TODO Auto-generated method stub
        return null;
    }


    public List<AuResource> getChildrens(AuResource funcTree) {
        // TODO Auto-generated method stub
        List<AuResource> result;
        String propertyName = "parentCode";
        result = null;
        return result;
    }

    public List<AuResource> getParentChilds(AuResource funcTree) {
        // TODO Auto-generated method stub
        List<AuResource> result;
        String propertyName = "parentCode";
        result = null;
        return result;
    }

    public AuResource getParent(AuResource funcTree) {
        // TODO Auto-generated method stub
        String propertyName = "totalCode";
        AuResource parent = null;
        return parent;
    }

    @Override
    public boolean checkUnique(AuResource funcTree) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<AuResource> getAll() {
        return resourceRepository.findAll();
    }




 

}
