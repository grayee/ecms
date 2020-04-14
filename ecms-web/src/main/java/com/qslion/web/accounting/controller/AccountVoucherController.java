package com.qslion.web.accounting.controller;

import com.qslion.framework.bean.*;
import com.qslion.framework.controller.BaseController;
import com.qslion.web.accounting.entity.AccountSubject;
import com.qslion.web.accounting.entity.AccountVoucher;
import com.qslion.web.accounting.service.AccountAssistService;
import com.qslion.web.accounting.service.AccountSubjectService;
import com.qslion.web.accounting.service.AccountVoucherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 会计凭证Controller
 *
 * @author Gray.Z
 * @date 2019/8/27 21:07.
 */
@Api(value = "会计凭证Controller", description = "会计凭证Controller", tags = {"会计凭证控制器"})
@ResponseResult
@RestController
@RequestMapping(value = "/accounting/voucher")
public class AccountVoucherController extends BaseController<AccountSubject> {
    @Autowired
    private AccountSubjectService accountSubjectService;
    @Autowired
    private AccountAssistService accountAssistService;
    @Autowired
    private AccountVoucherService accountVoucherService;


    @PostMapping(value = "/list")
    public Pager<EntityVo> list(@RequestBody Pageable pageable) {
        Pager<AccountVoucher> pager = accountVoucherService.findPage(pageable);
        return pager.wrap(EntityVo::getEntityVo);
    }


    @ApiOperation("保存凭证")
    @PostMapping
    public Long save(@Validated @RequestBody AccountVoucher accountVoucher) {
        AccountVoucher voucher = accountVoucherService.save(accountVoucher);
        return voucher.getId();
    }

    /**
     * 详细信息
     */
    @GetMapping(value = {"/detail/{id}", "/detail"})
    public EntityVo detail(@PathVariable(required = false) Long id) {
        AccountVoucher voucher = new AccountVoucher();
        if (id != null) {
            voucher = accountVoucherService.findById(id);
        }
        EntityVo ev = EntityVo.getEntityVo(voucher);
        return ev;
    }

}
