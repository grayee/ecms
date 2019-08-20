package com.qslion.custom.dao;

import com.qslion.core.entity.AuParty;
import com.qslion.custom.entity.AuCompany;
import com.qslion.custom.entity.AuDepartment;
import com.qslion.framework.dao.IGenericRepository;

public interface AuDepartmentRepository extends IGenericRepository<AuDepartment, Long> {


    /**
     * 根据团体获取部门
     *
     * @param auParty 团体
     * @return 团体关系
     */
    AuDepartment findByAuParty(AuParty auParty);
}