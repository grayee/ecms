package com.qslion.framework.dao;

import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import java.io.Serializable;
import java.util.List;

/**
 * Dao接口 - Dao基接口 - CRUD 基本操作
 */
public abstract interface IGenericDao<E, PK extends Serializable> {
    /**
     * 根据ID获取实体对象.
     *
     * @param id 记录ID
     * @return 实体对象
     */
    public E get(PK id);

    /**
     * 根据ID数组获取实体对象集合.
     *
     * @param ids ID对象数组
     * @return 实体对象集合
     */
    public List<E> get(PK[] ids);

    /**
     * 获取所有实体对象集合.
     *
     * @return 实体对象集合
     */
    public List<E> getAll();

    /**
     * 获取所有实体对象总数.
     *
     * @return 实体对象总数
     */
    public Long getTotalCount();


    /**
     * 添加实体对象.
     *
     * @param entity 对象
     * @return ID
     */
    public E insert(E entity);

    /**
     * 更新实体对象.
     *
     * @param entity 对象
     */
    public boolean update(E entity);

    /**
     * 删除实体对象.
     *
     * @param entity 对象
     */
    public boolean delete(E entity);

    /**
     * 根据ID删除实体对象.
     *
     * @param id 记录ID
     */
    public boolean delete(PK id);

    /**
     * 根据ID数组删除实体对象.
     *
     * @param ids ID数组
     */
    public boolean delete(PK[] ids);


    /**
     * 根据Pager对象进行查询(提供分页、查找、排序功能).
     *
     * @param pager Pager对象(封装了数据对象)
     * @return Pager对象
     */
    public Pager<E> findByPager(Pageable pageable);
}