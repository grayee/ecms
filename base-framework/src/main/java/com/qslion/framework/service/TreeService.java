package com.qslion.framework.service;

import com.qslion.framework.entity.BaseTree;

import java.io.Serializable;
import java.util.List;

/**
 * 通用树形结构服务类
 *
 * @author Gray.Z
 * @date 2018/4/13 10:54.
 */
public interface TreeService<T extends BaseTree<T, ID>, ID extends Serializable> extends IGenericService<T, ID>  {
    /**
     * 获得指定节点下所有归档
     *
     * @param list     列表数据
     * @param parentId 父id
     * @return List
     */
    List<T> getChildTreeList(List<T> list, ID parentId);


    /**
     * 获得指定节点下的所有子节点
     *
     * @param list List
     * @param t    指定节点
     * @return List
     */
    List<T> getChildList(List<T> list, T t);

    /**
     * 判断是否还有下一个子节点
     *
     * @param list List
     * @param t    指定节点
     * @return boolean
     */
    boolean hasChild(List<T> list, T t);
}
