/**
 *
 */
package com.qslion.authority.core.service.impl;

import com.qslion.authority.core.dao.AuPartyRepository;
import com.qslion.authority.core.entity.AuParty;
import com.qslion.authority.core.service.PartyService;
import com.qslion.framework.service.impl.GenericServiceImpl;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 修改备注：
 */
@Service("partyService")
public class PartyServiceImpl extends GenericServiceImpl<AuParty, Long> implements
    PartyService {

    @Autowired
    private AuPartyRepository partyRepository;


    public String addParty(AuParty paramParty) {
        // TODO Auto-generated method stub
        return null;
    }

    public String addPartyAndRelation(AuParty paramParty, String paramString1,
        String paramString2) {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean disableParty(String paramString) {
        // TODO Auto-generated method stub
        return false;
    }

    public int enableParty(String paramString) {
        // TODO Auto-generated method stub
        return 0;
    }

    public Object find(String paramString) {
        // TODO Auto-generated method stub
        return null;
    }

    public Map getNameMapByKey(List paramList) {
        // TODO Auto-generated method stub
        return null;
    }

    public int getRecordCount(String paramString, Object paramObject) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getRecordCountPartyRelation(String paramString) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getRecordCountPerson(Object paramObject) {
        // TODO Auto-generated method stub
        return 0;
    }

    public List queryAllPartyRelation(String paramString) {
        // TODO Auto-generated method stub
        return null;
    }

    public List queryAllPartyRelationDivPage(int paramInt1, int paramInt2,
        String paramString, Object paramObject) {
        // TODO Auto-generated method stub
        return null;
    }

    public List simpleQuery(int paramInt1, int paramInt2, String paramString1,
        String paramString2, Object paramObject) {
        // TODO Auto-generated method stub
        return null;
    }

    public List simpleQueryPerson(int paramInt1, int paramInt2,
        String paramString, Object paramObject) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public boolean disableAuParty(String paramString) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int enableAuParty(String paramString) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getRecordCountAuPartyRelation(String paramString) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List queryAllAuPartyRelation(String paramString) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List queryAllAuPartyRelationDivPage(int paramInt1, int paramInt2,
        String paramString, Object paramObject) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean updateParty(AuParty paramAuParty) {
        // TODO Auto-generated method stub
        return false;
    }

}
