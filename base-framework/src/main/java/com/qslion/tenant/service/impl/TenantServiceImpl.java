package com.qslion.tenant.service.impl;

import com.qslion.tenant.dao.TenantRepository;
import com.qslion.tenant.entity.TenantInfo;
import com.qslion.tenant.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Tenant Service
 *
 * @author Gray.Z
 * @date 2018/10/30 22:43.
 */
@Service
@Transactional(value = "masterTransactionManager", rollbackFor = Exception.class)
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantRepository repository;

    @Override
    public TenantInfo findByTenant(String tenant) {
        return repository.findByTenant(tenant);
    }
}
