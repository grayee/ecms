/**
 *
 */
package com.qslion.authority.core.service.impl;

import com.qslion.authority.core.dao.ConnectionRuleRepository;
import com.qslion.authority.core.entity.AuConnectionRule;
import com.qslion.authority.core.service.ConnectionRuleService;
import com.qslion.framework.service.impl.GenericServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：authority
 * 类名称：ConnectionRuleServiceImpl
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2011-8-8 下午03:26:02
 * 修改人：Administrator
 * 修改时间：2011-8-8 下午03:26:02
 * 修改备注：
 */
@Service("connectRuleService")
public class ConnectionRuleServiceImpl extends GenericServiceImpl<AuConnectionRule, Long> implements
        ConnectionRuleService {

    @Autowired
    public ConnectionRuleRepository connectRuleDao;


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
    public boolean checkRule(String parentTypeId, String childTypeId, String relationTypeId) {
        return false;
    }

    @Override
    public List<AuConnectionRule> getRuleByParentPartyTypeId(String parentPartyTypeId, String relTypeId) {
        return null;
    }

    @Override
    public List<AuConnectionRule> getRuleByChildPartyTypeId(String childPartyTypeId, String relTypeId) {
        return null;
    }
}
