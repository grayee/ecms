/**
 *
 */
package com.qslion.core.service;

import com.qslion.core.entity.AuPartyRole;
import com.qslion.framework.service.IGenericService;
import java.util.List;
import java.util.Map;

/**
 * 修改备注：
 */

public interface AuPartyRoleService extends IGenericService<AuPartyRole, Long> {
    public abstract int deleteByVisitorId(String paramString);

    public abstract int deleteByResourceId(String paramString);

    public abstract Map getAuByVisitorId(String paramString1, String paramString2);

    public abstract Map getAuByVisitorCode(String paramString1, String paramString2);

    public abstract Map getExtendAuByVisitorCode(String paramString1, String paramString2);

    public abstract Map getAuByRelList(List paramList, String paramString);

    public abstract Map getAuByPartyId(String paramString1, String paramString2, String paramString3);

    public abstract Map getAuByPartyId(String paramString1, String paramString2);

    public abstract boolean saveAu(String paramString1, List paramList, String paramString2);

    public abstract boolean saveOrgAu(String paramString1, String paramString2, String paramString3, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, String[] paramArrayOfString4);

    public abstract List parsePartyIdToVisitor(String paramString);

    public abstract String[] getPartyIdByResourceCode(String paramString);

    public abstract List parseVisitorToPartyId(String[] paramArrayOfString, String paramString);
}
