package com.qslion.web.accounting.dao;

import com.qslion.framework.dao.IGenericRepository;
import com.qslion.web.accounting.entity.AccountVoucher;
import org.springframework.stereotype.Repository;

/**
 * 会计凭证Repository
 *
 * @author Gray.Z
 * @date 2019/8/27 21:09.
 */
@Repository
public interface AccountVoucherRepository extends IGenericRepository<AccountVoucher, Long> {

}
