package com.qslion.custom.dao;

import com.qslion.core.entity.AuParty;
import com.qslion.custom.entity.AuDepartment;
import com.qslion.custom.entity.AuPosition;
import com.qslion.framework.dao.IGenericRepository;

public interface AuPositionRepository extends IGenericRepository<AuPosition, Long> {

    /**
     * 根据团体获取职位
     *
     * @param auParty 团体
     * @return 团体关系
     */
    AuPosition findByAuParty(AuParty auParty);
}