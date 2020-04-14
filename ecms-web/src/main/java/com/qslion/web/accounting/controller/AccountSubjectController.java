package com.qslion.web.accounting.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qslion.framework.entity.AttributeEntity;
import com.qslion.framework.enums.CurrencyType;
import com.qslion.web.accounting.entity.AccountAssist;
import com.qslion.web.accounting.entity.AccountSubject;
import com.qslion.web.accounting.enums.AccountDir;
import com.qslion.web.accounting.enums.SubjectType;
import com.qslion.web.accounting.service.AccountAssistService;
import com.qslion.web.accounting.service.AccountSubjectService;
import com.qslion.framework.bean.*;
import com.qslion.framework.controller.BaseController;
import com.qslion.moudles.ddic.util.DictUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 会计科目Controller
 *
 * @author Gray.Z
 * @date 2019/8/27 21:07.
 */
@Api(value = "会计科目Controller", description = "会计科目Controller", tags = {"会计科目控制器"})
@ResponseResult
@RestController
@RequestMapping(value = "/baseSetting/accountSubject")
public class AccountSubjectController extends BaseController<AccountSubject> {
    @Autowired
    private AccountSubjectService accountSubjectService;

    @Autowired
    private AccountAssistService accountAssistService;


    @PostMapping(value = "/list")
    public Pager<EntityVo> list(@RequestBody Pageable pageable) {
        pageable.setOrderDirection(Order.Direction.asc);
        pageable.setOrderProperty("subjectCode");
        Pager<AccountSubject> pager = accountSubjectService.findPage(pageable);
        pager.addExtras("subjectTypes", SubjectType.getMapList());
        return pager.wrap(subject -> {
            int spaceLen = subject.getSubjectCode().length() - 4;

            if (spaceLen > 0) {
                spaceLen = subject.getSubjectCode().length() + spaceLen;
                subject.setSubjectCode(String.format("%" + spaceLen + "s", subject.getSubjectCode()).replace(" ", "\r\n"));
                subject.setSubjectName(String.format("%" + spaceLen + "s", subject.getSubjectName()).replace(" ", "\r\n"));
            }
            EntityVo ev = EntityVo.getEntityVo(subject);
            ev.put("isSystem", DictUtils.getValue("isSystem", subject.getSystem().toString()));
            return ev;
        });
    }


    @ApiOperation("保存科目信息")
    @PostMapping
    public Long save(@Validated @RequestBody AccountSubject accountSubject) {
        AccountSubject subject = accountSubjectService.save(accountSubject);
        return subject.getId();
    }

    /**
     * 详细信息
     */
    @GetMapping(value = {"/detail/{id}", "/detail"})
    public EntityVo detail(@PathVariable(required = false) Long id) {
        AccountSubject subject = new AccountSubject();
        if (id != null) {
            subject = accountSubjectService.findById(id);
        }
        EntityVo ev = EntityVo.getEntityVo(subject);
        ev.put("balanceDirMap", AccountDir.getMapList());
        ev.put("subjectTypeMap", SubjectType.getMapList());
        ev.put("isSystemMap", DictUtils.getMapList("isSystem"));
        List<String> assistTypes = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(subject.getAcctAssists())) {
            assistTypes = subject.getAcctAssists().stream()
                    .map(AttributeEntity::getValue).collect(Collectors.toList());
        }
        ev.put("assistTypes", assistTypes);
        List<AccountAssist> assistTypeList = accountAssistService.findAll();
        ev.put("assistTypeMap", assistTypeList.stream().map(a -> {
            Map<String, Object> map = Maps.newHashMapWithExpectedSize(2);
            map.put("value", a.getValue());
            map.put("text", a.getName());
            return map;
        }).collect(Collectors.toList()));
        ev.put("currencyTypeMap", CurrencyType.getMapList());
        return ev;
    }

}
