package com.qslion.accounting.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qslion.accounting.entity.AccountingSubject;
import com.qslion.accounting.enums.SubjectType;
import com.qslion.accounting.service.AccountingSubjectService;
import com.qslion.framework.bean.*;
import com.qslion.framework.controller.BaseController;
import com.qslion.moudles.ddic.util.DictUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
        pageable.setOrderDirection(Order.Direction.asc);
        pageable.setOrderProperty("subjectCode");
        Pager<AccountingSubject> pager = accountingSubjectService.findPage(pageable);

        List<Map<String, Object>> sType = Lists.newArrayList();
        for (SubjectType subjectType : SubjectType.values()) {
            Map<String, Object> sMap = Maps.newHashMapWithExpectedSize(2);
            sMap.put("value", subjectType.getId());
            sMap.put("text", subjectType.getName());
            sType.add(sMap);
        }
        pager.addExtras("subjectTypes", sType);
        return pager.wrap(subject -> {
            int spaceLen = subject.getSubjectCode().length() - 4;
            if (spaceLen > 0) {
                spaceLen = subject.getSubjectCode().length() + spaceLen;
                subject.setSubjectCode(String.format("%" + spaceLen + "s", subject.getSubjectCode()).replace(" ", "\r\n"));
            }
            EntityVo ev = EntityVo.getResult(subject);
            ev.put("balanceDir", subject.getBalanceDir().getName());
            ev.put("subjectType", subject.getSubjectType().getName());
            ev.put("isSystem", DictUtils.getValue("isSystem", subject.getSystem().toString()));
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
