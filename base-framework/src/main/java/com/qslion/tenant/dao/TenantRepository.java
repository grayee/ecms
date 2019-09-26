package com.qslion.tenant.dao;

import com.qslion.framework.dao.IGenericRepository;
import com.qslion.tenant.entity.TenantInfo;
import org.springframework.stereotype.Repository;

/**
 * Tenant DAO
 *
 * @author Gray.Z
 * @date 2018/10/30 22:43.
 */
@Repository
public interface TenantRepository extends IGenericRepository<TenantInfo, Long> {

    /**
     * TenantInfo
     *
     * @param tenant 租户标识
     * @return TenantInfo
     */
    TenantInfo findByTenant(String tenant);
}
