/**
 *
 */
package com.qslion.core.util;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.qslion.core.entity.AuPartyRelation;
import com.qslion.core.entity.PartyEntity;
import com.qslion.core.enums.AuPartyType;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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


    public static List<AuPartyRelation> getRelationByPartyType(List<AuPartyRelation> relations, List<AuPartyType> parentPartyTypes) {
        List<AuPartyRelation> childRelations = Lists.newArrayList();
        Map<Long, AuPartyRelation> dictMap = Maps.newHashMap();
        relations.forEach(relation -> {
            if (parentPartyTypes.contains(relation.getPartyType())) {
                childRelations.add(relation);
            }
            dictMap.put(relation.getId(), relation);
        });

        Set<AuPartyRelation> resultRelations = Sets.newHashSet(childRelations);
        for (AuPartyRelation childRelation : childRelations) {
            AuPartyRelation parentRelation = dictMap.get(childRelation.getParentId());
            while (parentRelation != null) {
                resultRelations.add(parentRelation);
                parentRelation = dictMap.get(parentRelation.getParentId());
            }
        }
        return resultRelations.stream().collect(Collectors.toList());
    }

    public static void main(String[] args) {
    }
}
