/**
 *
 */
package com.qslion.authority.core.service;

import com.qslion.authority.core.entity.AuLoginLog;
import com.qslion.framework.service.IGenericService;

/**
 * 登陆日志Service
 *
 * @author Gray.Z
 * @date 2018/9/18.
 */
public interface LoginLogService extends IGenericService<AuLoginLog, Long> {


    /**
     * 记录登陆日志
     *
     * @param loginLog 登陆日志
     */
    boolean addLoginLog(AuLoginLog loginLog);

}
