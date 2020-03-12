/**
 *
 */
package com.qslion.authority.core.service.impl;

import com.qslion.authority.core.dao.AuConnectionRuleRepository;
import com.qslion.authority.core.entity.AuConnectionRule;
import com.qslion.authority.core.enums.AuOrgType;
import com.qslion.authority.core.enums.AuOrgRelationType;
import com.qslion.authority.core.service.ConnectionRuleService;
import com.qslion.framework.service.impl.GenericServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 项目名称：authority 类名称：ConnectionRuleServiceImpl 类描述： 创建人：Administrator 创建时间：2011-8-8 下午03:26:02 修改人：Administrator
 * 修改时间：2011-8-8 下午03:26:02 修改备注：
 */
@Service("connectRuleService")
@Transactional(value = "transactionManager", rollbackFor = Exception.class)
public class ConnectionRuleServiceImpl extends GenericServiceImpl<AuConnectionRule, Long> implements
    ConnectionRuleService {

    @Autowired
    public AuConnectionRuleRepository auConnectionRuleRepository;


    @Override
    public boolean checkRule(AuOrgType curOrgType, AuOrgType subOrgType, AuOrgRelationType relationType) {
        return auConnectionRuleRepository.findAll().stream().anyMatch(rule ->
            rule.getSubOrgType() == subOrgType && rule.getCurOrgType() == curOrgType
                && rule.getOrgRelationType() == relationType);
    }



    @Override
    public List<AuConnectionRule> getRuleBySubOrg(AuOrgRelationType relationType, AuOrgType orgType) {
        return auConnectionRuleRepository.findByOrgRelationTypeAndSubOrgType(relationType,orgType);
    }
}
