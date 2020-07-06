package com.qslion.authority.custom.service.impl;


import com.qslion.authority.custom.dao.AuEmployeeRepository;
import com.qslion.authority.custom.entity.AuEmployee;
import com.qslion.authority.custom.service.AuEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 员工Service类
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Service("employeeService")
@Transactional(value = "transactionManager", rollbackFor = Exception.class)
public class AuEmployeeServiceImpl extends AuOrgServiceImpl<AuEmployee, Long> implements AuEmployeeService {
    @Autowired
    private AuEmployeeRepository employeeRepository;




}
