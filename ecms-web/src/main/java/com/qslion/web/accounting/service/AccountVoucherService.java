package com.qslion.web.accounting.service;

import com.qslion.framework.service.IGenericService;
import com.qslion.web.accounting.entity.AccountVoucher;

/**
 * 会计凭证SService
 *
 * @author Gray.Z
 * @date 2019/8/27 21:09.
 */
public interface AccountVoucherService extends IGenericService<AccountVoucher, Long> {

    /**
     * 凭证序列号自增序列
     */
    String SERIAL_NUMBER = "serial.number:";

    /**
     * 根据业务码字符串,生成一个流水号,格式按照:<br/>
     * {bizCode}{10位的自增序列号}
     *
     * @param bizCode
     * @return 10位的序列号
     */
    String generate(String bizCode);
}
