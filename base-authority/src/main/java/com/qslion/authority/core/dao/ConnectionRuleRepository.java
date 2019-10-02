package com.qslion.authority.core.dao;

import com.qslion.authority.core.entity.AuConnectionRule;
import com.qslion.authority.core.enums.AuPartyRelationType;
import com.qslion.authority.core.enums.AuPartyType;
import com.qslion.framework.dao.IGenericRepository;

import java.util.List;

/**
 * Dao实现类 - 连接规则
 */
public interface ConnectionRuleRepository extends IGenericRepository<AuConnectionRule, Long> {

    /**
     * 连接规则
     *
     * @param partyRelationType 关系类型
     * @param subPartyType      下级团体类型
     * @return 连接规则
     */
    List<AuConnectionRule> findByPartyRelationTypeAndSubPartyType(AuPartyRelationType partyRelationType, AuPartyType subPartyType);
}