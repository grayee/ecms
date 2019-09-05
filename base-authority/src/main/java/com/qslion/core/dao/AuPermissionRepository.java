package com.qslion.core.dao;


import com.qslion.core.entity.AuPermission;
import com.qslion.framework.dao.IGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Dao实现类 - 权限
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Repository
public interface AuPermissionRepository extends IGenericRepository<AuPermission, Long> {

    AuPermission findByTypeAndValue(AuPermission.PermitType type, String value);
}