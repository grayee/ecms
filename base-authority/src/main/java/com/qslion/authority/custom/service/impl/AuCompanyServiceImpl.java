
package com.qslion.authority.custom.service.impl;


import com.qslion.authority.custom.entity.AuCompany;
import com.qslion.authority.custom.service.AuCompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 公司Service类
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Service("companyService")
@Transactional(value = "transactionManager", rollbackFor = Exception.class)
public class AuCompanyServiceImpl extends AuOrgServiceImpl<AuCompany, Long> implements AuCompanyService {




}
