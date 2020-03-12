package com.qslion.authority.core.dao;

import com.qslion.authority.core.entity.AuConnectionRule;
import com.qslion.authority.core.enums.AuOrgType;
import com.qslion.authority.core.enums.AuOrgRelationType;
import com.qslion.framework.dao.IGenericRepository;

import java.util.List;

/**
 * Dao实现类 - 连接规则
 */
public interface AuConnectionRuleRepository extends IGenericRepository<AuConnectionRule, Long> {

    /**
     * 连接规则
     *
     * @param orgRelationType 关系类型
     * @param subOrgType      下级机构类型
     * @return 连接规则
     */
    List<AuConnectionRule> findByOrgRelationTypeAndSubOrgType(AuOrgRelationType orgRelationType, AuOrgType subOrgType);
}