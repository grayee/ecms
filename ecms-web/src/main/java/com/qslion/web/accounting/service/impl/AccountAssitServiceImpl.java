package com.qslion.web.accounting.service.impl;

import com.qslion.web.accounting.dao.AccountAssistRepository;
import com.qslion.web.accounting.entity.AccountAssist;
import com.qslion.web.accounting.service.AccountAssistService;
import com.qslion.framework.service.impl.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 辅助核算Service实现
 *
 * @author Gray.Z
 * @date 2019/8/27 21:40.
 */
@Service
public class AccountAssitServiceImpl extends GenericServiceImpl<AccountAssist, Long> implements AccountAssistService {
    @Autowired
    private AccountAssistRepository accountAssistRepository;

}
