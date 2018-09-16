/**
 *
 */
package com.qslion.core.service;

import com.qslion.core.entity.AuAuthorize;
import com.qslion.framework.service.IGenericService;
import java.util.List;
import java.util.Map;

/**
 * 修改备注：
 */

public interface AuthorizeService extends IGenericService<AuAuthorize, Long> {

    int deleteByVisitorId(String paramString);

    int deleteByResourceId(String paramString);

    Map getAuByVisitorId(String paramString1, String paramString2);

    Map getAuByVisitorCode(String paramString1, String paramString2);

    Map getExtendAuByVisitorCode(String paramString1, String paramString2);

    Map getAuByRelList(List paramList, String paramString);

    Map getAuByPartyId(String paramString1, String paramString2, String paramString3);

    Map getAuByPartyId(String paramString1, String paramString2);

    boolean saveAu(String paramString1, List paramList, String paramString2);

    boolean saveOrgAu(String paramString1, String paramString2, String paramString3, String[] paramArrayOfString1, String[] paramArrayOfString2,
        String[] paramArrayOfString3, String[] paramArrayOfString4);

    List parsePartyIdToVisitor(String paramString);

    String[] getPartyIdByResourceCode(String paramString);

    List parseVisitorToPartyId(String[] paramArrayOfString, String paramString);
}
