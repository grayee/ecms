/**
 *
 */
package com.qslion.core.service;

import com.qslion.core.entity.AuConnectionRule;
import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.core.enums.AuPartyType;
import com.qslion.framework.service.IGenericService;
import java.util.List;

/**
 * 修改备注：
 */
public interface ConnectionRuleService extends IGenericService<AuConnectionRule, Long> {

    List queryAll(int paramInt1, int paramInt2, String paramString);

    int getRecordCount();

    int getRecordCount(String paramString);

    List simpleQuery(int paramInt1, int paramInt2, String paramString, Object paramObject);


    Object find(String paramString);

    int getRecordCount(String paramString1, String paramString2);

    List queryByType(Object paramObject);

    /**
     * 根据父子团体类型ID和团体关系类型ID验证是否满足连接规则
     *
     * @param curPartyType 当前团体类型
     * @param subPartyType 下级团体类型
     * @param relationType 团体关系
     * @return boolean
     */
    boolean checkRule(AuPartyType curPartyType, AuPartyType subPartyType, AuPartyRelationType relationType);

    //根据父团体类型ID和团体关系类型查找链接规则
    List<AuConnectionRule> getRuleByParentPartyTypeId(String parentPartyTypeId, String relTypeId);

    //根据子团体类型ID和团体关系类型查找链接规则
    List<AuConnectionRule> getRuleByChildPartyTypeId(String childPartyTypeId, String relTypeId);
}
