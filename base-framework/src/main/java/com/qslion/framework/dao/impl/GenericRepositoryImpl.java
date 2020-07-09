package com.qslion.framework.dao.impl;

import com.qslion.framework.bean.NestTreeable;
import com.qslion.framework.dao.IGenericRepository;
import com.qslion.framework.entity.BaseEntity;
import com.qslion.framework.entity.BaseTree;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Repository类-基类
 *
 * @author Gray.Z
 * @date 2018/4/3 20:25.
 */
public class GenericRepositoryImpl<T extends BaseEntity<ID>, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements IGenericRepository<T, ID> {

    private static final int BATCH_SIZE = 500;
    protected EntityManager entityManager;

    public GenericRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    public GenericRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public <S extends T> Iterable<S> batchSave(Iterable<S> var1) {
        Iterator<S> iterator = var1.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            entityManager.persist(iterator.next());
            index++;
            if (index % BATCH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        if (index % BATCH_SIZE != 0) {
            entityManager.flush();
            entityManager.clear();
        }
        return var1;
    }

    @Override
    @Transactional
    public <S extends T> Iterable<S> batchUpdate(Iterable<S> var1) {
        Iterator<S> iterator = var1.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            entityManager.merge(iterator.next());
            index++;
            if (index % BATCH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        if (index % BATCH_SIZE != 0) {
            entityManager.flush();
            entityManager.clear();
        }
        return var1;
    }

    public T treeSave(T entity) {
        BaseTree<ID> tree = (BaseTree<ID>) entity;
        String parentId = String.valueOf(tree.getParentId());
        String beanName = tree.getClass().getName();
        Integer parentRgt;
        if (parentId != null && !"".equals(parentId)) {
            // 如果父节点不为null，则获取节点的右边位置
            String hql = "select bean." + tree.getRgtName() + " from " + beanName + " bean where bean.id=:pid";
            parentRgt = ((Number) entityManager.createQuery(hql).setParameter("pid", parentId)).intValue();
            String uptRgtHql = "update " + beanName + " bean set bean." + tree.getRgtName() + " = bean." + tree.getRgtName() + " + 2 WHERE bean." + tree
                    .getRgtName() + " >= :parentRgt";
            String uptLftHql = "update " + beanName + " bean set bean." + tree.getLftName() + " = bean." + tree.getLftName() + " + 2 WHERE bean." + tree
                    .getLftName() + " >= :parentRgt";
            if (!StringUtils.isBlank(tree.getTreeCondition())) {
                uptRgtHql += " and (" + tree.getTreeCondition() + ")";
                uptLftHql += " and (" + tree.getTreeCondition() + ")";
            }
            // logger.info("NestTreeServiceImpl.insert uptRgtHql is:{},uptLftHql is:{}", uptRgtHql, uptLftHql);
            entityManager.createQuery(uptRgtHql).setParameter("parentRgt", parentRgt).executeUpdate();
            entityManager.createQuery(uptLftHql).setParameter("parentRgt", parentRgt).executeUpdate();
        } else {
            // 否则查找最大的右边位置
            String hql = "select max(bean." + tree.getRgtName() + ") from " + beanName + " bean";
            if (!StringUtils.isBlank(tree.getTreeCondition())) {
                hql += " where " + tree.getTreeCondition();
            }
            Number myPositionNumber = (Number) entityManager.createQuery(hql);
            // 如不存在，则为1
            if (myPositionNumber == null) {
                parentRgt = 1;
            } else {
                parentRgt = myPositionNumber.intValue() + 1;
            }
        }


        return this.save((T) tree);
    }

    /**
     * l
     * 查Map结果集
     */
    public void getMapByHql() {
        String hql = "select new Map(s.name as name, s.age as age) from Student s";
        Query query = entityManager.createQuery(hql);
        List<Map<String, Object>> list = query.getResultList();
    }
}

