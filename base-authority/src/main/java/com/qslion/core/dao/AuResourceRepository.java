package com.qslion.core.dao;


import com.qslion.core.entity.AuMenu;
import com.qslion.core.entity.AuResource;
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

    AuResource findByMenu(AuMenu auMenu);
}