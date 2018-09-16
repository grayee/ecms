package com.qslion.core.controller.au;

import com.qslion.core.entity.AuAuthorize;
import com.qslion.core.service.AuthorizeService;
import com.qslion.framework.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 项目名称：authority 类名称：AuthorizeAction 类描述： 创建人：Administrator 创建时间：2011-8-17 下午01:57:05
 * 修改人：Administrator 修改时间：2011-8-17 下午01:57:05 修改备注：
 */
@Controller
public class AuthorizeAction extends BaseController<AuAuthorize, Long> {

    /**
     *
     */
    private static final long serialVersionUID = 7537529280202789322L;

    @Autowired
    public AuthorizeService authorizeService;


    public AuAuthorize getModel() {
        // TODO Auto-generated method stub
        return null;
    }

}
