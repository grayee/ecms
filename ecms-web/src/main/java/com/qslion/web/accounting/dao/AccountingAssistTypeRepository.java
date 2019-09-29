package com.qslion.web.accounting.dao;

import com.qslion.web.accounting.entity.AccountingAssistType;
import com.qslion.framework.dao.IGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * 辅助核算Repository
 *
 * @author Gray.Z
 * @date 2019/8/27 21:09.
 */
@Repository
public interface AccountingAssistTypeRepository extends IGenericRepository<AccountingAssistType, Long> {

}
