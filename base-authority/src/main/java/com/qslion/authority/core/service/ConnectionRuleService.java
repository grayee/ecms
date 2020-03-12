/**
 *
 */
package com.qslion.authority.core.service;

import com.qslion.authority.core.entity.AuConnectionRule;
import com.qslion.authority.core.enums.AuOrgType;
import com.qslion.authority.core.enums.AuOrgRelationType;
import com.qslion.framework.service.IGenericService;

import java.util.List;

/**
 * 修改备注：
 */
public interface ConnectionRuleService extends IGenericService<AuConnectionRule, Long> {


    /**
     * 根据父子团体类型ID和团体关系类型ID验证是否满足连接规则
     *
     * @param curOrgType   当前组织团体类型
     * @param subOrgType   下级组织类型
     * @param relationType 组织关系
     * @return boolean
     */
    boolean checkRule(AuOrgType curOrgType, AuOrgType subOrgType, AuOrgRelationType relationType);
 

    /**
     * 根据子组织类型ID和组织关系类型查找链接规则
     *
     * @param relationType 组织关系
     * @param orgType      下级组织类型
     * @return List
     */
    List<AuConnectionRule> getRuleBySubOrg(AuOrgRelationType relationType, AuOrgType orgType);
}
