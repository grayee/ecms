/**
 *
 */
package com.qslion.core.util;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qslion.core.entity.AuPartyRelation;
import com.qslion.core.entity.PartyEntity;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 组织机构树工具类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
public class TreeTools {

    public static String getOrgStr(List<AuPartyRelation> relations, PartyEntity partyEntity) {
        Map<Long, AuPartyRelation> dictMap = Maps.newHashMap();
        for (AuPartyRelation relation : relations) {
            dictMap.put(relation.getId(), relation);
        }
        List<String> orgList = Lists.newArrayList();
        AuPartyRelation parentRelation = dictMap.get(partyEntity.getParentId());
        while (parentRelation != null) {
            orgList.add(parentRelation.getName());
            parentRelation = dictMap.get(parentRelation.getParentId());
        }
        Collections.reverse(orgList);
        return Joiner.on(GlobalConstants.ORG_TREE_SEPARATOR).join(orgList);
    }

    public static void main(String[] args) {
    }
}
