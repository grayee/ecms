/**
 *
 */
package com.qslion.core.service.impl;

import com.qslion.core.dao.AuthorizeRepository;
import com.qslion.core.entity.AuAuthorize;
import com.qslion.core.service.AuthorizeService;
import com.qslion.framework.service.impl.GenericServiceImpl;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：authority 类名称：AuthorizeServiceImpl 类描述： 创建人：Administrator 创建时间：2011-8-8 下午03:21:55
 * 修改人：Administrator 修改时间：2011-8-8 下午03:21:55 修改备注：
 */
@Service("authorizeService")
public class AuthorizeServiceImpl extends GenericServiceImpl<AuAuthorize, Long> implements
        AuthorizeService {

    @Autowired
    public AuthorizeRepository authorizeRepository;


    @Override
    public int deleteByVisitorId(String paramString) {
        return 0;
    }

    @Override
    public int deleteByResourceId(String paramString) {
        return 0;
    }

    @Override
    public Map getAuByVisitorId(String paramString1, String paramString2) {
        return null;
    }

    @Override
    public Map getAuByVisitorCode(String paramString1, String paramString2) {
        return null;
    }

    @Override
    public Map getExtendAuByVisitorCode(String paramString1, String paramString2) {
        return null;
    }

    @Override
    public Map getAuByRelList(List paramList, String paramString) {
        return null;
    }

    @Override
    public Map getAuByPartyId(String paramString1, String paramString2, String paramString3) {
        return null;
    }

    @Override
    public Map getAuByPartyId(String paramString1, String paramString2) {
        return null;
    }

    @Override
    public boolean saveAu(String paramString1, List paramList, String paramString2) {
        return false;
    }

    @Override
    public boolean saveOrgAu(String paramString1, String paramString2, String paramString3, String[] paramArrayOfString1,
        String[] paramArrayOfString2, String[] paramArrayOfString3, String[] paramArrayOfString4) {
        return false;
    }

    @Override
    public List parsePartyIdToVisitor(String paramString) {
        return null;
    }

    @Override
    public String[] getPartyIdByResourceCode(String paramString) {
        return new String[0];
    }

    @Override
    public List parseVisitorToPartyId(String[] paramArrayOfString, String paramString) {
        return null;
    }
}
