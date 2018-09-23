package com.qslion.framework.interceptor;

import com.qslion.framework.bean.NestTreeable;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.EmptyInterceptor;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.Type;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 过滤器 - 自动填充创建、更新时间、嵌套集合树模型算法实现
 */

@Component
public class EntityInterceptor extends EmptyInterceptor implements ApplicationContextAware {

    public static final String SESSION_FACTORY = "sessionFactory";
    private static final long serialVersionUID = 7319416231145791577L;

    private ApplicationContext appCtx;
    private SessionFactory sessionFactory;

    private EntityManager entityManager;

    // 保存数据时回调此方法
    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {

        if (entity instanceof NestTreeable) {
            NestTreeable<?> tree = (NestTreeable<?>) entity;
            String parentId = (String) tree.getParentId();
            String beanName = tree.getClass().getName();
            Session session = getSession();
            FlushModeType model = session.getFlushMode();
            session.setFlushMode(FlushMode.MANUAL);
            Integer parentRgt;
            if (parentId != null && !"".equals(parentId)) {
                // 如果父节点不为null，则获取节点的右边位置
                String hql = "select bean." + tree.getRgtName() + " from " + beanName + " bean where bean.id=:pid";
                parentRgt = ((Number) session.createQuery(hql).setParameter("pid", parentId).uniqueResult()).intValue();
                String hql1 =
                    "update " + beanName + " bean set bean." + tree.getRgtName() + " = bean." + tree.getRgtName() + " + 2 WHERE bean." + tree
                        .getRgtName() + " >= :parentRgt";
                String hql2 =
                    "update " + beanName + " bean set bean." + tree.getLftName() + " = bean." + tree.getLftName() + " + 2 WHERE bean." + tree
                        .getLftName() + " >= :parentRgt";
                if (!StringUtils.isBlank(tree.getTreeCondition())) {
                    hql1 += " and (" + tree.getTreeCondition() + ")";
                    hql2 += " and (" + tree.getTreeCondition() + ")";
                }
                System.out.println(parentId + "=====" + parentRgt + "=========" + hql + "===========" + hql1 + "===============" + hql2);
                session.createQuery(hql1).setParameter("parentRgt", parentRgt).executeUpdate();
                session.createQuery(hql2).setParameter("parentRgt", parentRgt).executeUpdate();
            } else {
                // 否则查找最大的右边位置
                String hql = "select max(bean." + tree.getRgtName() + ") from " + beanName + " bean";
                if (!StringUtils.isBlank(tree.getTreeCondition())) {
                    hql += " where " + tree.getTreeCondition();
                }
                Number myPositionNumber = (Number) session.createQuery(hql).uniqueResult();
                // 如不存在，则为0
                if (myPositionNumber == null) {
                    parentRgt = 1;
                } else {
                    parentRgt = myPositionNumber.intValue() + 1;
                }
            }
            session.setFlushMode(model);
            for (int i = 0; i < propertyNames.length; i++) {
                if (propertyNames[i].equals(tree.getLftName())) {
                    state[i] = parentRgt;
                }
                if (propertyNames[i].equals(tree.getRgtName())) {
                    state[i] = parentRgt + 1;
                }
            }
        }
        return true;
    }

    // 更新数据时回调此方法
    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {

        if (entity instanceof NestTreeable) {
            NestTreeable<?> tree = (NestTreeable<?>) entity;
            for (int i = 0; i < propertyNames.length; i++) {
                if (propertyNames[i].equals(tree.getParentName())) {
                    NestTreeable<?> preParent = (NestTreeable<?>) previousState[i];
                    NestTreeable<?> currParent = (NestTreeable<?>) currentState[i];
                    return updateParent(tree, preParent, currParent);
                }
            }
        }
        return true;
    }


    private boolean updateParent(NestTreeable<?> tree, NestTreeable<?> preParent, NestTreeable<?> currParent) {
        // 都为空、或都不为空且相等时，不作处理
        if ((preParent == null && currParent == null) || (preParent != null && currParent != null && preParent.getId().equals(currParent.getId()))) {
            return false;
        }
        String beanName = tree.getClass().getName();
        Session session = getSession();
        // 保存刷新模式，并设置成手动刷新
        FlushModeType model = session.getFlushMode();
        session.setFlushMode(FlushMode.MANUAL);
        // 先空出位置。当前父节点存在时，才需要空出位置。
        Integer currParentRgt;
        if (currParent != null) {
            // 获得节点跨度
            String hql = "select bean." + tree.getLftName() + ",bean." + tree.getRgtName() + " from " + beanName + " bean where bean.id=:id";
            Object[] position = (Object[]) session.createQuery(hql).setParameter("id", tree.getId()).uniqueResult();
            int nodeLft = ((Number) position[0]).intValue();
            int nodeRgt = ((Number) position[1]).intValue();
            int span = nodeRgt - nodeLft + 1;

            // 获得当前父节点右位置
            Object[] currPosition = (Object[]) session.createQuery(hql).setParameter("id", currParent.getId()).uniqueResult();
            int currParentLft = ((Number) currPosition[0]).intValue();
            currParentRgt = ((Number) currPosition[1]).intValue();

            // 空出位置
            String hql1 =
                "update " + beanName + " bean set bean." + tree.getRgtName() + " = bean." + tree.getRgtName() + " + " + span + " WHERE bean." + tree
                    .getRgtName() + " >= :parentRgt";
            String hql2 =
                "update " + beanName + " bean set bean." + tree.getLftName() + " = bean." + tree.getLftName() + " + " + span + " WHERE bean." + tree
                    .getLftName() + " >= :parentRgt";
            if (!StringUtils.isBlank(tree.getTreeCondition())) {
                hql1 += " and (" + tree.getTreeCondition() + ")";
                hql2 += " and (" + tree.getTreeCondition() + ")";
            }
            session.createQuery(hql1).setInteger("parentRgt", currParentRgt).executeUpdate();
            session.createQuery(hql2).setInteger("parentRgt", currParentRgt).executeUpdate();
        } else {
            // 否则查找最大的右边位置
            String hql = "select max(bean." + tree.getRgtName() + ") from " + beanName + " bean";
            if (!StringUtils.isBlank(tree.getTreeCondition())) {
                hql += " where " + tree.getTreeCondition();
            }
            currParentRgt = ((Number) session.createQuery(hql).uniqueResult()).intValue();
            currParentRgt++;
        }

        // 再调整自己
        String hql = "select bean." + tree.getLftName() + ",bean." + tree.getRgtName() + " from " + beanName + " bean where bean.id=:id";
        Object[] position = (Object[]) session.createQuery(hql).setParameter("id", tree.getId()).uniqueResult();
        int nodeLft = ((Number) position[0]).intValue();
        int nodeRgt = ((Number) position[1]).intValue();
        int span = nodeRgt - nodeLft + 1;
        int offset = currParentRgt - nodeLft;
        hql = "update " + beanName + " bean set bean." + tree.getLftName() + "=bean." + tree.getLftName() + "+:offset, bean." + tree.getRgtName()
            + "=bean." + tree.getRgtName() + "+:offset WHERE bean." + tree.getLftName() + " between :nodeLft and :nodeRgt";
        if (!StringUtils.isBlank(tree.getTreeCondition())) {
            hql += " and (" + tree.getTreeCondition() + ")";
        }
        session.createQuery(hql).setParameter("offset", offset).setParameter("nodeLft", nodeLft).setParameter("nodeRgt", nodeRgt).executeUpdate();

        // 最后删除（清空位置）
        String hql1 =
            "update " + beanName + " bean set bean." + tree.getRgtName() + " = bean." + tree.getRgtName() + " - " + span + " WHERE bean." + tree
                .getRgtName() + " > :nodeRgt";
        String hql2 =
            "update " + beanName + " bean set bean." + tree.getLftName() + " = bean." + tree.getLftName() + " - " + span + " WHERE bean." + tree
                .getLftName() + " > :nodeRgt";
        if (!StringUtils.isBlank(tree.getTreeCondition())) {
            hql1 += " and (" + tree.getTreeCondition() + ")";
            hql2 += " and (" + tree.getTreeCondition() + ")";
        }
        session.createQuery(hql1).setParameter("nodeRgt", nodeRgt).executeUpdate();
        session.createQuery(hql2).setParameter("nodeRgt", nodeRgt).executeUpdate();
        session.setFlushMode(model);
        return true;
    }

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if (entity instanceof NestTreeable) {
            NestTreeable<?> tree = (NestTreeable<?>) entity;
            String beanName = tree.getClass().getName();
            Session session = getSession();
            FlushModeType model = session.getFlushMode();
            session.setFlushMode(FlushMode.MANUAL);
            String hql = "select bean." + tree.getLftName() + " from " + beanName + " bean where bean.id=:id";
            Integer myPosition = ((Number) session.createQuery(hql).setParameter("id", tree.getId()).uniqueResult()).intValue();
            String hql1 =
                "update " + beanName + " bean set bean." + tree.getRgtName() + " = bean." + tree.getRgtName() + " - 2 WHERE bean." + tree.getRgtName()
                    + " > :myPosition";
            String hql2 =
                "update " + beanName + " bean set bean." + tree.getLftName() + " = bean." + tree.getLftName() + " - 2 WHERE bean." + tree.getLftName()
                    + " > :myPosition";
            if (!StringUtils.isBlank(tree.getTreeCondition())) {
                hql1 += " and (" + tree.getTreeCondition() + ")";
                hql2 += " and (" + tree.getTreeCondition() + ")";
            }
            session.createQuery(hql1).setInteger("myPosition", myPosition).executeUpdate();
            session.createQuery(hql2).setInteger("myPosition", myPosition).executeUpdate();
            session.setFlushMode(model);
        }
    }

    public void setApplicationContext(ApplicationContext appCtx) throws BeansException {
        this.appCtx = appCtx;
    }

    protected SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = (SessionFactory) appCtx.getBean(SESSION_FACTORY, SessionFactory.class);
            if (sessionFactory == null) {
                throw new IllegalStateException("not found bean named '" + SESSION_FACTORY + "',please check you spring config file.");
            }
        }
        return sessionFactory;
    }

    protected Session getSession() {
        return getSessionFactory().getCurrentSession();
    }

}
