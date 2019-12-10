package com.qslion.web.sob.service.impl;

import com.qslion.framework.service.impl.GenericServiceImpl;
import com.qslion.web.sob.dao.AccountSetRepository;
import com.qslion.web.sob.entity.AccountSet;
import com.qslion.web.sob.service.AccountSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  账套Service实现
 *
 * @author Gray.Z
 * @date 2019/8/27 21:40.
 */
@Service
public class AccountSetServiceImpl extends GenericServiceImpl<AccountSet, Long> implements AccountSetService {
    @Autowired
    private AccountSetRepository accountSetRepository;

}
