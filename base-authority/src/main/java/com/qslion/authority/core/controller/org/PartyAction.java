/**
 *
 */
package com.qslion.authority.core.controller.org;

import com.qslion.authority.core.entity.AuParty;
import com.qslion.authority.core.service.ConnectionRuleService;
import com.qslion.authority.core.service.PartyRelationService;
import com.qslion.authority.core.service.PartyService;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.controller.BaseController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 项目名称：authority 类名称：PartyAction 类描述： 创建人：Administrator 创建时间：2011-8-17 下午02:43:01 修改人：Administrator
 * 修改时间：2011-8-17 下午02:43:01 修改备注：
 */
@Controller
public class PartyAction extends BaseController<AuParty, Long> {

    /**
     *
     */
    private static final long serialVersionUID = -8996250686528510901L;

    @Autowired
    public PartyService partyService;

    @Autowired
    public PartyRelationService partyRelationService;

    @Autowired
    public ConnectionRuleService connectionRuleService;

    @RequestMapping(value = "/admin/party/default.jspx")
    public String getDefault() {
        return this.forward("default", false);
    }

    @RequestMapping(value = "/admin/party/manage.jspx")
    public String getManage() {
        return this.forward("manage", false);
    }

    @RequestMapping(value = "/admin/party/index.jspx")
    public String list(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("pager") Pager<AuParty> pager) {
        String type = request.getParameter("type") == null ? "" : request.getParameter("type");
        String typeId = request.getParameter("typeId") == null ? "" : request.getParameter("typeId");
        if ((null != type && !"".equals(type)) || (null != typeId && !"".equals(typeId))) {
         /*   AuPartyType partyType = new AuPartyType();
            partyType.setId(typeId);
            partyType.setKeyword(type);
            entity = new AuParty();
            entity.setAuPartyType(partyType);
            if (null == pager) {
                pager = new Pager<AuParty>();
            }
            pager.setEntity(entity);*/
        }
        String checkMsg = request.getParameter("checkMsg");
        if (checkMsg != null && !"".equals(checkMsg)) {
            model.addAttribute("error_msg", checkMsg);
        }
        /*pager = this.service.findByPager(pager);
        model.addAttribute("pager", pager);
        model.addAttribute("entity", entity);*/
        return this.forward("list", false);
    }

    // 查看
    @RequestMapping(value = "/admin/party/view.jspx")
    public String view(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
      /*  entity = this.service.get(request.getParameter("ids"));
        List<AuPartyRelation> relations = new ArrayList<AuPartyRelation>();
        for (AuPartyRelation relation : entity.getAuPartyRelations()) {
            //得到父团体关系，根据父团体关系得到父团体名称
            if (null != relation.getParentId() && !"".equals(relation.getParentId())) {
                String parentPartyId = partyRelationService.get(relation.getParentId()).getAuParty().getId();
                String parentPartyName = partyService.get(parentPartyId).getName();
                relation.setParentPartyName(parentPartyName == null ? "" : parentPartyName);
            } else {
                relation.setParentPartyName("根节点");
            }

            relations.add(relation);
        }
        Pager<AuPartyRelation> pager = new Pager<AuPartyRelation>();
        pager.setList(relations);
        pager.setTotalCount(relations.size());
        model.addAttribute("entity", entity);
        model.addAttribute("pager", pager);*/
        return forward("view", false);
    }

    //增加
    @RequestMapping(value = "/admin/party/save.jspx")
    public String insert(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") AuParty entity) {
        //this.partyService.insert(entity);
        logger.info("--------------------save!!!!!------------------------");
        return forward("index", true) + "?typeId=" + entity.getAuPartyType().getId();
    }

    //删除
    @RequestMapping(value = "/admin/party/deletes.jspx")
    public String deletes(HttpServletRequest request, HttpServletResponse response, ModelMap model, String[] ids, String typeId) {
        //partyService.delete(ids);
        logger.info("--------------------deletes!!!!!------------------------");
        return forward("index", true) + "?typeId=" + typeId;
    }

    //更新
    @RequestMapping(value = "/admin/party/update.jspx")
    public String update(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") AuParty entity) {
        partyService.update(entity);
        return forward("index", true);
    }

    //编辑，新增
    @RequestMapping(value = "/admin/party/input.jspx")
    public String input(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") AuParty entity) {
        String id = request.getParameter("ids");
        if (StringUtils.isNotEmpty(id)) {
            //entity = this.partyService.get(id);
        }
        model.addAttribute("entity", entity);
        return forward("input", false);
    }

    //添加团体关系
    @RequestMapping(value = "/admin/party/addrelation.jspx")
    public String addRelation(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") AuParty entity) throws UnsupportedEncodingException {
   /*     String parentCode = request.getParameter("parentCode");
        AuPartyRelation parentRelation = this.partyRelationService.get(parentCode);
        AuParty party = this.partyService.get(entity.getId());

        AuPartyRelation relation = new AuPartyRelation();
        relation.setName(party.getName());
        relation.setAuParty(party);
        relation.setPartytypeId(party.getAuPartyType().getId());
        relation.setTypeLevel(Short.valueOf(request.getParameter("relationLevel")));
        relation.setParentCode(parentCode);
        relation.setAuPartyRelationType(parentRelation.getAuPartyRelationType());
        relation.setParentPartyid(parentRelation.getAuParty().getId());

        String checkMsg = "";
        String childTypeId = party.getAuPartyType().getId();
        entity = this.partyService.get(parentRelation.getAuParty().getId());
        String parentTypeId = entity.getAuPartyType().getId();
        String relationTypeId = parentRelation.getAuPartyRelationType().getId();
        boolean flag = connectionRuleService.checkRule(parentTypeId, childTypeId, relationTypeId);
        if (flag) {
            relation.setIsLeaf("1");
            relation.setTypeIsLeaf("1");
            relation.setIsChief("1");
            relation.setIsInherit("1");
            this.partyRelationService.save(relation);
            logger.info("--------------------save!!!!!------------------------");
            //第四步
            parentRelation.setIsLeaf("0");
            this.partyRelationService.update(parentRelation);
            logger.info("--------------------update!!!!!------------------------");
        } else {
            checkMsg = "添加失败，没有满足条件的连接规则!";
            logger.info("--------------------faild!!!!!------------------------");
        }
        if (checkMsg != null && !"".equals(checkMsg)) {
            model.addAttribute("error_msg", checkMsg);
        }*/
        return "";//forward("index", true) + "?typeId=" + party.getAuPartyType().getId() + "&checkMsg=" + URLEncoder.encode(checkMsg, "UTF-8");
    }

    //删除团体关系
    @RequestMapping(value = "/admin/party/delrelation.jspx")
    public String delRelation(HttpServletRequest request, HttpServletResponse response, ModelMap model, String[] ids) {
        String partyId = request.getParameter("id");
        //partyRelationService.delete(ids);
        return forward("view", true) + "?ids=" + partyId;
    }

    public String forward(String viewName, boolean isRedirect) {
        String baseUrl = "";
        if (isRedirect) {
            baseUrl = "redirect:/admin/party/";
            return baseUrl + viewName + ".jspx";
        } else {
            baseUrl = "authority/org/party/";
            return baseUrl + viewName;
        }
    }
}
