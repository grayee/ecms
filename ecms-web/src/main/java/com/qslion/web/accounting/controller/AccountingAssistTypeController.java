package com.qslion.web.accounting.controller;

import com.google.common.collect.Lists;
import com.qslion.web.accounting.entity.AccountingAssistType;
import com.qslion.web.accounting.service.AccountingAssistTypeService;
import com.qslion.framework.bean.EntityVo;
import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping(value = "/list")
    public List<EntityVo> list() {
        List<AccountingAssistType> list = accountingAssistTypeService.findList(Lists.newArrayList(),Lists.newArrayList());
        return list.stream().map(aat->{
            EntityVo ev= EntityVo.getResult(aat.getClass());
            ev.put("isSystem","");
            return ev;
        }).collect(Collectors.toList());
    }

}
