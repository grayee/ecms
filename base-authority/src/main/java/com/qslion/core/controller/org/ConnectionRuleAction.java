/**
 *
 */
package com.qslion.core.controller.org;

import com.qslion.core.entity.AuConnectionRule;
import com.qslion.core.entity.AuPartyRelationType;
import com.qslion.core.entity.AuPartyType;
import com.qslion.core.service.ConnectionRuleService;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.controller.BaseController;
import java.util.HashMap;
import java.util.Map;
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
 * 类名称：PartyTypeAction
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2011-8-17 下午02:46:48
 * 修改人：Administrator
 * 修改时间：2011-8-17 下午02:46:48
 * 修改备注：
 */
@Controller
public class ConnectionRuleAction extends BaseController<AuConnectionRule, Long> {


    @Autowired
    public ConnectionRuleService connectionRuleService;


    //列表
    @RequestMapping(value = "/admin/connectrule/index.jspx")
    public String list(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("pager") Pager<AuConnectionRule> pager) {
       // pager = this.service.findByPager(pager);
        model.addAttribute("pager", pager);
        return forward("list", false);
    }

    //增加
    @RequestMapping(value = "/admin/connectrule/save.jspx")
    public String insert(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") AuConnectionRule entity) {
  /*      AuPartyType partyType = new AuPartyType();
        partyType.setId(request.getParameter("childPartyTypeId"));
        entity.setChildPartyType(partyType);

        partyType.setId(request.getParameter("parentPartyTypeId"));
        entity.setParentPartyType(partyType);

        AuPartyRelationType auPartyRelationType = new AuPartyRelationType();
        auPartyRelationType.setId(request.getParameter("auPartyRelationType"));
        entity.setAuPartyRelationType(auPartyRelationType);

        this.connectionRuleService.save(entity);*/
        logger.info("--------------------save!!!!!------------------------");
        return forward("index", true);
    }

    //删除
    @RequestMapping(value = "/admin/connectrule/deletes.jspx")
    public String deletes(HttpServletRequest request, HttpServletResponse response, ModelMap model, String[] ids) {
       // connectionRuleService.delete(ids);
        logger.info("--------------------deletes!!!!!------------------------");
        return forward("index", true);
    }

    //更新
    @RequestMapping(value = "/admin/connectrule/update.jspx")
    public String update(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") AuConnectionRule entity) {
        connectionRuleService.update(entity);
        return forward("index", true);
    }

    //编辑，新增
    @RequestMapping(value = "/admin/connectrule/input.jspx")
    public String input(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String id = request.getParameter("ids");
        Map<String, Object> map = new HashMap<String, Object>();
        //团体类型
        map.put("partyType", AuPartyType.values());
        //团体关系类型
        map.put("partyRelationType", AuPartyRelationType.values());
        model.addAttribute("dataMap", map);

        if (!StringUtils.isNotEmpty(id)) {
            //AuConnectionRule entity = this.connectionRuleService.get(id);
            //.addAttribute("entity", entity);
        }
        return forward("input", false);
    }

    //查看，明细
    @RequestMapping(value = "/admin/connectrule/view.jspx")
    public String detail(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String id = request.getParameter("ids");
        if (!StringUtils.isNotEmpty(id)) {
           // AuConnectionRule entity = this.connectionRuleService.get(id);
            //model.addAttribute("entity", entity);
        }
        return forward("view", false);
    }

    public String forward(String viewName, boolean isRedirect) {
        String baseUrl = "";
        if (isRedirect) {
            baseUrl = "redirect:/admin/connectrule/";
            return baseUrl + viewName + ".jspx";
        } else {
            baseUrl = "authority/org/connectrule/";
            return baseUrl + viewName;
        }
    }
}
