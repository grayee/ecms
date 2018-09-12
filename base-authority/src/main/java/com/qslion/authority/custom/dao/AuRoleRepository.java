package com.qslion.authority.custom.dao;


import com.qslion.authority.core.entity.AuRole;
import org.springframework.stereotype.Repository;

/**
 * Dao实现类 - 角色
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Repository
public interface AuRoleRepository extends org.springframework.data.jpa.repository.JpaRepository<AuRole, Long>,
    org.springframework.data.jpa.repository.JpaSpecificationExecutor<AuRole> {

}