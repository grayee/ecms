package com.qslion.authority.core.util;

import com.qslion.authority.core.entity.AuParty;
import com.qslion.authority.core.entity.AuPartyRelation;
import com.qslion.authority.core.service.PartyRelationService;
import com.qslion.authority.core.service.PartyService;
import com.qslion.framework.util.SpringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class OrgHelper {
    private static String partyRelationService = "partyRelationServiceImpl";
    private static String partyService = "partyServiceImpl";
    private static String partyTypeService = "partyTypeServiceImpl";

    public static PartyRelationService getPartyRelationService() {
        return (PartyRelationService) SpringUtil.getBean(partyRelationService);
    }

    public static PartyService getPartyService() {
        return (PartyService) SpringUtil.getBean(partyService);
    }


    //根据团体ID取得关系表CODE数组
    public static String[] getRelationCode(String partyId) {
        AuPartyRelation queryVo = new AuPartyRelation();
        // queryVo.setPartyid(partyId);
        // queryVo.setRelationtypeId("????????????");
        List rel = getPartyRelationService().queryPartyRelation(queryVo);

        if ((rel == null) || (rel.size() == 0))
            return null;

        ArrayList codes = new ArrayList();
        for (int i = 0; i < rel.size(); ++i) {
            AuPartyRelation relVo = (AuPartyRelation) rel.get(i);
            codes.add(relVo.getCode());
        }
        return ((String[]) codes.toArray(new String[0]));
    }

    //根据团体ID和团体类型取得关系表CODE数组
    public static String[] getRelationCode(String partyId, String partyType) {
        AuPartyRelation queryVo = new AuPartyRelation();
        queryVo.getAuParty().setId(partyId);
        List rel = getPartyRelationService().queryPartyRelation(queryVo);

        if ((rel == null) || (rel.size() == 0))
            return new String[]{"???????????" + "00001"};

        ArrayList codes = new ArrayList();
        for (int i = 0; i < rel.size(); ++i) {
            AuPartyRelation relVo = (AuPartyRelation) rel.get(i);
            List pRel = null;
            for (int j = pRel.size() - 1; j >= 0; --j) {
                AuPartyRelation pRelVo = (AuPartyRelation) pRel.get(j);
               /* if (partyType.equals(pRelVo.getPartytypeId())) {
                    codes.add(pRelVo.getCode());
                    break;
                }*/
            }
        }
        return ((String[]) codes.toArray(new String[0]));
    }

    //获取公司CODE
    public static String[] getCompanyCode(String partyId) {
        return getRelationCode(partyId, "GlobalConstants.getPartyType_comp");
    }

    //获取部门CODE
    public static String[] getDepartmentCode(String partyId) {
        return getRelationCode(partyId, "GlobalConstants.getPartyType_dept");
    }

    //获取岗位CODE
    public static String[] getPositionCode(String partyId) {
        return getRelationCode(partyId, "GlobalConstants.getPartyType_posi");
    }

    //根据关系ID获得团体ID
    public static String getPartyIDByRelationID(String id) {
        AuPartyRelation vo = new AuPartyRelation();
        vo.setId(id);
        List list = getPartyRelationService().queryPartyRelation(vo);
        if ((list == null) || (list.size() == 0))
            try {
                throw new Exception("查不到数据！");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        return ((AuPartyRelation) list.get(0)).getAuParty().getId();
    }

    //根据ID获取下级关系节点的ID
    public static List getSubRelationListByID(String id) {
        AuPartyRelation vo = new AuPartyRelation();
        vo.setId(id);
        List list = getPartyRelationService().queryPartyRelation(vo);
        if ((list == null) || (list.size() == 0))
            return new ArrayList();

        String code = ((AuPartyRelation) list.get(0)).getCode();
        vo = new AuPartyRelation();
        vo.setParentId(code);
        return getPartyRelationService().queryPartyRelation(vo);
    }

    //根据ID获取上级关系节点实体
    public static AuPartyRelation getUpRelationVoByID(String id) {
        AuPartyRelation vo = new AuPartyRelation();
        vo.setId(id);
        List list = getPartyRelationService().queryPartyRelation(vo);
        if ((list == null) || (list.size() == 0))
            return null;

        String parentCode = ((AuPartyRelation) list.get(0)).getParentId();

        vo = new AuPartyRelation();
        vo.setCode(parentCode);
        list = getPartyRelationService().queryPartyRelation(vo);
        if ((list == null) || (list.size() == 0))
            return null;

        return ((AuPartyRelation) list.get(0));
    }

    //
    public static List getSubRelationListByCode(String code) {
        AuPartyRelation vo = new AuPartyRelation();
        vo.setParentId(code);
        return getPartyRelationService().queryPartyRelation(vo);
    }

    public static AuPartyRelation getUpRelationVoByCode(String code) {
        AuPartyRelation vo = new AuPartyRelation();
        vo.setCode(code);
        List list = getPartyRelationService().queryPartyRelation(vo);
        if ((list == null) || (list.size() == 0))
            return null;

        String parentCode = ((AuPartyRelation) list.get(0)).getParentId();

        vo = new AuPartyRelation();
        vo.setCode(parentCode);
        list = getPartyRelationService().queryPartyRelation(vo);
        if ((list == null) || (list.size() == 0))
            return null;

        return ((AuPartyRelation) list.get(0));
    }

    public static AuParty getPartyVoByID(String partyId) {
        return ((AuParty) getPartyService().find(partyId));
    }

    public static AuParty getPartyVoByCode(String code) {
        AuPartyRelation vo = new AuPartyRelation();
        vo.setCode(code);
        List list = getPartyRelationService().queryPartyRelation(vo);
        if ((list == null) || (list.size() == 0))
            return null;

        String partyId = ((AuPartyRelation) list.get(0)).getAuParty().getId();
        return ((AuParty) getPartyService().find(partyId));
    }

    public static Map getNameMapByIDList(ArrayList lPartyId) {
        return getPartyService().getNameMapByKey(lPartyId);
    }




    public static String addParty(AuParty vo) {
        return getPartyService().addParty(vo);
    }

    public static boolean updateParty(AuParty vo) {
        return getPartyService().updateParty(vo);
    }

    public static boolean deleteParty(String partyId) {
        getPartyService().delete(partyId);
        return true;
    }

    public static boolean addRoot(String partyId, String partyRelationTypeId) {
        return getPartyRelationService().initRoot(partyId, partyRelationTypeId);
    }

    public static boolean addAuPartyRelation(String childPartyId, String parentRelId, String relTypeId) {
        return getPartyRelationService().addPartyRelation(childPartyId, parentRelId, relTypeId);
    }

    public static boolean deleteAuPartyRelation(String id) {
        return getPartyRelationService().deletePartyRelation(id);
    }

    public static void main(String args[]) {
        getRelationCode("0000");
        System.out.println("------------");
    }
}
