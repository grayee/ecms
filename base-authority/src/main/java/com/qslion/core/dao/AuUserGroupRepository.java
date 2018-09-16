/*
 * Copyright (c) 2018. Tianyi AIDOC Company.Inc. All Rights Reserved.
 */
package com.qslion.core.dao;


import com.qslion.core.entity.AuUserGroup;
import com.qslion.framework.dao.IGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Dao实现类 - 用户组
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Repository
public interface AuUserGroupRepository extends IGenericRepository<AuUserGroup, Long> {

}