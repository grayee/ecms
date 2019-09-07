package com.qslion.framework.service.impl;

import com.qslion.framework.bean.BaseTree;
import com.qslion.framework.service.TreeInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 通用树形结构服务类
 *
 * @author Gray.Z
 * @date 2018/4/13 10:54.
 */
public abstract class TreeService<T extends BaseTree<T, ID>, ID extends Serializable> implements TreeInterface<T, ID> {

    public List<T> getChildTreeObjects(List<T> list, ID parentId) {
        List<T> returnList = new ArrayList<T>();
        for (T res : list) {
            //判断第一个对象是否为第一个节点
            if (res.getParentId() == parentId) {
                //相等--说明第一个节点为父节点--递归下面的子节点
                recursionFn(list, res);
                returnList.add(res);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    public void recursionFn(List<T> list, T t) {
        List<T> childsList = getChildList(list, t);
        //设置他的子集对象集
        t.setChildren(childsList);
        //迭代--这些子集的对象--时候还有下一级的子级对象
        for (T nextChild : childsList) {
            //下一个对象，与所有的资源集进行判断
            if (hasChild(list, nextChild)) {
                //有下一个子节点,递归
                Iterator<T> it = childsList.iterator();
                while (it.hasNext()) {
                    T node = it.next();
                    //所有的对象--跟当前这个childsList 的对象子节点
                    recursionFn(list, node);
                }
            }
        }
    }

    /**
     * 获得指定节点下的所有子节点
     *
     * @param list
     * @param t
     * @return
     */
    public List<T> getChildList(List<T> list, T t) {
        List<T> childsList = new ArrayList<T>();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            T child = it.next();
            //判断集合的父ID是否等于上一级的id
            if (((BaseTree<T, ID>) child).getParentId() == ((BaseTree<T, ID>) t).getId()) {
                childsList.add(child);
            }
        }
        return childsList;
    }

    /**
     * 判断是否还有下一个子节点
     *
     * @param list
     * @param t
     * @return
     */
    public boolean hasChild(List<T> list, T t) {
        return getChildList(list, t).size() > 0;
    }
}
