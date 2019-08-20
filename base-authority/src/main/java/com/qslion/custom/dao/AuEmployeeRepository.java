package com.qslion.custom.dao;

import com.qslion.core.entity.AuParty;
import com.qslion.custom.entity.AuEmployee;
import com.qslion.custom.entity.AuPosition;
import com.qslion.framework.dao.IGenericRepository;

public interface AuEmployeeRepository extends IGenericRepository<AuEmployee, Long> {

    /**
     * 根据团体获取员工
     *
     * @param auParty 团体
     * @return 团体关系
     */
    AuEmployee findByAuParty(AuParty auParty);
}