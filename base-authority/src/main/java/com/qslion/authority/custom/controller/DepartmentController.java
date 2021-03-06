package com.qslion.authority.custom.controller;

import com.qslion.authority.core.entity.AuOrgRelation;
import com.qslion.authority.core.enums.AuOrgRelationType;
import com.qslion.authority.core.service.AuOrgRelationService;
import com.qslion.authority.core.util.TreeTools;
import com.qslion.authority.custom.entity.AuDepartment;
import com.qslion.authority.custom.service.AuDepartmentService;
import com.qslion.framework.bean.*;
import com.qslion.framework.controller.BaseController;


import io.swagger.annotations.Api;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
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
    private AuOrgRelationService auOrgRelationService;


    @PostMapping
    public Long save(@Validated @RequestBody AuDepartment department) {
        //如果用户不手工编号，则系统自动编号
        if (StringUtils.isEmpty(department.getDeptNo())) {
            department.setDeptNo(String.valueOf(RandomUtils.nextInt(1000, 9999)));
        }
        AuDepartment auDepartment = departmentService.insert(department);
        return auDepartment.getId();
    }


    @DeleteMapping
    public boolean delete(@RequestBody List<Long> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            departmentService.deleteByIds(ids);
            return true;
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
        Pager<AuDepartment> pager = departmentService.findPage(pageable);
        List<AuOrgRelation> relations = auOrgRelationService.findByRelationType(AuOrgRelationType.ADMINISTRATIVE);
        return pager.wrap(dept -> {
            EntityVo ev = EntityVo.getEntityVo(dept);
            ev.put("parentId", TreeTools.getTreePathStr(relations, dept.getParentId()));
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
