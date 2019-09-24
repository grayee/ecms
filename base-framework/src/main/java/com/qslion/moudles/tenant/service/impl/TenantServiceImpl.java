package com.qslion.moudles.tenant.service.impl;

import com.qslion.framework.service.impl.GenericServiceImpl;
import com.qslion.moudles.tenant.dao.TenantRepository;
import com.qslion.moudles.tenant.entity.TenantInfo;
import com.qslion.moudles.tenant.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Tenant Service
 *
 * @author Gray.Z
 * @date 2018/10/30 22:43.
 */
public class TenantServiceImpl extends GenericServiceImpl<TenantInfo, Long> implements
        TenantService {

    @Autowired
    private TenantRepository repository;

    @Override
    public TenantInfo findByTenant(String tenant) {
        return repository.findByTenant(tenant);
    }
}
