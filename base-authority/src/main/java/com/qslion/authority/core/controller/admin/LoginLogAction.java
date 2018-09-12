/**
 *
 */
package com.qslion.authority.core.controller.admin;

import com.qslion.authority.core.entity.AuLoginLog;
import com.qslion.authority.core.service.LoginLogService;
import com.qslion.framework.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginLogAction extends BaseController<AuLoginLog, String> {

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
