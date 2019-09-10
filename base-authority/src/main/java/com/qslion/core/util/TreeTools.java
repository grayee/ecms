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
import com.qslion.core.enums.AuPartyType;
import com.qslion.framework.entity.BaseTree;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;
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
     * @param list     list
     * @param parentId parentId
     * @return String
     */
    public static <T extends BaseTree<Long>> String getPathTreeStr(List<T> list, Long parentId) {
        Map<Long, BaseTree<Long>> dictMap = Maps.newHashMapWithExpectedSize(list.size());
        list.forEach(tree -> dictMap.put(tree.getId(), tree));
        List<String> orgList = Lists.newArrayList();
        BaseTree<Long> parent = dictMap.get(parentId);
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


    public static <T extends BaseTree<Long>> List<T> getChildTreeList(List<T> list, Long parentId) {
        List<T> returnList = new ArrayList<>();
        for (T res : list) {
            //判断第一个对象是否为第一个节点
            if (res.getParentId() != null && res.getParentId().equals(parentId)) {
                //相等--说明第一个节点为父节点--递归下面的子节点
                res.setChildren(getChildren(list, res));
                returnList.add(res);
            }
        }
        return returnList;
    }


    private static <T extends BaseTree<Long>> List<T> getChildren(List<T> list, T t) {
        List<T> childList = getChildList(list, t);
        list = list.stream().filter(l -> !t.getId().equals(l.getId())).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(childList)) {
            //迭代--这些子集的对象--时候还有下一级的子级对象
            for (T nextChild : childList) {
                //下一个对象，与所有的资源集进行判断
                if (hasChild(list, nextChild)) {
                    //有下一个子节点,递归 所有的对象--跟当前这个childList 的对象子节点
                    nextChild.setChildren(getChildren(list, nextChild));
                }
            }
        }
        return childList;
    }


    private static <T extends BaseTree<Long>> List<T> getChildList(List<T> list, T t) {
        List<T> childList = new ArrayList<>();
        list.forEach(child -> {
            //判断集合的父ID是否等于上一级的id
            if (child.getParentId() != null && child.getParentId().equals(t.getId())) {
                childList.add(child);
            }
        });
        return childList;
    }


    private static <T extends BaseTree<Long>> boolean hasChild(List<T> list, T t) {
        return getChildList(list, t).size() > 0;
    }


    public static void main(String[] args) {
    }
}
