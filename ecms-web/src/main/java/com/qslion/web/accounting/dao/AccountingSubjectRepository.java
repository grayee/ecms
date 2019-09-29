package com.qslion.web.accounting.dao;

import com.qslion.web.accounting.entity.AccountingSubject;
import com.qslion.framework.dao.IGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * 会计科目Repository
 *
 * @author Gray.Z
 * @date 2019/8/27 21:09.
 */
@Repository
public interface AccountingSubjectRepository extends IGenericRepository<AccountingSubject, Long> {

}
