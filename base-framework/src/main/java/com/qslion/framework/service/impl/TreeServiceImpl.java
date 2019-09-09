package com.qslion.framework.service.impl;

import com.qslion.framework.entity.BaseTree;
import com.qslion.framework.service.TreeService;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 通用树形结构服务类
 *
 * @author Gray.Z
 * @date 2018/4/13 10:54.
 */
public abstract class TreeServiceImpl<T extends BaseTree<T, ID>, ID extends Serializable> extends GenericServiceImpl<T, ID> implements TreeService<T, ID> {

    public List<T> getChildTreeList(List<T> list, ID parentId) {
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


    private List<T> getChildren(List<T> list, T t) {
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


    public List<T> getChildList(List<T> list, T t) {
        List<T> childList = new ArrayList<>();
        list.forEach(child -> {
            //判断集合的父ID是否等于上一级的id
            if (child.getParentId() != null && child.getParentId().equals(t.getId())) {
                childList.add(child);
            }
        });
        return childList;
    }


    public boolean hasChild(List<T> list, T t) {
        return getChildList(list, t).size() > 0;
    }
}
