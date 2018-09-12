package com.qslion.authority.custom.dao;


import com.qslion.authority.core.entity.AuUser;
import org.springframework.stereotype.Repository;

/**
 * Dao实现类 - 用户
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Repository
public interface AuUserRepository extends org.springframework.data.jpa.repository.JpaRepository<AuUser, Long>,
    org.springframework.data.jpa.repository.JpaSpecificationExecutor<AuUser> {


    AuUser findUserByUsername(String username);

}