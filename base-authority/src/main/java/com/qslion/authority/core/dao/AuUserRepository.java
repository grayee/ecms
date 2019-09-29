/*
 * Copyright (c) 2018. Tianyi AIDOC Company.Inc. All Rights Reserved.
 */
package com.qslion.authority.core.dao;


import com.qslion.authority.core.entity.AuUser;
import com.qslion.framework.dao.IGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Dao实现类 - 用户
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Repository
public interface AuUserRepository extends IGenericRepository<AuUser, Long> {


    AuUser findUserByUsername(String username);

}