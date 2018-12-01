/**
 *
 */
package com.qslion.core.controller.admin;

import com.qslion.core.entity.AuLoginLog;
import com.qslion.core.service.LoginLogService;
import com.qslion.framework.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginLogController extends BaseController<AuLoginLog> {

    /**
     *
     */
    private static final long serialVersionUID = -3753411455210049949L;

    @Autowired
    public LoginLogService loginLogService;

    @Autowired
    public void setService(LoginLogService loginLogService) {

    }


    public AuLoginLog getModel() {
        // TODO Auto-generated method stub
        return null;
    }

}
