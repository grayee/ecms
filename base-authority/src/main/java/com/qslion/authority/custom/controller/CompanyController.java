package com.qslion.authority.custom.controller;


import com.qslion.authority.core.entity.AuPartyRelation;
import com.qslion.authority.core.enums.AuPartyRelationType;
import com.qslion.authority.core.service.PartyRelationService;
import com.qslion.authority.core.util.TreeTools;
import com.qslion.authority.custom.entity.AuCompany;
import com.qslion.authority.custom.service.AuCompanyService;
import com.qslion.framework.bean.EntityVo;
import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.controller.BaseController;
import com.qslion.framework.util.ValidatorUtils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公司控制类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@Api(value="公司Controller",description="公司Controller",tags={"公司控制器"})
@ResponseResult
@RestController
@RequestMapping(value = "/org/company")
public class CompanyController extends BaseController<AuCompany> {

    @Autowired
    private AuCompanyService companyService;

    @Autowired
    private PartyRelationService partyRelationService;

    /**
     * 保存
     *
     * @param company 公司
     * @return ID
     */
    @ApiOperation("保存公司信息")
    @PostMapping
    public Long save(@Validated({AddGroup.class}) @RequestBody AuCompany company) {
        AuCompany auCompany = companyService.insert(company);
        return auCompany.getId();
    }


    /**
     * 从页面的表单获取团体关系id，并删除团体关系及相关的权限记录
     */
    @DeleteMapping
    public boolean delete(@RequestBody List<Long> ids) {
        return CollectionUtils.isNotEmpty(ids) && companyService.remove(ids);
    }

    /**
     * 从页面表单获取信息注入vo，并修改单条记录，同时调用接口更新相应的团体、团体关系记录
     */
    @PutMapping
    public boolean update(@RequestBody AuCompany company) {
        AuCompany auCompany = companyService.update(company);
        return auCompany == null;
    }

    @PostMapping(value = "/list")
    public Pager<EntityVo> list(@RequestBody Pageable pageable) {
        Pager<AuCompany> pager = companyService.findPage(pageable);
        List<AuPartyRelation> relations = partyRelationService.findByRelationType(AuPartyRelationType.ADMINISTRATIVE);
        return pager.wrap(company -> {
            EntityVo ev = EntityVo.getResult(company);
            ev.put("parentId", TreeTools.getPathTreeStr(relations, company.getParentId()));
            return ev;
        });
    }

    /**
     * 详细信息
     */
    @GetMapping(value = "/detail/{id}")
    public AuCompany detail(@PathVariable Long id) {
        return companyService.findById(id);
    }

}
