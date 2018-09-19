package com.qslion.framework.service.impl;

import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.dao.IGenericHibernateDao;
import com.qslion.framework.service.IGenericHibernateService;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;


public abstract class GenericHibernateServiceImpl<T, PK extends Serializable> implements IGenericHibernateService<T, PK> {

    private IGenericHibernateDao<T, PK> baseDao;

    public IGenericHibernateDao<T, PK> getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(IGenericHibernateDao<T, PK> baseDao) {
        this.baseDao = baseDao;
    }

    public T get(PK id) {
        return baseDao.get(id);
    }

    public T load(PK id) {
        return baseDao.load(id);
    }

    public List<T> get(PK[] ids) {
        return baseDao.get(ids);
    }

    public T get(String propertyName, Object value) {
        return baseDao.get(propertyName, value);
    }

    public List<T> getList(String propertyName, Object value) {
        return baseDao.getList(propertyName, value);
    }

    public List<T> getAll() {
        return baseDao.getAll();
    }

    public Long getTotalCount() {
        return baseDao.getTotalCount();
    }

    public boolean isUnique(String propertyName, Object oldValue, Object newValue) {
        return baseDao.isUnique(propertyName, oldValue, newValue);
    }

    public boolean isExist(String propertyName, Object value) {
        return baseDao.isExist(propertyName, value);
    }

    public PK save(T entity) {
        return baseDao.save(entity);
    }

    public boolean update(T entity) {
        return baseDao.update(entity);
    }

    public boolean delete(T entity) {
        return baseDao.delete(entity);
    }

    public boolean delete(PK id) {
        return baseDao.delete(id);
    }

    public boolean delete(PK[] ids) {
        return baseDao.delete(ids);
    }

    public void flush() {
        baseDao.flush();
    }

    public void clear() {
        baseDao.clear();
    }

    public void evict(Object object) {
        baseDao.evict(object);
    }

    public Pager<T> findByPager(Pageable pageable) {
        return baseDao.findByPager(pageable);
    }

    public Pager<T> findByPager(Pageable pageable, DetachedCriteria detachedCriteria) {
        return baseDao.findByPager(pageable, detachedCriteria);
    }

    public T insert(T entity) {
        // TODO Auto-generated method stub
        return baseDao.insert(entity);
    }
}
