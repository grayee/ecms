package com.qslion.web.sob.controller;


import com.qslion.framework.bean.*;
import com.qslion.framework.controller.BaseController;

import com.qslion.moudles.ddic.util.DictUtils;

import com.qslion.web.accounting.enums.GaapType;
import com.qslion.web.accounting.enums.SubjectType;
import com.qslion.web.accounting.enums.VatType;

import com.qslion.web.sob.entity.AccountSet;
import com.qslion.web.sob.service.AccountSetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



/**
 * 账套Controller
 *
 * @author Gray.Z
 * @date 2019/8/27 21:07.
 */
@Api(value = "账套Controller", description = "会计账套Controller", tags = {"会计账套控制器"})
@ResponseResult
@RestController
@RequestMapping(value = "/baseSetting/accountSet")
public class AccountSetController extends BaseController<AccountSet> {
    @Autowired
    private AccountSetService accountSetService;

    @PostMapping(value = "/list")
    public Pager<EntityVo> list(@RequestBody Pageable pageable) {
        pageable.setOrderDirection(Order.Direction.asc);
        pageable.setOrderProperty("subjectCode");
        Pager<AccountSet> pager = accountSetService.findPage(pageable);
        pager.addExtras("subjectTypes", SubjectType.getMapList());
        return pager.wrap(sob -> {
            EntityVo ev = EntityVo.getResult(sob);
            ev.put("vatType", sob.getVatType().getName());
            ev.put("gaapType", sob.getGaapType().getName());
            ev.put("isSystem", DictUtils.getValue("isSystem", sob.getSystem().toString()));
            return ev;
        });
    }


    @ApiOperation("保存账套信息")
    @PostMapping
    public Long save(@Validated @RequestBody AccountSet accountSet) {
        AccountSet bookSet = accountSetService.save(accountSet);
        return bookSet.getId();
    }

    /**
     * 详细信息
     */
    @GetMapping(value = {"/detail/{id}", "/detail"})
    public EntityVo detail(@PathVariable(required = false) Long id) {
        AccountSet accountSet = new AccountSet();
        if (id != null) {
            accountSet = accountSetService.findById(id);
        }
        EntityVo ev = EntityVo.getResult(accountSet);
        ev.put("vatTypeMap", VatType.getMapList());
        ev.put("gaapTypeMap", GaapType.getMapList());
        ev.put("isSystemMap", DictUtils.getMapList("isSystem"));
        return ev;
    }

}
