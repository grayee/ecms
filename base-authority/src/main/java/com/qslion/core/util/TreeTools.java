/**
 *
 */
package com.qslion.core.util;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.qslion.core.entity.AuPartyRelation;
import com.qslion.core.entity.AuResource;
import com.qslion.core.entity.PartyEntity;
import com.qslion.core.enums.AuPartyType;
import com.qslion.framework.bean.TreeNode;

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

        Set<AuPartyRelation> resultRelations = getAuPartyRelations(childRelations, dictMap);
        return resultRelations.stream().collect(Collectors.toList());
    }

    public static List<AuPartyRelation> getPathTreeData(List<AuPartyRelation> relations, List<Long> targetIds) {
        List<AuPartyRelation> childRelations = Lists.newArrayList();
        Map<Long, AuPartyRelation> dictMap = Maps.newHashMap();
        relations.forEach(relation -> {
            if (targetIds.contains(relation.getId())) {
                childRelations.add(relation);
            }
            dictMap.put(relation.getId(), relation);
        });

        Set<AuPartyRelation> resultRelations = getAuPartyRelations(childRelations, dictMap);
        return resultRelations.stream().collect(Collectors.toList());
    }

    private static Set<AuPartyRelation> getAuPartyRelations(List<AuPartyRelation> childRelations, Map<Long, AuPartyRelation> dictMap) {
        Set<AuPartyRelation> resultRelations = Sets.newHashSet(childRelations);
        for (AuPartyRelation childRelation : childRelations) {
            AuPartyRelation parentRelation = dictMap.get(childRelation.getParentId());
            while (parentRelation != null) {
                resultRelations.add(parentRelation);
                parentRelation = dictMap.get(parentRelation.getParentId());
            }
        }
        return resultRelations;
    }

    public static List<AuResource> getPathTree(List<AuResource> resources, List<Long> targetIds) {
        List<AuResource> childResource = Lists.newArrayList();
        Map<Long, AuResource> dictMap = Maps.newHashMap();
        resources.forEach(resource -> {
            if (targetIds.contains(resource.getId())) {
                childResource.add(resource);
            }
            dictMap.put(resource.getId(), resource);
        });

        Set<AuResource> resultResource = Sets.newHashSet(childResource);
        for (AuResource resource : childResource) {
            AuResource parentResource = dictMap.get(resource.getParentId());
            while (parentResource != null) {
                resultResource.add(parentResource);
                parentResource = dictMap.get(parentResource.getParentId());
            }
        }
        return resultResource.stream().collect(Collectors.toList());
    }

    public static void main(String[] args) {
    }
}
