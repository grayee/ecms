package com.qslion.custom.controller;

import com.qslion.core.entity.AuPartyRelation;
import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.core.service.PartyRelationService;
import com.qslion.core.util.TreeTools;
import com.qslion.custom.entity.AuDepartment;
import com.qslion.custom.service.AuDepartmentService;
import com.qslion.framework.bean.*;
import com.qslion.framework.controller.BaseController;


import com.qslion.framework.entity.BaseTree;
import io.swagger.annotations.Api;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


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
            EntityVo ev = EntityVo.getResult(dept);
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
