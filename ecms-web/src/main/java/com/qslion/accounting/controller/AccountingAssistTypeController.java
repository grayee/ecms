package com.qslion.accounting.controller;

import com.qslion.accounting.entity.AccountingAssistType;
import com.qslion.accounting.service.AccountingAssistTypeService;
import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 辅助核算Controller
 *
 * @author Gray.Z
 * @date 2019/8/27 21:07.
 */
@Api(value = "辅助核算Controller", description = "辅助核算Controller", tags = {"辅助核算控制器"})
@ResponseResult
@RestController
@RequestMapping(value = "/accounting/assist")
public class AccountingAssistTypeController extends BaseController<AccountingAssistType> {
    @Autowired
    public AccountingAssistTypeService accountingAssistTypeService;


}
