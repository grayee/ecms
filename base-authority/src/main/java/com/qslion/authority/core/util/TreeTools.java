/**
 *
 */
package com.qslion.authority.core.util;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.qslion.framework.bean.NestTreeable;
import com.qslion.framework.bean.TreeNode;
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
     * 根据parentId 获取树路径字符串
     * eg：XX集团->XX分公司->XX中支公司
     *
     * @param dataList 全集数据
     * @param parentId parentId
     * @return String
     */
    public static <T extends BaseTree<Long>> String getTreePathStr(List<T> dataList, Long parentId) {
        Map<Long, BaseTree<Long>> dictMap = Maps.newHashMapWithExpectedSize(dataList.size());
        dataList.forEach(tree -> dictMap.put(tree.getId(), tree));
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

    /**
     * 指定ID集合的树结构数据
     *
     * @param dataList  全集数据
     * @param targetIds 目标数据ID
     * @param <T>       数据类型
     * @return List<T>
     */
    public static <T extends BaseTree<Long>> List<T> getTargetTreePath(List<T> dataList, List<Long> targetIds) {
        List<T> children = Lists.newArrayList();
        Map<Long, T> dictMap = Maps.newHashMapWithExpectedSize(dataList.size());
        dataList.forEach(data -> {
            dictMap.put(data.getId(), data);
            if (targetIds.contains(data.getId())) {
                children.add(data);
            }
        });
        return getChildrenParent(children, dictMap);
    }

    /**
     * 自下而上查找下级节点的父节点
     *
     * @param children 下级节点
     * @param dictMap  数据字典
     * @param <T>      数据类型
     * @return List<T> 下级节点
     */
    private static <T extends BaseTree<Long>> List<T> getChildrenParent(List<T> children, Map<Long, T> dictMap) {
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

    /**
     * treeNode树
     *
     * @param dataList 上下级关系数据集
     * @param <T>      BaseTree
     * @return List<TreeNode>
     */
    public static <T extends BaseTree<Long>> List<TreeNode> getTreeList(List<T> dataList) {
        return getTreeList(dataList, null);
    }

    /**
     * treeNode树
     *
     * @param dataList   上下级关系数据集
     * @param checkedIds 选择状态的数据
     * @param <T>        BaseTree
     * @return List<TreeNode>
     */
    public static <T extends BaseTree<Long>> List<TreeNode> getTreeList(List<T> dataList, List<String> checkedIds) {
        Map<Boolean, List<T>> dMap = dataList.stream().collect(Collectors.groupingBy(t -> t.getParentId() == null));
        List<T> rootList = dMap.get(true);
        List<T> subList = dMap.get(false);
        List<TreeNode> treeList = new ArrayList<>();
        if (subList != null && !subList.isEmpty()) {
            for (T rootNode : rootList) {
                TreeNode rootTreeNode = rootNode.getTreeNode();
                if (CollectionUtils.isNotEmpty(checkedIds) && checkedIds.contains(rootTreeNode.getId())) {
                    rootTreeNode.setChecked(true);
                }
                rootTreeNode.setChildren(getChildren(subList, rootNode, checkedIds));
                treeList.add(rootTreeNode);
            }
        }
        return treeList;
    }

    /**
     * 递归获取下级节点treeNode
     *
     * @param subList    下级节点集合
     * @param parent     上级节点
     * @param checkedIds 选择状态的数据
     * @return List<TreeNode>
     */
    private static <T extends BaseTree<Long>> List<TreeNode> getChildren(List<T> subList, T parent, List<String> checkedIds) {
        subList = subList.stream().filter(c -> !parent.getId().equals(c.getId())).collect(Collectors.toList());
        List<T> childList = getChildList(subList, parent);
        List<TreeNode> children = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(childList)) {
            //迭代--这些子集的对象--时候还有下一级的子级对象
            for (T nextChild : childList) {
                TreeNode treeNode = nextChild.getTreeNode();
                if (CollectionUtils.isNotEmpty(checkedIds) && checkedIds.contains(treeNode.getId())) {
                    treeNode.setChecked(true);
                }
                //下一个对象，与所有的资源集进行判断
                if (hasChild(subList, nextChild)) {
                    //有下一个子节点,递归 所有的对象--跟当前这个childList 的对象子节点
                    treeNode.setChildren(getChildren(subList, nextChild, checkedIds));
                }
                children.add(treeNode);
            }
        }
        return children;
    }

    private static <T extends BaseTree<Long>> List<T> getChildList(List<T> dataList, T t) {
        List<T> childList = new ArrayList<>();
        dataList.forEach(child -> {
            //判断集合的父ID是否等于上一级的id
            if (child.getParentId() != null && child.getParentId().equals(t.getId())) {
                childList.add(child);
            }
        });
        return childList;
    }


    public static <T extends BaseTree<Long>> boolean hasChild(List<T> list, T t) {
        return getChildList(list, t).size() > 0;
    }

}
