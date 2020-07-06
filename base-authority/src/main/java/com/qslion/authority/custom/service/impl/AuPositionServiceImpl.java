
package com.qslion.authority.custom.service.impl;


import com.qslion.authority.custom.dao.AuPositionRepository;
import com.qslion.authority.custom.entity.AuPosition;
import com.qslion.authority.custom.service.AuPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 职位Service类
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Service("positionService")
@Transactional(value = "transactionManager", rollbackFor = Exception.class)
public class AuPositionServiceImpl extends AuOrgServiceImpl<AuPosition, Long> implements AuPositionService {
    @Autowired
    private AuPositionRepository positionRepository;


}
