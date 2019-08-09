package com.qslion.custom.controller;

import com.qslion.custom.entity.AuDepartment;
import com.qslion.custom.service.AuDepartmentService;
import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.bean.ResponseResult;
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
    public Pager<AuDepartment> list(Pageable pageable) {
        return departmentService.findPage(pageable);
    }

    /**
     * 详细信息
     */
    @GetMapping(value = "/detail/{id}")
    public AuDepartment detail(@PathVariable Long id) {
        return departmentService.findById(id);
    }
}
