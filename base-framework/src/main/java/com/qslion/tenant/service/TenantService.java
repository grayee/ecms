package com.qslion.tenant.service;

import com.qslion.framework.service.IGenericService;
import com.qslion.tenant.entity.TenantInfo;

/**
 * Tenant Service
 *
 * @author Gray.Z
 * @date 2018/10/30 22:43.
 */
public interface TenantService extends IGenericService<TenantInfo, Long> {

    /**
     * TenantInfo
     *
     * @param tenant 租户标识
     * @return TenantInfo
     */
    TenantInfo findByTenant(String tenant);
}
