package com.qslion.custom.controller;

import com.alibaba.fastjson.JSON;
import com.qslion.core.entity.AuConnectionRule;
import com.qslion.core.entity.AuParty;
import com.qslion.core.entity.AuPartyRelation;
import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.core.enums.AuPartyType;
import com.qslion.core.service.ConnectionRuleService;
import com.qslion.core.service.PartyRelationService;
import com.qslion.core.service.PartyService;
import com.qslion.framework.bean.TreeNode;
import com.qslion.custom.entity.AuCompany;
import com.qslion.custom.service.AuCompanyService;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.controller.BaseController;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 组织机构控制类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@ResponseResult
@RestController
@RequestMapping(value = "/organization")
public class OrganizationController extends BaseController<AuPartyRelation> {

    @Autowired
    public PartyRelationService partyRelationService;

    @Autowired
    public PartyService partyService;

    @Autowired
    public ConnectionRuleService connectionRuleService;

    @Autowired
    public AuCompanyService companyService;


    //管理页
    @RequestMapping(value = "/admin/relation/manage.jspx")
    public String manage(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("pager") Pager<AuCompany> pager) {
        boolean flag = partyRelationService.hasCustomRoot(AuPartyRelationType.ADMINISTRATIVE.getId() + "");
        if (flag) {
            return "manage";
        } else {
            return "init";
        }
    }

    //添加根节点
    @RequestMapping(value = "/admin/relation/initRoot.jspx")
    public String initRoot(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String partyId = request.getParameter("rootId");
        AuParty party = partyService.findById(Long.valueOf(partyId));
        //行政关系
        partyRelationService.initRoot(party, AuPartyRelationType.ADMINISTRATIVE);
        return "manage";
    }


    //增加
    @RequestMapping(value = "/admin/relation/save.jspx")
    public String insert(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        logger.info("--------------------save!!!!!------------------------");
        return "redirect:/test/index.jspx";
    }

    //删除
    @RequestMapping(value = "/admin/relation/deletes.jspx")
    public String deletes(HttpServletRequest request, HttpServletResponse response, ModelMap model, String id) {
        if (StringUtils.isNotEmpty(id)) {
            AuParty party = null;

            if (party.getAuPartyType() == AuPartyType.COMPANY) {
                return "redirect:/admin/company/deletes.jspx?ids=" + id;
            } else if (party.getAuPartyType() == AuPartyType.DEPARTMENT) {
                return "redirect:/admin/department/deletes.jspx?ids=" + id;
            } else if (party.getAuPartyType() == AuPartyType.POSITION) {
                return "redirect:/admin/position/deletes.jspx?ids=" + id;
            } else if (party.getAuPartyType() == AuPartyType.EMPLOYEE) {
                return "redirect:/admin/employee/deletes.jspx?ids=" + id;
            }
        }
        return "success";
    }

    //更新
    @RequestMapping(value = "/admin/relation/update.jspx")
    public String update(HttpServletRequest request, HttpServletResponse response) {

        return "redirect:/test/index.jspx";
    }

    //编辑，新增
    @RequestMapping(value = "/admin/relation/input.jspx")
    public String input(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        //1.根据ID去的团体类型ID 2.根据团体类型ID为父团体类型ID条件查找符合条件的链接规则
        String id = request.getParameter("id");
        AuParty party = null;
        List<AuConnectionRule> rules = connectionRuleService.getRuleByParentPartyTypeId(party.getAuPartyType().getId() + "", AuPartyRelationType.ADMINISTRATIVE.getId() + "");
        HashMap<String, String> ruleMap = new HashMap<String, String>();
        for (AuConnectionRule rule : rules) {
            if (rule.getSubPartyType() == AuPartyType.COMPANY) {
                ruleMap.put("comp", null);
            } else if (rule.getSubPartyType() == AuPartyType.DEPARTMENT) {
                ruleMap.put("dept", null);
            } else if (rule.getSubPartyType() == AuPartyType.POSITION) {
                ruleMap.put("posi", null);
            } else if (rule.getSubPartyType() == AuPartyType.EMPLOYEE) {
                ruleMap.put("empl", null);
            } else if (rule.getSubPartyType() == AuPartyType.PROXY) {
                ruleMap.put("proxy", null);
            }
        }
        model.addAttribute("relationId", request.getParameter("relationId"));
        model.addAttribute("ruleMap", ruleMap);
        return "input";
    }

    //查看，明细
    @RequestMapping(value = "/admin/relation/view.jspx")
    public String detail(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String id = request.getParameter("id");
        String relationId = request.getParameter("rid");
        if (StringUtils.isNotEmpty(id)) {
            AuParty party = null;

            if (party.getAuPartyType() == AuPartyType.COMPANY) {
                return "redirect:/admin/company/view.jspx?ids=" + id + "&rid=" + relationId;
            } else if (party.getAuPartyType() == AuPartyType.DEPARTMENT) {
                return "redirect:/admin/department/view.jspx?ids=" + id + "&rid=" + relationId;
            } else if (party.getAuPartyType() == AuPartyType.POSITION) {
                return "redirect:/admin/position/view.jspx?ids=" + id + "&rid=" + relationId;
            } else if (party.getAuPartyType() == AuPartyType.EMPLOYEE) {
                return "redirect:/admin/employee/view.jspx?ids=" + id + "&rid=" + relationId;
            }
        }
        return "success";
    }

    @RequestMapping(value = "/admin/relation/getOrgTree.jspx")
    public String getOrgTree(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        List<TreeNode> resultList = partyRelationService.getPartyRelationTree(AuPartyRelationType.ADMINISTRATIVE, null);
        model.addAttribute("data", JSON.toJSON(resultList));
        return "tree";
    }

    @RequestMapping(value = "/admin/relation/default.jspx")
    public String getDefault() {
        return "default";
    }
    
}
