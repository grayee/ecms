/**
 *
 */
package com.qslion.authority.core.util;

import com.qslion.authority.core.dao.AuResourceRepository;
import com.qslion.authority.core.entity.AuAuthorize;
import com.qslion.authority.core.entity.AuFuncMenu;
import com.qslion.authority.core.entity.AuResource;
import com.qslion.authority.core.service.AuResourceService;
import com.qslion.authority.core.service.AuthorizeService;
import com.qslion.authority.core.service.FuncMenuService;
import com.qslion.authority.core.vo.LoginSessionVo;
import com.qslion.framework.util.DBUtils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * 修改备注：
 */

public class AuHelper {
    //@javax.annotation.Resource
    private static AuResourceService resourceService;
    //@javax.annotation.Resource
    private static AuthorizeService authorizeService;
    //@javax.annotation.Resource
    private static FuncMenuService funcMenuService;
    //@javax.annotation.Resource
    private static AuResourceRepository resourceDao;

    public static boolean hasPriv(String partyId, String resCode, String resType) {
        String strSql = "";
        if ("menu".equals(resType))
            strSql = "value='" + resCode + "' and resource_type='" + "GlobalConstants.getResType_menu" + "'";
        else if ("button".equals(resType))
            strSql = "value='" + resCode + "' and resource_type='" + "GlobalConstants.getResType_butn" + "'";
        else if ("field".equals(resType))
            strSql = "id='" + resCode + "' and resource_type='" + "GlobalConstants.getResType_fild" + "'";
        else if ("record".equals(resType))
            strSql = "id='" + resCode + "' and resource_type='" + "GlobalConstants.getResType_recd" + "'";
        else if ("org".equals(resType)) {
            strSql = "value='" + resCode + "' and resource_type='" + "GlobalConstants.getResType_orga" + "'";
        }

        List list = resourceService.queryByCondition(strSql);
        if ((list == null) || (list.size() == 0))
            return false;

        AuResource resVo = (AuResource) list.get(0);
        if ("1".equals(resVo.getIsPublic()))
            return true;

        Map m_all = authorizeService.getAuByPartyId(partyId, resType);

        return (m_all.keySet().contains(resVo.getId()));
    }

    public static Map getFuncPrivList(String partyId) {
        Map m_func = new HashMap();
        m_func.putAll(authorizeService.getAuByPartyId(partyId, "GlobalConstants.getResType_menu"));
        m_func.putAll(authorizeService.getAuByPartyId(partyId, "GlobalConstants.getResType_butn"));
        return m_func;
    }

    public static Map getFuncPrivList(String partyId, String resType) {
        Map m_func = new HashMap();
        if ("menu".equals(resType))
            m_func = authorizeService.getAuByPartyId(partyId, "GlobalConstants.getResType_menu");
        else if ("button".equals(resType))
            m_func = authorizeService.getAuByPartyId(partyId, "GlobalConstants.getResType_butn");

        return m_func;
    }

    public static Map getDataPrivList(String partyId) {
        Map m_data = new HashMap();
        m_data.putAll(authorizeService.getAuByPartyId(partyId, "GlobalConstants.getResType_fild"));
        m_data.putAll(authorizeService.getAuByPartyId(partyId, "GlobalConstants.getResType_recd"));
        if (m_data != null) {
            String AU_PERMIT = "GlobalConstants.getAuTypePermit()";
            Iterator it = m_data.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                AuAuthorize auVo = (AuAuthorize) m_data.get(key);
                String auStatus = auVo.getAuthorizeStatus();
                if (!(AU_PERMIT.equals(auStatus)))
                    m_data.remove(key);
            }
        }

        m_data.putAll(authorizeService.getAuByPartyId(partyId, "GlobalConstants.getResType_orga()"));
        return m_data;
    }

    public static Map getDataPrivList(String partyId, String resType) {
        Map m_data = new HashMap();
        if ("field".equals(resType))
            m_data = authorizeService.getAuByPartyId(partyId, "GlobalConstants.getResType_fild()");
        else if ("record".equals(resType))
            m_data = authorizeService.getAuByPartyId(partyId, "GlobalConstants.getResType_recd()");
        else if ("org".equals(resType)) {
            m_data = authorizeService.getAuByPartyId(partyId, "GlobalConstants.getResType_orga()");
        }

        if ((m_data != null) && (!("org".equals(resType)))) {
            String AU_PERMIT = "GlobalConstants.getAuTypePermit()";
            Iterator it = m_data.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                AuAuthorize auVo = (AuAuthorize) m_data.get(key);
                String auStatus = auVo.getAuthorizeStatus();
                if (!(AU_PERMIT.equals(auStatus)))
                    m_data.remove(key);
            }
        }

        return m_data;
    }

    public static void filterFieldPrivInVo(Object vo, String tableName, HttpServletRequest request) {
        List list = resourceService.queryByCondition("table_name='" + tableName + "' and resource_type='" + "GlobalConstants.getResType_fild()" + "'");

        if ((list == null) || (list.size() == 0))
            return;

        Map m_data = LoginHelper.getOwnerFild(request);
        Map m_filter = new HashMap();
        for (int i = 0; i < list.size(); ++i) {
            AuResource resVo = (AuResource) list.get(i);
            if (!("1".equals(resVo.getIsPublic()))) {
                if (m_data.keySet().contains(resVo.getId()))
                    //break label162;
                    label162:m_filter.put(resVo.getFieldName(), null);
            }
        }
        BeanWrapper bw = new BeanWrapperImpl(vo);
        PropertyDescriptor[] pd = bw.getPropertyDescriptors();
        for (int i = 0; i < pd.length; ++i) {
            String name = pd[i].getName();
            if ((!("class".equals(name))) && (m_filter.keySet().contains(name.toUpperCase())))
                try {
                    bw.setPropertyValue(name, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    public static String filterRecordPrivInSQL(String strSql, LoginSessionVo authorizedContext) {
        strSql = strSql.toLowerCase();
        String tempSql = strSql;
        int index = tempSql.lastIndexOf("from");
        tempSql = tempSql.substring(index + 4);
        index = tempSql.indexOf("where");
        if (index != -1)
            tempSql = tempSql.substring(0, index);

        index = tempSql.indexOf("order");
        if (index != -1)
            tempSql = tempSql.substring(0, index);

        Map map = new HashMap();
        String patternStr = ",";
        String patternAs = "as";
        String patternBlank = " ";
        String tempPattern = "";
        String[] fields = tempSql.split(",");

        for (int i = 0; i < fields.length; ++i) {
            String tempField = fields[i].trim();
            if (tempField.indexOf("as") != -1)
                tempPattern = "as";
            else if (tempField.indexOf(" ") != -1)
                tempPattern = " ";

            if (!("".equals(tempPattern))) {
                String[] table = tempField.split(tempPattern);
                map.put(table[1].trim(), table[0].trim());
            } else map.put(tempField, tempField);

        }

        Map recordMap = authorizedContext.getOwner_recd_map();

        Set valueSet = map.keySet();
        Iterator valueIt = valueSet.iterator();

        int orderbyIndex = strSql.indexOf("order");
        String strSqlBeforeOrderby = (orderbyIndex == -1) ? strSql : strSql.substring(0, orderbyIndex);
        String strSqlAfterOrderby = (orderbyIndex == -1) ? "" : strSql.substring(orderbyIndex);
        while (valueIt.hasNext()) {
            String nameOfName = (String) valueIt.next();
            String nameOfTable = (String) map.get(nameOfName);

            AuResource auResourceVo = new AuResource();
            auResourceVo.setTableName(nameOfTable.toUpperCase());
            auResourceVo.setResourceType("4");
            List<AuResource> IdList = null;//resourceDao.queryIdByTableNameAndAuResourceType(auResourceVo);

            for (int i = 0; i < IdList.size(); ++i) {
                AuResource auResourceVoForSQL = (AuResource) IdList.get(i);
                if (recordMap.containsKey(auResourceVoForSQL.getId()))
                    strSqlBeforeOrderby = sqlAssembly(auResourceVoForSQL, strSqlBeforeOrderby, nameOfName, nameOfTable);
            }
        }

        strSql = strSqlBeforeOrderby + " " + strSqlAfterOrderby;
        return strSql;
    }

    public static String sqlAssembly(AuResource auResourceVoForSQL, String strSqlBeforeOrderby, String nameOfName, String nameOfTable) {
        String value = auResourceVoForSQL.getValue();

        String fieldName = auResourceVoForSQL.getFieldName();

        fieldName = nameOfName + "." + fieldName;

        String fileter_type = (auResourceVoForSQL.getFilterType() == null) ? "" : auResourceVoForSQL.getFilterType();

        if (strSqlBeforeOrderby.indexOf("where") != -1) {
            strSqlBeforeOrderby = strSqlBeforeOrderby + " and " + fieldName + " " + fileter_type + " " + value;
        } else {
            strSqlBeforeOrderby = strSqlBeforeOrderby + " where " + fieldName + " " + fileter_type + " " + value;
        }

        return strSqlBeforeOrderby;
    }

    public static String filterOrgPrivInSQL(String strSql, String fieldName, HttpServletRequest request) {
        String[] arrayDataPriv = LoginHelper.getOwnerOrg(request);
        int iLen = 0;
        if (arrayDataPriv != null)
            iLen = arrayDataPriv.length;

        String strDataPriv = " (1=2 ";
        for (int i = 0; i < iLen; ++i) {
            if ("".equals(arrayDataPriv[i]))
                return strSql;

            strDataPriv = strDataPriv + " or " + fieldName + " like '" + arrayDataPriv[i] + "%' ";
        }
        strDataPriv = strDataPriv + " ) ";

        if ((strSql != null) && (strSql.trim().length() > 0))
            strSql = strSql + " and " + strDataPriv;
        else
            strSql = strDataPriv;

        return strSql;
    }

    public static String[] getRolePartyIdByRequest(HttpServletRequest req) {
        String partyid = LoginHelper.getPartyId(req);

        return getRolePartyIdByUserPartyId(partyid);
    }

    public static String[] getRolePartyIdByUserPartyId(String partyid) {
        String[] rolePartyIds = null;

        String reltypeid = "GlobalConstants.getRelaType_role()";

        String strsql = "select parent_code from au_partyrelation where partyid='" + partyid + "' and RELATIONTYPE_ID='" + reltypeid + "'";
        List<String> lBaseRole = null;
      /*  	ProjTools.getCommonBsInstance().doQuery(strsql, new RowMapper() {
	      public Object mapRow(ResultSet rs, int no) throws SQLException {
	        return rs.getString("parent_code");
	      }
	    });*/
        ResultSet rs = null;
        try {
            rs = DBUtils.execute(strsql);
            while (rs.next()) {
                lBaseRole.add(rs.getString("parent_code"));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        HashMap roleMap = new HashMap();
        if ((lBaseRole != null) && (lBaseRole.size() > 0)) {
            for (Iterator it = lBaseRole.iterator(); it.hasNext(); ) {
                String roleCode = (String) it.next();

                String[] codes = TreeTools.splitTreeCode(roleCode);

                for (int i = 0; i < codes.length; ++i)
                    if (!(roleMap.containsKey(codes[i])))
                        roleMap.put(codes[i], null);

            }

            String[] roleCodes = (String[]) roleMap.keySet().toArray(new String[0]);
            String strsql2 = "select partyid from au_partyrelation where code in (" + roleCodes + ")";
            List<String> lAllRole = null;
	      /*  List lAllRole = ProjTools.getCommonBsInstance().doQuery(strsql2, new RowMapper() {
	        public Object mapRow(ResultSet rs, int no) throws SQLException {
	          return rs.getString("partyid");
	        }

	      });*/
            try {
                rs = DBUtils.execute(strsql2);
                while (rs.next()) {
                    lAllRole.add(rs.getString("partyid"));
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            rolePartyIds = (String[]) lAllRole.toArray(new String[0]);
        }

        return rolePartyIds;
    }

    public static List<String> getRoles(String userName) {
        List roleList = new ArrayList();

        String reltypeid = "GlobalConstants.getRelaType_role()";

        String strsql = "select parent_code from au_partyrelation a, au_user b where a.partyid=b.party_id and b.login_id='" + userName + "' and a.RELATIONTYPE_ID='" + reltypeid + "'";
        List<String> lBaseRole = null;
	    	
	/*    ProjTools.getCommonBsInstance().doQuery(strsql, new RowMapper() {
	      public Object mapRow(ResultSet rs, int no) throws SQLException {
	        return rs.getString("parent_code");
	      }
	    });*/
        ResultSet rs = null;
        try {
            rs = DBUtils.execute(strsql);
            while (rs.next()) {
                lBaseRole.add(rs.getString("parent_code"));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        HashMap roleMap = new HashMap();
        if ((lBaseRole != null) && (lBaseRole.size() > 0)) {
            for (Iterator it = lBaseRole.iterator(); it.hasNext(); ) {
                String roleCode = (String) it.next();

                String[] codes = TreeTools.splitTreeCode(roleCode);

                for (int i = 0; i < codes.length; ++i)
                    if (!(roleMap.containsKey(codes[i])))
                        roleMap.put(codes[i], null);

            }

            for (Iterator it = roleMap.keySet().iterator(); it.hasNext(); roleList.add(it.next())) ;
        }

        return roleList;
    }

    public static String[] getUserPartyIdByMenuName(String name) {
        List list = funcMenuService.queryByCondition("name='" + name + "'");
        if ((list == null) || (list.size() == 0))
            return null;

        String totalcode = ((AuFuncMenu) list.get(0)).toString();
        return getUserPartyIdByMenuTotalCode(totalcode);
    }

    public static String[] getUserPartyIdByMenuKeyword(String keyword) {
        List list = funcMenuService.queryByCondition("keyword='" + keyword + "'");
        if ((list == null) || (list.size() == 0))
            return null;

        String totalcode = ((AuFuncMenu) list.get(0)).toString();
        return getUserPartyIdByMenuTotalCode(totalcode);
    }

    public static String[] getUserPartyIdByMenuTotalCode(String totalcode) {
        return authorizeService.getPartyIdByResourceCode(totalcode);
    }
}
