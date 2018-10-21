package com.qslion.custom.controller;


import com.google.common.collect.Lists;
import com.qslion.core.service.ConnectionRuleService;
import com.qslion.core.service.PartyRelationService;
import com.qslion.core.service.PartyService;
import com.qslion.custom.entity.AuCompany;
import com.qslion.custom.service.AuCompanyService;
import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.controller.BaseController;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公司控制类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@ResponseResult
@RestController
@RequestMapping(value = "/org/company")
public class CompanyController extends BaseController<AuCompany, String> {

    @Autowired
    private AuCompanyService companyService;
    @Autowired
    private PartyService partyService;
    @Autowired
    private PartyRelationService partyRelationService;
    @Autowired
    private ConnectionRuleService connectionRuleService;

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    /**
     * 保存
     *
     * @param company 公司
     * @return ID
     */
    @PostMapping
    public Long save(@Validated @RequestBody AuCompany company, @RequestParam(required = false) Long parentCode) {
        AuCompany auCompany = companyService.insert(company, parentCode);
        return auCompany.getId();
    }


    /**
     * 从页面的表单获取团体关系id，并删除团体关系及相关的权限记录
     */
    @DeleteMapping
    public boolean delete(Long[] ids) {
        List<AuCompany> companyList = Lists.newArrayList(ids).stream().map(id -> {
            AuCompany company = new AuCompany();
            company.setId(id);
            return company;
        }).collect(Collectors.toList());
        companyService.delete(companyList);
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

    @GetMapping(value = "/list")
    public Pager<AuCompany> list(Pageable pageable) {
        return companyService.findPage(pageable);
    }

    /**
     * 详细信息
     */
    @GetMapping(value = "/detail/{id}")
    public AuCompany detail(@PathVariable Long id) {
        return companyService.find(id);
    }

}
