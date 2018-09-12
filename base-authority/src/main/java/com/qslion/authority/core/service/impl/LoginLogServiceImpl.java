/**
 *
 */
package com.qslion.authority.core.service.impl;

import com.qslion.authority.core.dao.AuLoginLogRepository;
import com.qslion.authority.core.entity.AuLoginLog;
import com.qslion.authority.core.service.LoginLogService;
import com.qslion.authority.core.vo.LoginSessionVo;
import com.qslion.framework.service.impl.GenericServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 修改备注：
 */
@Service("loginLogService")
public class LoginLogServiceImpl extends GenericServiceImpl<AuLoginLog, Long> implements
        LoginLogService {
    @Autowired
    private AuLoginLogRepository loginLogRepository;


    public int doUpdate(String paramString) {
        // TODO Auto-generated method stub
        return 0;
    }

    public AuLoginLog find(String paramString) {
        // TODO Auto-generated method stub
        return null;
    }

    public int getRecordCount(String paramString,
                              LoginSessionVo paramLoginSessionVo) {
        // TODO Auto-generated method stub
        return 0;
    }

    public List queryByCondition() {
        // TODO Auto-generated method stub
        return null;
    }

    public List queryByCondition(String paramString) {
        // TODO Auto-generated method stub
        return null;
    }

    public List queryByCondition(String paramString1, String paramString2) {
        // TODO Auto-generated method stub
        return null;
    }

    public List queryByCondition(int paramInt1, int paramInt2,
                                 String paramString, LoginSessionVo paramLoginSessionVo) {
        // TODO Auto-generated method stub
        return null;
    }

    public List queryByCondition(int paramInt1, int paramInt2,
                                 String paramString1, String paramString2,
                                 LoginSessionVo paramLoginSessionVo) {
        // TODO Auto-generated method stub
        return null;
    }

}
