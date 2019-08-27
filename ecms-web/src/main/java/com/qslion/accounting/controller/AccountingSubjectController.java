package com.qslion.accounting.controller;

import com.qslion.accounting.entity.AccountingSubject;
import com.qslion.accounting.service.AccountingSubjectService;
import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会计科目Controller
 *
 * @author Gray.Z
 * @date 2019/8/27 21:07.
 */
@Api(value = "会计科目Controller", description = "会计科目Controller", tags = {"会计科目控制器"})
@ResponseResult
@RestController
@RequestMapping(value = "/accounting/subject")
public class AccountingSubjectController extends BaseController<AccountingSubject> {
    @Autowired
    public AccountingSubjectService accountingSubjectService;


}
