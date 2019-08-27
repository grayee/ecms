package com.qslion.accounting.service.impl;

import com.qslion.accounting.dao.AccountingSubjectRepository;
import com.qslion.accounting.entity.AccountingSubject;
import com.qslion.accounting.service.AccountingSubjectService;
import com.qslion.framework.service.impl.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 会计科目Service实现
 *
 * @author Gray.Z
 * @date 2019/8/27 21:40.
 */
@Service
public class AccountingSubjectServiceImpl extends GenericServiceImpl<AccountingSubject, Long> implements AccountingSubjectService {
    @Autowired
    private AccountingSubjectRepository accountingSubjectRepository;

}
