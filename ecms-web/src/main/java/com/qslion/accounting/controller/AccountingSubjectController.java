package com.qslion.accounting.controller;

import com.qslion.accounting.entity.AccountingSubject;
import com.qslion.accounting.service.AccountingSubjectService;
import com.qslion.framework.bean.EntityVo;
import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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


    @PostMapping(value = "/list")
    public Pager<EntityVo> list(@RequestBody Pageable pageable) {
        Pager<AccountingSubject> pager = accountingSubjectService.findPage(pageable);
        return pager.wrap(subject -> {
            EntityVo ev = EntityVo.getResult(subject);
            ev.put("balanceDir", subject.getBalanceDir().getName());
            ev.put("subjectType", subject.getSubjectType().getName());
            return ev;
        });
    }

    @ApiOperation("保存科目信息")
    @PostMapping
    public Long save(@Validated @RequestBody AccountingSubject accountingSubject) {
        AccountingSubject subject = accountingSubjectService.save(accountingSubject);
        return subject.getId();
    }


}
