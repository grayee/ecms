package com.qslion.framework.service;

import com.qslion.framework.bean.BaseTree;

import java.io.Serializable;
import java.util.List;

/**
 * 通用树形结构服务类
 *
 * @author Gray.Z
 * @date 2018/4/13 10:54.
 */
public interface TreeInterface<T extends BaseTree<T, ID>, ID extends Serializable> {
    /**
     * 获得指定节点下所有归档
     *
     * @param list
     * @param parentId
     * @return
     */
    List<T> getChildTreeObjects(List<T> list, ID parentId);

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    void recursionFn(List<T> list, T t);

    /**
     * 获得指定节点下的所有子节点
     *
     * @param list
     * @param t
     * @return
     */
    List<T> getChildList(List<T> list, T t);

    /**
     * 判断是否还有下一个子节点
     *
     * @param list
     * @param t
     * @return
     */
    boolean hasChild(List<T> list, T t);
}
