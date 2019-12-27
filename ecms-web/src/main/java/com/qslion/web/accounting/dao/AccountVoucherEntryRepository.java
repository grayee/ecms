package com.qslion.web.accounting.dao;

import com.qslion.framework.dao.IGenericRepository;
import com.qslion.web.accounting.entity.AccountVoucherEntry;
import org.springframework.stereotype.Repository;

/**
 * 会计凭证分录Repository
 *
 * @author Gray.Z
 * @date 2019/8/27 21:09.
 */
@Repository
public interface AccountVoucherEntryRepository extends IGenericRepository<AccountVoucherEntry, Long> {

}
