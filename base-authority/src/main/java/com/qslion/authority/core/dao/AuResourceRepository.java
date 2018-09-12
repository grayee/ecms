package com.qslion.authority.core.dao;


import com.qslion.authority.core.entity.AuResource;
import com.qslion.framework.dao.IGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Dao实现类 - 资源
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Repository
public interface AuResourceRepository extends IGenericRepository<AuResource, Long> {

}