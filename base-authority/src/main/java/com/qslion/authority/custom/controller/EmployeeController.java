/**
 *
 */
package com.qslion.authority.custom.controller;


import com.qslion.authority.core.entity.AuPartyRelation;
import com.qslion.authority.core.enums.AuPartyRelationType;
import com.qslion.authority.core.service.ConnectionRuleService;
import com.qslion.authority.core.service.PartyRelationService;
import com.qslion.authority.core.util.TreeTools;
import com.qslion.authority.custom.entity.AuEmployee;
import com.qslion.authority.custom.service.AuEmployeeService;
import com.qslion.framework.bean.EntityVo;
import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.controller.BaseController;

import com.qslion.framework.util.ValidatorUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 雇员控制类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@ResponseResult
@RestController
@RequestMapping(value = "/org/employee")
public class EmployeeController extends BaseController<AuEmployee> {

    @Autowired
    public AuEmployeeService employeeService;
    @Autowired
    public PartyRelationService partyRelationService;
    @Autowired
    public ConnectionRuleService connectionRuleService;
    /**
     * 保存
     *
     * @param employee 公司
     * @return ID
     */
    @ApiOperation("保存员工信息")
    @PostMapping
    public Long save(@Validated({ValidatorUtils.AddGroup.class}) @RequestBody AuEmployee employee) {
        AuEmployee auEmployee = employeeService.insert(employee);
        return auEmployee.getId();
    }


    /**
     * 从页面的表单获取团体关系id，并删除团体关系及相关的权限记录
     */
    @DeleteMapping
    public boolean delete(@RequestBody List<Long> ids) {
        return CollectionUtils.isNotEmpty(ids) && employeeService.remove(ids);
    }

    /**
     * 从页面表单获取信息注入vo，并修改单条记录，同时调用接口更新相应的团体、团体关系记录
     */
    @PutMapping
    public boolean update(@RequestBody AuEmployee employee) {
        AuEmployee auEmployee = employeeService.update(employee);
        return auEmployee == null;
    }

    @PostMapping(value = "/list")
    public Pager<EntityVo> list(@RequestBody Pageable pageable) {
        Pager<AuEmployee> pager =employeeService.findPage(pageable);
        List<AuPartyRelation> relations = partyRelationService.findByRelationType(AuPartyRelationType.ADMINISTRATIVE);
        return pager.wrap(emp -> {
            EntityVo ev = EntityVo.getPageResult(emp);
            ev.put("parentId", TreeTools.getPathTreeStr(relations, emp.getParentId()));
            return ev;
        });
    }

    /**
     * 详细信息
     */
    @GetMapping(value = "/detail/{id}")
    public AuEmployee detail(@PathVariable Long id) {
        return employeeService.findById(id);
    }
}
