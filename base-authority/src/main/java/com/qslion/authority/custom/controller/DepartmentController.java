package com.qslion.authority.custom.controller;

import com.qslion.authority.core.entity.AuPartyRelation;
import com.qslion.authority.core.enums.AuPartyRelationType;
import com.qslion.authority.core.service.PartyRelationService;
import com.qslion.authority.core.util.TreeTools;
import com.qslion.authority.custom.entity.AuDepartment;
import com.qslion.authority.custom.service.AuDepartmentService;
import com.qslion.framework.bean.*;
import com.qslion.framework.controller.BaseController;


import io.swagger.annotations.Api;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 部门控制类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@Api(value = "部门Controller", description = "部门Controller", tags = {"部门控制器"})
@ResponseResult
@RestController
@RequestMapping(value = "/org/department")
public class DepartmentController extends BaseController<AuDepartment> {

    @Autowired
    public AuDepartmentService departmentService;

    @Autowired
    private PartyRelationService partyRelationService;


    @PostMapping
    public Long save(@Validated @RequestBody AuDepartment department) {
        AuDepartment auDepartment = departmentService.insert(department);
        return auDepartment.getId();
    }


    @DeleteMapping
    public boolean delete(@RequestBody List<Long> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            return departmentService.remove(ids);
        }
        return false;
    }

    @PutMapping
    public boolean update(@RequestBody AuDepartment department) {
        AuDepartment auDepartment = departmentService.update(department);
        return auDepartment == null;
    }

    @PostMapping(value = "/list")
    public Pager<EntityVo> list(@RequestBody Pageable pageable) {
        Pager<AuDepartment> pager =departmentService.findPage(pageable);
        List<AuPartyRelation> relations = partyRelationService.findByRelationType(AuPartyRelationType.ADMINISTRATIVE);
        return pager.wrap(dept -> {
            EntityVo ev = EntityVo.getPageResult(dept);
            ev.put("parentId", TreeTools.getPathTreeStr(relations, dept.getParentId()));
            return ev;
        });
    }

    /**
     * 详细信息
     */
    @GetMapping(value = "/detail/{id}")
    public AuDepartment detail(@PathVariable Long id) {
        return departmentService.findById(id);
    }
}
