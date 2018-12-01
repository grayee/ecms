/**
 *
 */
package com.qslion.core.service;

import com.qslion.core.entity.AuUser;
import com.qslion.framework.service.IGenericService;

/**
 * 用户Service
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
public interface AuUserService extends IGenericService<AuUser, Long> {

    /**
     * 根据用户名获取用户
     *
     * @param username 用户名
     * @return 用户
     */
    AuUser findUserByUsername(String username);

    /**
     * 获取当前用户登录ID
     *
     * @return 用户登录ID
     */
    String getCurrentUsername();

}
