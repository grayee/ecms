/**
 *
 */
package com.qslion.core.service.impl;

import com.qslion.core.dao.ConnectionRuleRepository;
import com.qslion.core.entity.AuConnectionRule;
import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.core.enums.AuPartyType;
import com.qslion.core.service.ConnectionRuleService;
import com.qslion.framework.service.impl.GenericServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：authority 类名称：ConnectionRuleServiceImpl 类描述： 创建人：Administrator 创建时间：2011-8-8 下午03:26:02 修改人：Administrator
 * 修改时间：2011-8-8 下午03:26:02 修改备注：
 */
@Service("connectRuleService")
public class ConnectionRuleServiceImpl extends GenericServiceImpl<AuConnectionRule, Long> implements
    ConnectionRuleService {

    @Autowired
    public ConnectionRuleRepository connectionRuleRepository;


    @Override
    public List queryAll(int paramInt1, int paramInt2, String paramString) {
        return null;
    }

    @Override
    public int getRecordCount() {
        return 0;
    }

    @Override
    public int getRecordCount(String paramString) {
        return 0;
    }

    @Override
    public List simpleQuery(int paramInt1, int paramInt2, String paramString, Object paramObject) {
        return null;
    }

    @Override
    public Object find(String paramString) {
        return null;
    }

    @Override
    public int getRecordCount(String paramString1, String paramString2) {
        return 0;
    }

    @Override
    public List queryByType(Object paramObject) {
        return null;
    }

    @Override
    public boolean checkRule(AuPartyType curPartyType, AuPartyType subPartyType, AuPartyRelationType relationType) {
        return connectionRuleRepository.findAll().stream().anyMatch(rule ->
            rule.getSubPartyType() == subPartyType && rule.getCurPartyType() == curPartyType
                && rule.getPartyRelationType() == relationType);
    }

    @Override
    public List<AuConnectionRule> getRuleByParentPartyTypeId(String parentPartyTypeId, String relTypeId) {
        return null;
    }

    @Override
    public List<AuConnectionRule> getRuleBySubParty(AuPartyRelationType relationType, AuPartyType partyType) {
        return connectionRuleRepository.findByPartyRelationTypeAndSubPartyType(relationType,partyType);
    }
}
