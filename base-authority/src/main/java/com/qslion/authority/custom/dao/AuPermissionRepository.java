package com.qslion.authority.custom.dao;


import com.qslion.authority.core.entity.AuPermission;
import org.springframework.stereotype.Repository;

/**
 * Dao实现类 - 权限
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Repository
public interface AuPermissionRepository extends org.springframework.data.jpa.repository.JpaRepository<AuPermission, Long>,
    org.springframework.data.jpa.repository.JpaSpecificationExecutor<AuPermission> {

}