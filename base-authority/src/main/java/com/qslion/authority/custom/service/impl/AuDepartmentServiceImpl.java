package com.qslion.authority.custom.service.impl;


import com.qslion.authority.custom.dao.AuDepartmentRepository;
import com.qslion.authority.custom.entity.AuDepartment;
import com.qslion.authority.custom.service.AuDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * 部门Service类
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Service("departmentService")
@Transactional(value = "transactionManager", rollbackFor = Exception.class)
public class AuDepartmentServiceImpl extends  AuOrgServiceImpl<AuDepartment, Long> implements AuDepartmentService {

    @Autowired
    private AuDepartmentRepository departmentRepository;


}
