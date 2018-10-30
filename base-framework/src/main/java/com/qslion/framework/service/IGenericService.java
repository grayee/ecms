

package com.qslion.framework.service;

import com.qslion.framework.bean.QueryFilter;
import com.qslion.framework.bean.Order;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.bean.Pageable;
import com.qslion.framework.entity.BaseEntity;
import java.io.Serializable;
import java.util.List;

/**
 * 通用service接口
 *
 * @author Gray.Z
 * @date 2018/4/13 10:54.
 */
public interface IGenericService<T extends BaseEntity<ID>, ID extends Serializable> {

    /**
     * 根据ID查找实体对象
     *
     * @param id ID
     * @return 实体对象，若不存在则返回null
     */
    T findById(ID id);

    /**
     * 查找所有实体对象集合
     *
     * @return 所有实体对象集合
     */
    List<T> findAll();

    /**
     * 查找实体对象集合
     *
     * @param ids ID
     * @return 实体对象集合
     */
    List<T> findList(ID... ids);

    /**
     * 查找实体对象集合
     *
     * @param count 数量
     * @param queryFilters 筛选
     * @param orders 排序
     * @return 实体对象集合
     */
    List<T> findList(Integer count, List<QueryFilter> queryFilters, List<Order> orders);

    /**
     * 查找实体对象集合
     *
     * @param first 起始记录
     * @param count 数量
     * @param queryFilters 筛选
     * @param orders 排序
     * @return 实体对象集合
     */
    List<T> findList(Integer first, Integer count, List<QueryFilter> queryFilters, List<Order> orders);

    /**
     * 查找实体对象分页
     *
     * @param pageable 分页信息
     * @return 实体对象分页
     */
    Pager<T> findPage(Pageable pageable);

    /**
     * 查询实体对象总数
     *
     * @return 实体对象总数
     */
    long count();

    /**
     * 查询实体对象数量
     *
     * @param queryFilters 筛选
     * @return 实体对象数量
     */
    long count(QueryFilter... queryFilters);

    /**
     * 判断实体对象是否存在
     *
     * @param id ID
     * @return 实体对象是否存在
     */
    boolean exists(ID id);

    /**
     * 判断实体对象是否存在
     *
     * @param queryFilters 筛选
     * @return 实体对象是否存在
     */
    boolean exists(QueryFilter... queryFilters);

    /**
     * 保存实体对象
     *
     * @param entity 实体对象
     * @return 实体对象
     */
    T save(T entity);

    /**
     * 更新实体对象
     *
     * @param entity 实体对象
     * @return 实体对象
     */
    T update(T entity);

    /**
     * 更新实体对象
     *
     * @param entity 实体对象
     * @param ignoreProperties 忽略属性
     * @return 实体对象
     */
    T update(T entity, String... ignoreProperties);

    /**
     * 删除实体对象
     *
     * @param id ID
     */
    void delete(ID id);

    /**
     * 删除实体对象
     *
     * @param entities entities
     */
    void delete(List<T> entities);

    /**
     * 删除实体对象
     *
     * @param entity 实体对象
     */
    void delete(T entity);
}
