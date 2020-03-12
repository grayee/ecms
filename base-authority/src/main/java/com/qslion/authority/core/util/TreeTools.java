/**
 *
 */
package com.qslion.authority.core.util;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.qslion.framework.entity.AbstractTree;
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
    public static <T extends AbstractTree<Long>> String getPathTreeStr(List<T> list, Long parentId) {
        Map<Long, AbstractTree<Long>> dictMap = Maps.newHashMapWithExpectedSize(list.size());
        list.forEach(tree -> dictMap.put(tree.getId(), tree));
        List<String> orgList = Lists.newArrayList();
        AbstractTree<Long> parent = dictMap.get(parentId);
        int depth = 0;
        while (parent != null && depth < MAX_DEPTH) {
            orgList.add(parent.getName());
            parent = dictMap.get(parent.getParentId());
            depth++;
        }
        Collections.reverse(orgList);
        return Joiner.on(GlobalConstants.ORG_TREE_SEPARATOR).join(orgList);
    }

    /**
     * 树结构数据
     *
     * @param dataList      全集数据
     * @param targetIds 目标数据ID
     * @param <T>       数据类型
     * @return List<T>
     */
    public static <T extends AbstractTree<Long>> List<T> filterTreePath(List<T> dataList, List<Long> targetIds) {
        List<T> children = Lists.newArrayList();
        Map<Long, T> dictMap = Maps.newHashMapWithExpectedSize(dataList.size());
        dataList.forEach(data -> {
            dictMap.put(data.getId(), data);
            if (targetIds.contains(data.getId())) {
                children.add(data);
            }
        });
        return getTs(children, dictMap);
    }

    private static <T extends AbstractTree<Long>> List<T> getTs(List<T> children, Map<Long, T> dictMap) {
        Set<T> result = Sets.newHashSet(children);
        for (T t : children) {
            T parent = dictMap.get(t.getParentId());
            int depth = 0;
            //自下而上遍历
            while (parent != null && depth < MAX_DEPTH) {
                result.add(parent);
                parent = dictMap.get(parent.getParentId());
                depth++;
            }
        }
        return result.stream().collect(Collectors.toList());
    }

    public static <T extends AbstractTree<Long>> List<T> getChildTreeList(List<T> list) {
        Map<Boolean, List<T>> dMap = list.stream().collect(Collectors.groupingBy(t -> t.getParentId() == null));
        List<T> rootList = dMap.get(true);
        List<T> subList = dMap.get(false);
        List<T> returnList = new ArrayList<>();
        for (T root : rootList) {
            root.setChildren(getChildren(subList, root));
            returnList.add(root);
        }
        return returnList;
    }

    private static <T extends AbstractTree<Long>> List<T> getChildren(List<T> list, T t) {
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

    private static <T extends AbstractTree<Long>> List<T> getChildList(List<T> list, T t) {
        List<T> childList = new ArrayList<>();
        list.forEach(child -> {
            //判断集合的父ID是否等于上一级的id
            if (child.getParentId() != null && child.getParentId().equals(t.getId())) {
                childList.add(child);
            }
        });
        return childList;
    }


    public static <T extends AbstractTree<Long>> boolean hasChild(List<T> list, T t) {
        return getChildList(list, t).size() > 0;
    }


    public <T extends AbstractTree<Long>> List<T> getTree(List<T> list) {   //调用的方法入口
        Map<Boolean, List<T>> dMap = list.stream().collect(Collectors.groupingBy(t -> t.getParentId() == null));
        List<T> rootList = dMap.get(true);
        List<T> bodyList = dMap.get(false);

        if (bodyList != null && !bodyList.isEmpty()) {
            //声明一个map，用来过滤已操作过的数据
            Map<Long, Long> map = Maps.newHashMapWithExpectedSize(bodyList.size());
            rootList.forEach(node -> getChild(node, bodyList, map));
            return rootList;
        }
        return null;
    }

    private <T extends AbstractTree<Long>> void getChild(T node, List<T> bodyList, Map<Long, Long> map) {
        List<T> childList = Lists.newArrayList();
        List<T> filteredList = bodyList.stream().filter(c -> !map.containsKey(c.getId()))
                .filter(c -> c.getParentId().equals(node.getId()))
                .collect(Collectors.toList());

        filteredList.forEach(c -> {
            map.put(c.getId(), c.getParentId());
            getChild(c, filteredList, map);
            childList.add(c);
        });
        node.setChildren(childList);
    }

    public static void main(String[] args) {
    }
}
