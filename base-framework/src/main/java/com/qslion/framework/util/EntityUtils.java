package com.qslion.framework.util;

import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.hibernate.persister.walking.spi.AttributeDefinition;
import org.springframework.beans.BeanUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.beans.PropertyDescriptor;
import java.util.Map;

/**
 * Entity工具类
 *
 * @author Gray.Z
 * @date 2018/9/23 10:24.
 */
public class EntityUtils {

    /**
     * EntityScanner
     */
    private static EntityManager entityManager;

    public static void main(String[] args) {

        //通过EntityManager获取factory
        EntityManagerFactory entityManagerFactory = entityManager.getEntityManagerFactory();
        SessionFactoryImpl sessionFactory = (SessionFactoryImpl) entityManagerFactory.unwrap(SessionFactory.class);
        Map<String, EntityPersister> persisterMap = sessionFactory.getMetamodel().entityPersisters();
        for (Map.Entry<String, EntityPersister> entity : persisterMap.entrySet()) {
            Class targetClass = entity.getValue().getMappedClass();
            SingleTableEntityPersister persister = (SingleTableEntityPersister) entity.getValue();
            Iterable<AttributeDefinition> attributes = persister.getAttributes();
            String entityName = targetClass.getSimpleName();//Entity的名称
            String tableName = persister.getTableName();//Entity对应的表的英文名


            System.out.println("类名：" + entityName + " => 表名：" + tableName);

            //属性
            for (AttributeDefinition attr : attributes) {
                String propertyName = attr.getName(); //在entity中的属性名称
                String[] columnName = persister.getPropertyColumnNames(propertyName); //对应数据库表中的字段名
                String type = "";
                PropertyDescriptor targetPd = BeanUtils.getPropertyDescriptor(targetClass, propertyName);
                if (targetPd != null) {
                    type = targetPd.getPropertyType().getSimpleName();
                }
                System.out.println("属性名：" + propertyName + " => 类型：" + type + " => 数据库字段名：" + columnName[0]);
            }
        }
    }

}
