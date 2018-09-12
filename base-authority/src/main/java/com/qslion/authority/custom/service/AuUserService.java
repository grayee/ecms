/*
 * Copyright (c) 2018. Tianyi AIDOC Company.Inc. All Rights Reserved.
 */

package com.qslion.authority.custom.service;

import com.qslion.authority.core.entity.AuUser;
import com.qslion.framework.service.IGenericService;

/**
 * server
 *
 * @author Gray.Z
 * @date 2018/4/30 19:15.
 */
public interface AuUserService extends IGenericService<AuUser,Long> {

    AuUser addAuUser(AuUser auUser);
}
