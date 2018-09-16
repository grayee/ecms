/**
 *
 */
package com.qslion.core.service;

import com.qslion.core.entity.AuUser;
import com.qslion.framework.service.IGenericService;

/**
 * @version
 *
 */
public interface AuUserService extends IGenericService<AuUser, Long> {

    AuUser findUserByUsername(String username);

    int getRecordCount();

    int getRecordCount(String paramString);

    String getCurrentUsername();

}
