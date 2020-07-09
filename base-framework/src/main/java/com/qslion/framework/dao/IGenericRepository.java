package com.qslion.framework.dao;

import com.qslion.framework.entity.BaseEntity;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Gray.Z
 */
@NoRepositoryBean
public interface IGenericRepository<T extends BaseEntity<ID>, ID extends Serializable> extends JpaRepository<T, ID>,
    JpaSpecificationExecutor<T> {

    <S extends T> Iterable<S> batchSave(Iterable<S> var1);

    <S extends T> Iterable<S> batchUpdate(Iterable<S> var1);

    T treeSave(T entity);
}
