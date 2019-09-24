package com.qslion.moudles.tenant.dao;

import com.qslion.framework.dao.IGenericRepository;
import com.qslion.moudles.tenant.entity.TenantInfo;

/**
 * Tenant DAO
 *
 * @author Gray.Z
 * @date 2018/10/30 22:43.
 */
public interface TenantRepository extends IGenericRepository<TenantInfo, Long> {

    /**
     * TenantInfo
     *
     * @param tenant 租户标识
     * @return TenantInfo
     */
    TenantInfo findByTenant(String tenant);
}
