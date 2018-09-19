package com.qslion.framework.dao;

import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import java.io.Serializable;
import java.util.List;


/**
 * Dao接口 - hibernateDao基接口
 */
public abstract interface IGenericJdbcDao<E, PK extends Serializable> {


    /**
     * 添加实体对象.
     *
     * @param entity 对象
     * @return ID
     */
    public PK insert(E entity) throws Exception;

    /**
     * 更新实体对象.
     *
     * @param entity 对象
     */
    public boolean update(E entity) throws Exception;


    /**
     * 根据ID删除实体对象.
     *
     * @param id 记录ID
     */
    public boolean delete(PK id) throws Exception;


    /**
     * 按照指定的id进行的数据库的查询的操作
     *
     * @return Entity实体操作类的一个对象
     */
    public E findById(String id) throws Exception;

    /**
     * 返回查询的所有的对象数据的基本的信息
     *
     * @return 包含有entity实体的一个list集合
     */

    public List<E> findAll() throws Exception;

    /**
     * 根据Pager对象进行查询(提供分页、查找、排序功能).
     *
     * @param pager Pager对象(封装了数据对象)
     * @return Pager对象
     */
    public Pager<E> findByPager(Pageable pageable) throws Exception;


}