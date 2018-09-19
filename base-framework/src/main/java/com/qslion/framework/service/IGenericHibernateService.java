package com.qslion.framework.service;

import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;

/**
 * Service接口 - Service接口基类
 */
public interface IGenericHibernateService<T , PK extends Serializable> {
    /**
     * 根据ID获取实体对象.
     *
     * @param id 记录ID
     * @return 实体对象
     */
    public T load(PK id);


    /**
     * 根据属性名和属性值获取实体对象.
     *
     * @param propertyName 属性名称
     * @param value        属性值
     * @return 实体对象
     */
    public T get(String propertyName, Object value);

    /**
     * 根据属性名和属性值获取实体对象集合.
     *
     * @param propertyName 属性名称
     * @param value        属性值
     * @return 实体对象集合
     */
    public List<T> getList(String propertyName, Object value);


    /**
     * 根据属性名、修改前后属性值判断在数据库中是否唯一(若新修改的值与原来值相等则直接返回true).
     *
     * @param propertyName 属性名称
     * @param oldValue     修改前的属性值
     * @param oldValue     修改后的属性值
     * @return boolean
     */
    public boolean isUnique(String propertyName, Object oldValue, Object newValue);

    /**
     * 根据属性名判断数据是否已存在.
     *
     * @param propertyName 属性名称
     * @param value        值
     * @return boolean
     */
    public boolean isExist(String propertyName, Object value);

    /**
     * 保存实体对象.
     *
     * @param entity 对象
     * @return ID
     */
    public PK save(T entity);

    /**
     * 刷新session.
     */
    public void flush();

    /**
     * 清除Session.
     */
    public void clear();

    /**
     * 清除某一对象.
     *
     * @param object 需要清除的对象
     */
    public void evict(Object object);

    /**
     * 根据Pager和DetachedCriteria对象进行查询(提供分页、查找、排序功能).
     *
     * @param pager Pager对象
     * @return Pager对象
     */
    public Pager<T> findByPager(Pageable pageable, DetachedCriteria detachedCriteria);

}
