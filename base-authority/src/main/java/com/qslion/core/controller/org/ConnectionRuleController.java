/**
 *
 */
package com.qslion.core.controller.org;

import com.qslion.core.entity.AuConnectionRule;
import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.core.enums.AuPartyType;
import com.qslion.core.service.ConnectionRuleService;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.controller.BaseController;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 连接规则控制类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@RestController
@RequestMapping(value = "/org/connection/rule")
public class ConnectionRuleController extends BaseController<AuConnectionRule, Long> {

    @Autowired
    public ConnectionRuleService connectionRuleService;

    //列表
    @RequestMapping(value = "/admin/connectrule/index.jspx")
    public String list(HttpServletRequest request, HttpServletResponse response, ModelMap model,
        @ModelAttribute("pager") Pager<AuConnectionRule> pager) {
        // pager = this.service.findByPager(pager);
        model.addAttribute("pager", pager);
        return "";
    }

    //增加
    @RequestMapping(value = "/admin/connectrule/save.jspx")
    public String insert(HttpServletRequest request, HttpServletResponse response, ModelMap model,
        @ModelAttribute("entity") AuConnectionRule entity) {
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
        return "";
    }

    //删除
    @RequestMapping(value = "/admin/connectrule/deletes.jspx")
    public String deletes(HttpServletRequest request, HttpServletResponse response, ModelMap model, String[] ids) {
        // connectionRuleService.delete(ids);
        logger.info("--------------------deletes!!!!!------------------------");
        return "";
    }

    //更新
    @RequestMapping(value = "/admin/connectrule/update.jspx")
    public String update(HttpServletRequest request, HttpServletResponse response, ModelMap model,
        @ModelAttribute("entity") AuConnectionRule entity) {
        connectionRuleService.update(entity);
        return "";
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
        return "";
    }

    //查看，明细
    @RequestMapping(value = "/admin/connectrule/view.jspx")
    public String detail(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String id = request.getParameter("ids");
        if (!StringUtils.isNotEmpty(id)) {
            // AuConnectionRule entity = this.connectionRuleService.get(id);
            //model.addAttribute("entity", entity);
        }
        return "";
    }

}
