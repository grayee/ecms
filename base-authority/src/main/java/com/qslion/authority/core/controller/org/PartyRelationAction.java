/**
 *
 */
package com.qslion.authority.core.controller.org;

import com.alibaba.fastjson.JSON;
import com.qslion.authority.core.entity.AuPartyRelation;
import com.qslion.authority.core.entity.AuPartyRelationType;
import com.qslion.authority.core.service.ConnectionRuleService;
import com.qslion.authority.core.service.PartyRelationService;
import com.qslion.authority.core.service.PartyService;
import com.qslion.authority.core.util.TreeNode;
import com.qslion.framework.controller.BaseController;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 项目名称：authority
 * 类名称：PartyRelationAction
 * 类描述：  团体关系
 * 创建人：Administrator
 * 创建时间：2011-8-17 下午02:46:48
 * 修改人：Administrator
 * 修改时间：2011-8-17 下午02:46:48
 * 修改备注：
 */
@Controller
public class PartyRelationAction extends BaseController<AuPartyRelation, Long> {

    @Autowired
    public PartyRelationService partyRelationService;

    @Autowired
    public ConnectionRuleService connectionRuleService;
    @Autowired
    public PartyService partyService;


    @RequestMapping(value = "/admin/party/relation/default.jspx")
    public String getDefault() {
        return this.forward("default", false);
    }

    @RequestMapping(value = "/admin/party/relation/manage.jspx")
    public String getManage() {
        return this.forward("manage", false);
    }

    //列表
    @RequestMapping(value = "/admin/party/relation/index.jspx")
    public String list(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String type = request.getParameter("type") == null ? "" : request.getParameter("type");
        String relationTypeId = request.getParameter("relationTypeId") == null ? "" : request.getParameter("relationTypeId");
        if ((null != type && !"".equals(type)) || (null != relationTypeId && !"".equals(relationTypeId))) {
         /*   AuPartyRelationType partyRelationType = new AuPartyRelationType();
            partyRelationType.setId(relationTypeId);
            partyRelationType.setKeyword(type);
            entity = new AuPartyRelation();
            entity.setAuPartyRelationType(partyRelationType);
            if (null == pager) {
                pager = new Pager<AuPartyRelation>();
            }
            pager.setEntity(entity);*/
        }
      /*  pager = this.service.findByPager(pager);
        String checkMsg = request.getParameter("checkMsg");
        if (checkMsg != null && !"".equals(checkMsg)) {
            model.addAttribute("error_msg", checkMsg);
        }
        model.addAttribute("relationTypeId", relationTypeId);
        model.addAttribute("pager", pager);
        model.addAttribute("data", getPartyRelationTree(request, response, model));
        if (pager.getList().size() <= 0) {
            return forward("init", false);
        } else {
            return forward("list", false);
        }*/

      return "";
    }



    //编辑，新增
    @RequestMapping(value = "/admin/party/relation/input.jspx")
    public String input(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String id = request.getParameter("ids");
        if (StringUtils.isNotEmpty(id)) {
            //AuPartyRelation entity = this.partyRelationService.get(id);
            //model.addAttribute("entity", entity);
        }
        return forward("input", false);
    }

    //增加
    @RequestMapping(value = "/admin/party/relation/save.jspx")
    public String insert(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") AuPartyRelation entity) throws UnsupportedEncodingException {
        /*************************
         * 团体关系添加规则：
         * 1.判断该关系是否存在
         * 2.根据父子团体类型ID和团体关系类型ID验证是否符合团体连接规则
         * 3.添加团体关系
         * 4.更新团体关系的叶子节点状态
         * *****************************/
        //设置子团体及其类型ID
      /*  String partyId = entity.getAuParty().getId();
        String relTypeId = entity.getAuPartyRelationType().getId();
        String parentRelId = entity.getId() == null ? "" : entity.getId();

        AuParty party = this.partyService.get(partyId);
        String childTypeId = party.getAuPartyType().getId();
        entity.setAuParty(party);
        entity.setPartytypeId(party.getAuPartyType().getId());
        entity.setName(party.getName());
        entity.setRelationTypeKeyword(party.getPartytypeKeyword());

        String checkMsg = "";
        if (parentRelId != null && !"".equals(parentRelId)) {
            //设置父团体团体关系
            AuPartyRelation parentRelation = this.partyRelationService.get(entity.getId());
            entity.setParentCode(parentRelation.getId());
            entity.setParentPartyid(parentRelation.getAuParty().getId());
            //校验连接规则
            party = this.partyService.get(parentRelation.getAuParty().getId());
            String parentTypeId = party.getAuPartyType().getId();
            boolean flag = connectionRuleService.checkRule(parentTypeId, childTypeId, relTypeId);
            if (flag) {
                entity.setIsLeaf("1");
                entity.setTypeIsLeaf("1");
                entity.setIsChief("1");
                entity.setIsInherit("1");
                this.partyRelationService.save(entity);
                //第四步
                parentRelation.setIsLeaf("0");
                this.partyRelationService.update(parentRelation);
            } else {
                checkMsg = "添加失败，没有满足条件的连接规则!";
            }
        } else {
            //设置根节点
            boolean flag = connectionRuleService.checkRule(childTypeId, "", relTypeId);
            if (flag) {
                entity.setIsLeaf("1");
                entity.setTypeIsLeaf("1");
                entity.setIsChief("1");
                entity.setIsInherit("1");
                this.partyRelationService.save(entity);
            } else {
                checkMsg = "添加根节点失败，没有满足条件的连接规则!";
            }
        }*/
        return "";//forward("index", true) + "?relationTypeId=" + relTypeId + "&checkMsg=" + URLEncoder.encode(checkMsg, "UTF-8");
    }

    //删除
    @RequestMapping(value = "/admin/party/relation/deletes.jspx")
    public String deletes(HttpServletRequest request, HttpServletResponse response, ModelMap model, String[] ids) {
        String relationTypeId = request.getParameter("relationTypeId");
       // partyRelationService.delete(ids);
        logger.info("--------------------deletes!!!!!------------------------");
        return forward("index", true) + "?relationTypeId=" + relationTypeId;
    }

    //更新
    @RequestMapping(value = "/admin/party/relation/update.jspx")
    public String update(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") AuPartyRelation entity) {
        partyRelationService.update(entity);
        return forward("index", true);
    }

    //查看，明细
    @RequestMapping(value = "/admin/party/relation/view.jspx")
    public String detail(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String id = request.getParameter("ids");
        if (StringUtils.isNotEmpty(id)) {
            model.addAttribute("entity", AuPartyRelationType.values());
        }
        return forward("view", false);
    }

    //局部关系树
    public String getPartyRelationTree(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String relationTypeId = request.getParameter("relationTypeId") == null ? "" : request.getParameter("relationTypeId");
        List<TreeNode> resultList = this.partyRelationService.getPartyRelationTree(relationTypeId, false);
        return JSON.toJSONString(resultList);
    }

    //全局关系树
    @RequestMapping(value = "/admin/party/relation/tree.jspx")
    public String getGlobalPartyRelationTree(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        List<TreeNode> resultList = this.partyRelationService.getGlobalRelationTree();
        model.addAttribute("data", JSON.toJSONString(resultList));
        return forward("tree", false);
    }

    public String forward(String viewName, boolean isRedirect) {
        String baseUrl = "";
        if (isRedirect) {
            baseUrl = "redirect:/admin/party/relation/";
            return baseUrl + viewName + ".jspx";
        } else {
            baseUrl = "authority/org/party/relation/";
            return baseUrl + viewName;
        }
    }

}
