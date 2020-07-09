package com.qslion.authority.custom.controller;


import com.qslion.authority.core.entity.AuOrgRelation;
import com.qslion.authority.core.enums.AuOrgRelationType;
import com.qslion.authority.core.enums.AuOrgType;
import com.qslion.authority.core.service.AuOrgRelationService;
import com.qslion.authority.core.util.TreeTools;
import com.qslion.authority.custom.entity.AuCompany;
import com.qslion.authority.custom.service.AuCompanyService;
import com.qslion.framework.bean.EntityVo;
import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.controller.BaseController;
import com.qslion.framework.enums.EnableStatus;
import com.qslion.framework.util.ValidatorUtils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
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
@Api(value = "公司Controller", description = "公司Controller", tags = {"公司控制器"})
@ResponseResult
@RestController
@RequestMapping(value = "/org/company")
public class CompanyController extends BaseController<AuCompany> {

    @Autowired
    private AuCompanyService companyService;

    @Autowired
    private AuOrgRelationService auOrgRelationService;

    /**
     * 保存
     *
     * @param company 公司
     * @return ID
     */
    @ApiOperation("保存公司信息")
    @PostMapping
    public Long save(@Validated({AddGroup.class}) @RequestBody AuCompany company) {
        //如果用户不手工编号，则系统自动编号
        if (StringUtils.isEmpty(company.getCompanyNo())) {
            company.setCompanyNo(String.valueOf(RandomUtils.nextInt(1000, 9999)));
        }
        if (company.getEnableStatus() == null) {
            company.setEnableStatus(EnableStatus.ENABLE);
            company.setEnableDate(DateTime.now().toDate());
        }
        AuCompany auCompany = companyService.insert(company);
        return auCompany.getId();
    }


    /**
     * 从页面的表单获取团体关系id，并删除团体关系及相关的权限记录
     */
    @DeleteMapping
    public boolean delete(@RequestBody List<Long> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            companyService.deleteByIds(ids);
        }
        return true;
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
        List<AuOrgRelation> relations = auOrgRelationService.findByRelationType(AuOrgRelationType.ADMINISTRATIVE);
        return pager.wrap(company -> {
            EntityVo ev = EntityVo.getEntityVo(company);
            ev.put("parentId", TreeTools.getTreePathStr(relations, company.getParentId()));
            return ev;
        });
    }

    /**
     * 详细信息
     */
    @GetMapping(value = "/detail/{id}")
    public AuCompany detail(@PathVariable Long id) {
        AuCompany company = companyService.findById(id);
        AuOrgRelation relation = auOrgRelationService.findByOrg(AuOrgRelationType.ADMINISTRATIVE, AuOrgType.COMPANY, id);
        if (relation != null) {
            company.setParentId(relation.getParentId());
        }
        return company;
    }

}
