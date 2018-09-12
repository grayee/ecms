package com.qslion.authority.custom.dao;


import com.qslion.authority.core.entity.AuUserGroup;
import org.springframework.stereotype.Repository;

/**
 * Dao实现类 - 用户组
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Repository
public interface AuUserGroupRepository extends org.springframework.data.jpa.repository.JpaRepository<AuUserGroup, Long>,
    org.springframework.data.jpa.repository.JpaSpecificationExecutor<AuUserGroup> {

}