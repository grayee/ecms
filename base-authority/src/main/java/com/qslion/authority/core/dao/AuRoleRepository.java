package com.qslion.authority.core.dao;


import com.qslion.authority.core.entity.AuRole;
import com.qslion.framework.dao.IGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Dao实现类 - 角色
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Repository
public interface AuRoleRepository extends IGenericRepository<AuRole, Long> {

}