package com.qslion.web.accounting.service.impl;

import com.qslion.web.accounting.dao.AccountSubjectRepository;
import com.qslion.web.accounting.entity.AccountSubject;
import com.qslion.web.accounting.service.AccountSubjectService;
import com.qslion.framework.service.impl.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 会计科目Service实现
 *
 * @author Gray.Z
 * @date 2019/8/27 21:40.
 */
@Service
@Transactional(value = "sobTransactionManager", rollbackFor = Exception.class)
public class AccountSubjectServiceImpl extends GenericServiceImpl<AccountSubject, Long> implements AccountSubjectService {
    @Autowired
    private AccountSubjectRepository accountSubjectRepository;

}
