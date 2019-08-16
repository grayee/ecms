package com.qslion.custom.dao;

import com.qslion.core.entity.AuParty;
import com.qslion.custom.entity.AuCompany;
import com.qslion.framework.dao.IGenericRepository;

public interface AuCompanyRepository extends IGenericRepository<AuCompany, Long> {


    /**
     * 根据团体获取公司
     *
     * @param auParty 团体
     * @return 团体关系
     */
    AuCompany findByAuParty(AuParty auParty);
}