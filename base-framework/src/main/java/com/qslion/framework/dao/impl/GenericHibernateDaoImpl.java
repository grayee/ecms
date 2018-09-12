package com.qslion.framework.dao.impl;

import com.qslion.framework.bean.Pager;
import com.qslion.framework.dao.IGenericHibernateDao;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.Assert;

/**
 * Dao实现类 - Dao实现类基类
 */

//@Repository
public class GenericHibernateDaoImpl<T, PK extends Serializable> implements IGenericHibernateDao<T, PK> {

    private static final Logger logger = LogManager.getLogger();
    protected Class<T> entityClass;
    protected SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public GenericHibernateDaoImpl() {
        this.entityClass = null;
        Class c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            this.entityClass = (Class<T>) parameterizedType[0];
        }
    }

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public T get(PK id) {
        Assert.notNull(id, "id is required");
        return (T) getSession().get(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    public T load(PK id) {
        Assert.notNull(id, "id is required");
        return (T) getSession().load(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> get(PK[] ids) {
        Assert.notEmpty(ids, "ids must not be empty");
        String hql = "from " + entityClass.getName() + " as model where model.id in(:ids)";
        return getSession().createQuery(hql).setParameterList("ids", ids).list();
    }

    @SuppressWarnings("unchecked")
    public T get(String propertyName, Object value) {
        Assert.hasText(propertyName, "propertyName must not be empty");
        Assert.notNull(value, "value is required");
        String hql = "from " + entityClass.getName() + " as model where model." + propertyName + " = ?";
        return (T) getSession().createQuery(hql).setParameter(0, value).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<T> getList(String propertyName, Object value) {
        Assert.hasText(propertyName, "propertyName must not be empty");
        Assert.notNull(value, "value is required");
        String hql = "from " + entityClass.getName() + " as model where model." + propertyName + " = ?";
        return getSession().createQuery(hql).setParameter(0, value).list();
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        String hql = "from " + entityClass.getName();
        return getSession().createQuery(hql).list();
    }

    public Long getTotalCount() {
        String hql = "select count(*) from " + entityClass.getName();
        return (Long) getSession().createQuery(hql).uniqueResult();
    }

    public boolean isUnique(String propertyName, Object oldValue, Object newValue) {
        Assert.hasText(propertyName, "propertyName must not be empty");
        Assert.notNull(newValue, "newValue is required");
        if (newValue == oldValue || newValue.equals(oldValue)) {
            return true;
        }
        if (newValue instanceof String) {
            if (oldValue != null && StringUtils.equalsIgnoreCase((String) oldValue, (String) newValue)) {
                return true;
            }
        }
        T object = get(propertyName, newValue);
        return (object == null);
    }

    public boolean isExist(String propertyName, Object value) {
        Assert.hasText(propertyName, "propertyName must not be empty");
        Assert.notNull(value, "value is required");
        T object = get(propertyName, value);
        return (object != null);
    }

    @SuppressWarnings("unchecked")
    public PK save(T entity) {
        Assert.notNull(entity, "entity is required");
        return (PK) getSession().save(entity);
    }

    public boolean update(T entity) {
        Assert.notNull(entity, "entity is required");
        try {
            getSession().update(entity);
            logger.debug("update successful");
        } catch (RuntimeException re) {
            logger.error("update failed", re);
            throw re;
        }
        return true;
    }

    public boolean delete(T entity) {
        Assert.notNull(entity, "entity is required");
        try {
            getSession().delete(entity);
            logger.debug("delete successful");
        } catch (RuntimeException re) {
            logger.error("delete failed", re);
            throw re;
        }
        return true;
    }

    public boolean delete(PK id) {
        Assert.notNull(id, "id is required");
        T entity = load(id);
        try {
            getSession().delete(entity);
            logger.debug("delete successful");
        } catch (RuntimeException re) {
            logger.error("delete failed", re);
            throw re;
        }
        return true;
    }

    public boolean delete(PK[] ids) {
        Assert.notEmpty(ids, "ids must not be empty");
        for (PK id : ids) {
            T entity = load(id);
            try {
                getSession().delete(entity);
                logger.debug("delete successful");
            } catch (RuntimeException re) {
                logger.error("delete failed", re);
                throw re;
            }
        }
        return true;
    }

    public void flush() {
        getSession().flush();
    }

    public void clear() {
        getSession().clear();
    }

    public void evict(Object object) {
        Assert.notNull(object, "object is required");
        getSession().evict(object);
    }

    public Pager<T> findByPager(Pager<T> pager) {
        if (pager == null) {
            pager = new Pager<T>();
        }
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
        return findByPager(pager, detachedCriteria);
    }

    public Pager<T> findByPager(Pager<T> pager, DetachedCriteria detachedCriteria) {
        if (pager == null) {
            pager = new Pager<T>();
        }
        Integer pageNumber = pager.getPageNumber();
        Integer pageSize = pager.getPageSize();
        String property = pager.getProperty();
        String keyword = pager.getKeyword();
        String orderBy = pager.getOrderBy();
        Pager.OrderType orderType = pager.getOrderType();

        Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
        //分页查询条件
        T entity = pager.getEntity();
        if (entity != null) {
            Class<? extends Object> entityClazz = entity.getClass();
            Field[] entityFields = entityClazz.getDeclaredFields();
            for (Field field : entityFields) {
                if ("serialVersionUID".equals(field.getName())) {
                    continue;
                }
                String filedName = field.getName();
                PropertyDescriptor pd = null;
                Object fieldValue = "";
                try {
                    pd = new PropertyDescriptor(filedName, entityClazz);
                    Method getMethod = pd.getReadMethod();
                    fieldValue = getMethod.invoke(entity);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (StringUtils.isNotEmpty(filedName) && fieldValue != null) {
                    logger.info("entity field name :" + filedName + ",field value string:" + fieldValue.toString());
                    if (fieldValue instanceof Collection<?>) {
                        Collection<?> c = (Collection<?>) fieldValue;
                        if (c.size() > 0) {
                            criteria.add(Restrictions.eq(filedName, fieldValue));
                        }
                    } else {
                        criteria.add(Restrictions.eq(filedName, fieldValue));
                    }
                }
            }
        }

        //增加属性查询条件
        if (StringUtils.isNotEmpty(property) && StringUtils.isNotEmpty(keyword)) {
            String propertyString = "";
            if (property.contains(".")) {
                String propertyPrefix = StringUtils.substringBefore(property, ".");
                String propertySuffix = StringUtils.substringAfter(property, ".");
                criteria.createAlias(propertyPrefix, "model");
                propertyString = "model." + propertySuffix;
            } else {
                propertyString = property;
            }
            criteria.add(Restrictions.like(propertyString, "%" + keyword + "%"));
        }
        Integer totalCount = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();

        criteria.setProjection(null);
        //CriteriaSpecification 为 Criteria 的父接口
        criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        if (StringUtils.isNotEmpty(orderBy) && orderType != null) {
            if (orderType == Pager.OrderType.asc) {
                criteria.addOrder(Order.asc(orderBy));
            } else {
                criteria.addOrder(Order.desc(orderBy));
            }
        }
        pager.setTotalCount(totalCount);

        pager.setList(criteria.list());
        return pager;
    }

    public T insert(T entity) {
        // TODO Auto-generated method stub
        Assert.notNull(entity, "entity is required");
        return (T) getSession().save(entity);
    }


}
