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
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String generate(String bizCode) {
        StringBuilder serialNumber = new StringBuilder();
        //会计期间+凭证号+流水号，结账后删除KEY重新生成
        String incrementKey = String.format("%s.%s.%s", "2020-01", SERIAL_NUMBER, bizCode);
        long num = redisTemplate.opsForValue().increment(incrementKey, 1);
        serialNumber.append(bizCode).append(num);
        return serialNumber.toString();
    }
}
