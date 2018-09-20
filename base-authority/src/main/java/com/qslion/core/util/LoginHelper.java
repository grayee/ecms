/**
 *
 */
package com.qslion.core.util;

import com.qslion.core.entity.AuMenu;
import com.qslion.core.entity.AuPartyRelation;
import com.qslion.core.entity.AuUser;
import com.qslion.core.service.AuMenuService;
import com.qslion.core.vo.LoginSessionVo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 修改备注：
 */

public class LoginHelper {
    //@Autowired
    public static AuMenuService auMenuService;

    //通过request对象取得登陆Session
    public static LoginSessionVo getLoginVo(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        LoginSessionVo vo = null;
        if ((session != null) && (session.getAttribute("LOGIN_SESSION_VO") != null))
            vo = (LoginSessionVo) session.getAttribute("LOGIN_SESSION_VO");

        return vo;
    }

    //通过session对象取得登陆Session
    public static LoginSessionVo getLoginVo(HttpSession session) {
        LoginSessionVo vo = null;
        if ((session != null) && (session.getAttribute("LOGIN_SESSION_VO") != null))
            vo = (LoginSessionVo) session.getAttribute("LOGIN_SESSION_VO");
        return vo;
    }

    //取得登陆名称
    public static String getLoginName(HttpServletRequest req) {
        LoginSessionVo vo = getLoginVo(req);
        if (vo == null)
            return null;
        return vo.getName();
    }

    //取得登陆ID
    public static String getLoginId(HttpServletRequest req) {
        LoginSessionVo vo = getLoginVo(req);
        if (vo == null)
            return null;
        return vo.getLogin_id();
    }

    //取得登陆团体ID
    public static String getPartyId(HttpServletRequest req) {
        LoginSessionVo vo = getLoginVo(req);
        if (vo == null)
            return null;
        return vo.getParty_id();
    }

    //是否是管理员登陆
    public static boolean getIsAdmin(HttpServletRequest req) {
        LoginSessionVo vo = getLoginVo(req);
        if (vo == null)
            return false;
        return (vo.getIs_admin().equals("1"));
    }

    //通过request对象取得公司code数组
    public static String[] getCompanyCode(HttpServletRequest req) {
        LoginSessionVo vo = getLoginVo(req);
        if (vo == null)
            return null;

        List rel = vo.getRelation_vo_list();
        if ((rel == null) || (rel.size() == 0))
            return new String[]{"GlobalConstants.getRelaType_comp()" + "00001"};

        ArrayList codes = new ArrayList();
        for (int i = 0; i < rel.size(); ++i) {
            AuPartyRelation relVo = (AuPartyRelation) rel.get(i);
         /* List pRel = relVo.getA
	      for (int j = pRel.size() - 1; j >= 0; --j) {
	        AuPartyRelation pRelVo = (AuPartyRelation)pRel.get(j);
	        if ("GlobalConstants.getPartyType_comp()".equals(pRelVo.getPartytypeId())) {
	          codes.add(pRelVo.getCode());
	          break;
	        }
	      }*/
        }
        return ((String[]) codes.toArray(new String[0]));
    }

    //通过request对象取得公司名称数组
    public static String[] getCompanyName(HttpServletRequest req) {
        LoginSessionVo vo = getLoginVo(req);
        if (vo == null)
            return null;

        List rel = vo.getRelation_vo_list();
        if ((rel == null) || (rel.size() == 0))
            return new String[]{"-"};

        ArrayList names = new ArrayList();
        for (int i = 0; i < rel.size(); ++i) {
            AuPartyRelation relVo = (AuPartyRelation) rel.get(i);
	     /* List pRel = relVo.getAllParentVo();
	      for (int j = pRel.size() - 1; j >= 0; --j) {
	        AuPartyRelation pRelVo = (AuPartyRelation)pRel.get(j);
	        if ("GlobalConstants.getPartyType_comp()".equals(pRelVo.getPartytypeId())) {
	          names.add(pRelVo.getName());
	          break;
	        }
	      }*/
        }
        return ((String[]) names.toArray(new String[0]));
    }

    //通过request对象取得部门code数组
    public static String[] getDepartmentCode(HttpServletRequest req) {
        LoginSessionVo vo = getLoginVo(req);
        if (vo == null)
            return null;

        List rel = vo.getRelation_vo_list();
        if ((rel == null) || (rel.size() == 0))
            return new String[]{"GlobalConstants.getRelaType_comp()" + "00001"};

	   /* ArrayList codes = new ArrayList();
	    for (int i = 0; i < rel.size(); ++i) {
	      AuPartyRelation relVo = (AuPartyRelation)rel.get(i);
	      List pRel = relVo.getAllParentVo();
	      for (int j = pRel.size() - 1; j >= 0; --j) {
	        AuPartyRelation pRelVo = (AuPartyRelation)pRel.get(j);
	        if ("GlobalConstants.getPartyType_dept()".equals(pRelVo.getPartytypeId())) {
	          codes.add(pRelVo.getCode());
	          break;
	        }
	      }
	    }*/
        return null; //((String[])codes.toArray(new String[0]));
    }

    //通过request对象取得部门名称数组
    public static String[] getDepartmentName(HttpServletRequest req) {
        LoginSessionVo vo = getLoginVo(req);
        if (vo == null)
            return null;

        List rel = vo.getRelation_vo_list();
        if ((rel == null) || (rel.size() == 0))
            return new String[]{"-"};

        ArrayList names = new ArrayList();
        for (int i = 0; i < rel.size(); ++i) {
            AuPartyRelation relVo = (AuPartyRelation) rel.get(i);
	    /*  List pRel = relVo.getAllParentVo();
	      for (int j = pRel.size() - 1; j >= 0; --j) {
	        AuPartyRelation pRelVo = (AuPartyRelation)pRel.get(j);
	        if ("GlobalConstants.getPartyType_dept()".equals(pRelVo.getPartytypeId())) {
	          names.add(pRelVo.getName());
	          break;
	        }
	      }*/
        }
        return ((String[]) names.toArray(new String[0]));
    }

    public static String[] getPositionCode(HttpServletRequest req) {
        LoginSessionVo vo = getLoginVo(req);
        if (vo == null)
            return null;

        List rel = vo.getRelation_vo_list();
        if ((rel == null) || (rel.size() == 0))
            return new String[]{"GlobalConstants.getRelaType_comp()" + "00001"};

        ArrayList codes = new ArrayList();
        for (int i = 0; i < rel.size(); ++i) {
            AuPartyRelation relVo = (AuPartyRelation) rel.get(i);
	   /*   List pRel = relVo.getAllParentVo();
	      for (int j = pRel.size() - 1; j >= 0; --j) {
	        AuPartyRelation pRelVo = (AuPartyRelation)pRel.get(j);
	        if ("GlobalConstants.getPartyType_posi()".equals(pRelVo.getPartytypeId())) {
	          codes.add(pRelVo.getCode());
	          break;
	        }
	      }*/
        }
        return ((String[]) codes.toArray(new String[0]));
    }

    public static String[] getPositionName(HttpServletRequest req) {
        LoginSessionVo vo = getLoginVo(req);
        if (vo == null)
            return null;

        List rel = vo.getRelation_vo_list();
        if ((rel == null) || (rel.size() == 0))
            return new String[]{"-"};

	   /* ArrayList names = new ArrayList();
	    for (int i = 0; i < rel.size(); ++i) {
	      AuPartyRelation relVo = (AuPartyRelation)rel.get(i);
	      List pRel = relVo.getAllParentVo();
	      for (int j = pRel.size() - 1; j >= 0; --j) {
	        AuPartyRelation pRelVo = (AuPartyRelation)pRel.get(j);
	        if ("GlobalConstants.getPartyType_posi()".equals(pRelVo.getPartytypeId())) {
	          names.add(pRelVo.getName());
	          break;
	        }
	      }
	    }*/
        return null;//((String[])names.toArray(new String[0]));
    }

    public static String[] getEmployeeCode(HttpServletRequest req) {
        LoginSessionVo vo = getLoginVo(req);
        if (vo == null)
            return null;

        List rel = vo.getRelation_vo_list();
        if ((rel == null) || (rel.size() == 0))
            return new String[]{" GlobalConstants.getRelaType_comp()" + "00001"};

        String[] codes = new String[rel.size()];
        for (int i = 0; i < rel.size(); ++i)
            codes[i] = ((AuPartyRelation) rel.get(i)).getCode();

        return codes;
    }

    public static String[] getOwnerOrg(HttpServletRequest req) {
        LoginSessionVo vo = getLoginVo(req);
        if (vo == null)
            return null;
        return vo.getOwner_org_arr();
    }

    public static Map getOwnerMenu(HttpServletRequest req) {
        LoginSessionVo vo = getLoginVo(req);
        if (vo == null)
            return null;
        return vo.getOwner_menu_map();
    }

    public static Map getOwnerButn(HttpServletRequest req) {
        LoginSessionVo vo = getLoginVo(req);
        if (vo == null)
            return null;
        return vo.getOwner_butn_map();
    }

    public static Map getOwnerFild(HttpServletRequest req) {
        LoginSessionVo vo = getLoginVo(req);
        if (vo == null)
            return null;
        return vo.getOwner_fild_map();
    }

    public static Map getOwnerRecd(HttpServletRequest req) {
        LoginSessionVo vo = getLoginVo(req);
        if (vo == null)
            return null;
        return vo.getOwner_recd_map();
    }

    public static Map getOwnerMenuUrl(HttpServletRequest req) {
        LoginSessionVo vo = getLoginVo(req);
        if (vo == null)
            return null;
        return vo.getOwner_menu_url_map();
    }

    public static Map getAllMenuUrl(HttpServletRequest req) {
        LoginSessionVo vo = getLoginVo(req);
        if (vo == null)
            return null;
        return vo.getAll_menu_url_map();
    }

    public static String[] getOwnerOrg4Admin(HttpServletRequest req) {
        LoginSessionVo vo = getLoginVo(req);
        if (vo == null)
            return null;
        return vo.getOwner_org_arr_admin();
    }

    public static Map getOwnerMenu4Admin(HttpServletRequest req) {
        LoginSessionVo vo = getLoginVo(req);
        if (vo == null)
            return null;
        return vo.getOwner_menu_map_admin();
    }

    public static Map getOwnerButn4Admin(HttpServletRequest req) {
        LoginSessionVo vo = getLoginVo(req);
        if (vo == null)
            return null;
        return vo.getOwner_butn_map_admin();
    }

    public static Map getOwnerFild4Admin(HttpServletRequest req) {
        LoginSessionVo vo = getLoginVo(req);
        if (vo == null)
            return null;
        return vo.getOwner_fild_map_admin();
    }

    public static Map getOwnerRecd4Admin(HttpServletRequest req) {
        LoginSessionVo vo = getLoginVo(req);
        if (vo == null)
            return null;
        return vo.getOwner_recd_map_admin();
    }

    public static Map getOwnerFunc(HttpServletRequest req) {
        LoginSessionVo vo = getLoginVo(req);
        if (vo == null)
            return null;
        return vo.getOwner_func_map();
    }

    public static Map getOwnerFunc4Admin(HttpServletRequest req) {
        LoginSessionVo vo = getLoginVo(req);
        if (vo == null)
            return null;
        return vo.getOwner_func_map_admin();
    }

    public static boolean hasButnAu(HttpSession session, String keyword) {
        LoginSessionVo vo = getLoginVo(session);
        if (vo == null)
            return false;
        if ("1".equals(vo.getIs_admin()))
            return true;

        Map map = vo.getOwner_butn_map();
        if (map == null)
            return false;
        for (Iterator it = map.keySet().iterator(); it.hasNext(); ) {
            AuMenu fVo = (AuMenu) map.get((String) it.next());
            //if (keyword.equals(fVo.getKeyword()))
                return true;
        }

        return false;
    }

    public static int validate(String login_id, String password) {
        List lResult = auMenuService.queryByCondition("login_id='" + login_id + "'");

        if ((lResult != null) && (lResult.size() > 0)) {
            AuUser userVo = (AuUser) lResult.get(0);
            if (password.equals(userVo.getPassword())) {
                if ("1".equals(userVo.getEnableStatus()))
                    return 1;

                return -1;
            }

            return 0;
        }

        return -2;
    }

}
