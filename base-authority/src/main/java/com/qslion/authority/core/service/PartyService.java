/**
 *
 */
package com.qslion.authority.core.service;

import com.qslion.authority.core.entity.AuParty;
import com.qslion.framework.service.IGenericService;
import java.util.List;
import java.util.Map;

/**
 * 修改备注：
 */

public interface PartyService extends IGenericService<AuParty, Long> {

    String addParty(AuParty paramAuParty);

    String addPartyAndRelation(AuParty paramAuParty, String paramString1, String paramString2);

    boolean updateParty(AuParty paramAuParty);

    boolean disableAuParty(String paramString);

    int getRecordCount(String paramString, Object paramObject);

    List simpleQuery(int paramInt1, int paramInt2, String paramString1, String paramString2, Object paramObject);

    int enableAuParty(String paramString);

    Object find(String paramString);

    List queryAllAuPartyRelation(String paramString);

    Map getNameMapByKey(List paramList);

    List simpleQueryPerson(int paramInt1, int paramInt2, String paramString, Object paramObject);

    int getRecordCountPerson(Object paramObject);

    List queryAllAuPartyRelationDivPage(int paramInt1, int paramInt2, String paramString, Object paramObject);

    int getRecordCountAuPartyRelation(String paramString);

}
