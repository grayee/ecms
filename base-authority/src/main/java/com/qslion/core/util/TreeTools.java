/**
 *
 */
package com.qslion.core.util;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.qslion.core.entity.AuMenu;
import com.qslion.core.entity.AuPartyRelation;
import com.qslion.core.entity.AuResource;
import com.qslion.core.entity.PartyEntity;
import com.qslion.core.enums.AuPartyType;
import com.qslion.framework.bean.TreeNode;
import com.qslion.framework.entity.NestTreeEntity;

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

    private static Integer MAX_DEPTH = 100;

    /**
     * 根据parentId 获取树路径
     *
     * @param treeEntities NestTreeEntity
     * @param parentId     parentId
     * @return String
     */
    public static String getPathTreeStr(List<NestTreeEntity> treeEntities, Long parentId) {
        Map<Long, NestTreeEntity> dictMap = Maps.newHashMapWithExpectedSize(treeEntities.size());
        treeEntities.forEach(tree -> dictMap.put(tree.getId(), tree));
        List<String> orgList = Lists.newArrayList();
        NestTreeEntity parent = dictMap.get(parentId);
        int depth = 0;
        while (parent != null && depth < MAX_DEPTH) {
            orgList.add(parent.getName());
            parent = dictMap.get(parent.getParentId());
            depth++;
        }
        Collections.reverse(orgList);
        return Joiner.on(GlobalConstants.ORG_TREE_SEPARATOR).join(orgList);
    }


    public static List<AuPartyRelation> getRelationByPartyType(List<AuPartyRelation> relations, List<AuPartyType> parentPartyTypes) {
        List<AuPartyRelation> children = Lists.newArrayList();
        Map<Long, AuPartyRelation> dictMap = Maps.newHashMapWithExpectedSize(relations.size());
        relations.forEach(relation -> {
            if (parentPartyTypes.contains(relation.getPartyType())) {
                children.add(relation);
            }
            dictMap.put(relation.getId(), relation);
        });

        Set<AuPartyRelation> resultRelations = getAuPartyRelations(children, dictMap);
        return resultRelations.stream().collect(Collectors.toList());
    }

    public static List<AuPartyRelation> getPathTreeData(List<AuPartyRelation> relations, List<Long> targetIds) {
        List<AuPartyRelation> childRelations = Lists.newArrayList();
        Map<Long, AuPartyRelation> dictMap = Maps.newHashMapWithExpectedSize(relations.size());
        relations.forEach(relation -> {
            if (targetIds.contains(relation.getId())) {
                childRelations.add(relation);
            }
            dictMap.put(relation.getId(), relation);
        });

        Set<AuPartyRelation> result = getAuPartyRelations(childRelations, dictMap);
        return result.stream().collect(Collectors.toList());
    }

    public static List<AuResource> getPathTree(List<AuResource> resources, List<Long> targetIds) {
        List<AuResource> children = Lists.newArrayList();
        Map<Long, AuResource> dictMap = Maps.newHashMapWithExpectedSize(resources.size());
        resources.forEach(resource -> {
            if (targetIds.contains(resource.getId())) {
                children.add(resource);
            }
            dictMap.put(resource.getId(), resource);
        });

        Set<AuResource> result = Sets.newHashSet(children);
        for (AuResource resource : children) {
            AuResource parent = dictMap.get(resource.getParentId());
            int depth = 0;
            while (parent != null && depth < MAX_DEPTH) {
                result.add(parent);
                parent = dictMap.get(parent.getParentId());
                depth++;
            }
        }
        return result.stream().collect(Collectors.toList());
    }

    public static List<AuMenu> getMenuPath(List<AuMenu> menus, List<Long> targetIds) {
        List<AuMenu> children = Lists.newArrayList();
        Map<Long, AuMenu> dictMap = Maps.newHashMapWithExpectedSize(menus.size());
        menus.forEach(menu -> {
            if (targetIds.contains(menu.getId())) {
                children.add(menu);
            }
            dictMap.put(menu.getId(), menu);
        });

        Set<AuMenu> result = Sets.newHashSet(children);
        for (AuMenu menu : children) {
            AuMenu parent = dictMap.get(menu.getParentId());
            int depth = 0;
            while (parent != null && depth < MAX_DEPTH) {
                result.add(parent);
                parent = dictMap.get(parent.getParentId());
                depth++;
            }
        }
        return result.stream().collect(Collectors.toList());
    }

    private static Set<AuPartyRelation> getAuPartyRelations(List<AuPartyRelation> children, Map<Long, AuPartyRelation> dictMap) {
        Set<AuPartyRelation> result = Sets.newHashSet(children);
        for (AuPartyRelation child : children) {
            AuPartyRelation parent = dictMap.get(child.getParentId());
            int depth = 0;
            while (parent != null && depth < MAX_DEPTH) {
                result.add(parent);
                parent = dictMap.get(parent.getParentId());
                depth++;
            }
        }
        return result;
    }


    public static void main(String[] args) {
    }
}
