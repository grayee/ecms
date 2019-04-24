/**
 *
 */
package com.qslion.core.service.impl;

import com.qslion.core.dao.AuLoginLogRepository;
import com.qslion.core.entity.AuLoginLog;
import com.qslion.core.service.LoginLogService;
import com.qslion.framework.service.impl.GenericServiceImpl;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登陆日志Service 实现
 *
 * @author Gray.Z
 * @date 2018/9/18.
 */
@Service("loginLogService")
public class LoginLogServiceImpl extends GenericServiceImpl<AuLoginLog, Long> implements
    LoginLogService {
    @Autowired
    private AuLoginLogRepository loginLogRepository;

    @Override
    public boolean addLoginLog(AuLoginLog loginLog) {
        loginLog.setLoginTime(DateTime.now().toDate());
        loginLogRepository.save(loginLog);
        return true;
    }
}
