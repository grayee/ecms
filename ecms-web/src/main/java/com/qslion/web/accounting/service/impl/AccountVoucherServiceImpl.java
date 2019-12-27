package com.qslion.web.accounting.service.impl;

import com.qslion.framework.service.impl.GenericServiceImpl;
import com.qslion.web.accounting.dao.AccountSubjectRepository;
import com.qslion.web.accounting.dao.AccountVoucherEntryRepository;
import com.qslion.web.accounting.dao.AccountVoucherRepository;
import com.qslion.web.accounting.entity.AccountSubject;
import com.qslion.web.accounting.entity.AccountVoucher;
import com.qslion.web.accounting.service.AccountSubjectService;
import com.qslion.web.accounting.service.AccountVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 会计凭证Service实现
 *
 * @author Gray.Z
 * @date 2019/8/27 21:40.
 */
@Service
@Transactional(value = "transactionManager", rollbackFor = Exception.class)
public class AccountVoucherServiceImpl extends GenericServiceImpl<AccountVoucher, Long> implements AccountVoucherService {
    @Autowired
    private AccountSubjectRepository accountSubjectRepository;
    @Autowired
    private AccountVoucherRepository accountVoucherRepository;
    @Autowired
    private AccountVoucherEntryRepository accountVoucherEntryRepository;


}
