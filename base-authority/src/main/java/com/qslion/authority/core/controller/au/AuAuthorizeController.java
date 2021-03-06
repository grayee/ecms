package com.qslion.authority.core.controller.au;

import com.qslion.authority.core.entity.AuAuthorize;
import com.qslion.authority.core.entity.AuMenu;
import com.qslion.authority.core.service.AuMenuService;
import com.qslion.authority.core.service.AuResourceService;
import com.qslion.authority.core.service.AuthorizeService;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;


/**
 * 授权控制类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@Controller
public class AuAuthorizeController extends BaseController<AuAuthorize> {


    @Autowired
    public AuthorizeService authorizeService;
    @Autowired
    public AuResourceService resourceService;
    @Autowired
    public AuMenuService auMenuService;

    /**
     * 列表
     */
    @RequestMapping(value = "/admin/authorize/index.jspx")
    public String list(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("pager") Pager<AuAuthorize> pager) {
        model.addAttribute("pager", pager);
        return forward("list", false);
    }

    /**
     * 增加
     */
    @RequestMapping(value = "/admin/authorize/save.jspx")
    public String insert(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") AuAuthorize entity) {
        return forward("index", true);
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/admin/authorize/deletes.jspx")
    public String deletes(HttpServletRequest request, HttpServletResponse response, ModelMap model, String[] ids) {
        return forward("index", true);
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/admin/authorize/update.jspx")
    public String update(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") AuAuthorize entity) {
        authorizeService.update(entity);
        return forward("index", true);
    }

    /**
     * 编辑，新增
     */
    @RequestMapping(value = "/admin/authorize/input.jspx")
    public String input(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") AuAuthorize entity) {
        String id = request.getParameter("ids");
        if (StringUtils.isNotEmpty(id)) {

        }
        model.addAttribute("entity", entity);
        return forward("input", false);
    }

    /**
     * 查看，明细
     */
    @RequestMapping(value = "/admin/authorize/view.jspx")
    public String detail(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String id = request.getParameter("ids");
        if (StringUtils.isNotEmpty(id)) {
            AuAuthorize entity = null;
            model.addAttribute("entity", entity);
        }
        return forward("view", false);
    }

    /**
     * 授权
     */
    @RequestMapping(value = "/admin/authorize/grantAuth.jspx")
    public void grantedAuthorities(HttpServletRequest request, HttpServletResponse response) {
        String partyId = "";
        //AuParty auParty = null;//partyService.get(partyId);
        String funcIds = "";
        String[] funcIdArray = funcIds.split(",");
        //团体自身拥有的权限
        Set<AuAuthorize> partyAuths = null; //auParty.getAuAuthorizes();//auAuthorizeService.getAuthorizeByPartyId(partyId);
        for (int i = 0; i < funcIdArray.length; i++) {
            AuMenu functree = null;//auMenuService.get(funcIdArray[i]);
            for (AuAuthorize auth : partyAuths) {
                AuAuthorize authorize = new AuAuthorize();
                // authorize.setAuResource(functree.getAuResource());
                //authorize.setAuParty(auParty);
                //authorize.setOrgType(auParty.getAuPartyType().getId());
                // auParty.getAuAuthorizes().add(authorize);
            }
        }
        //partyService.insert(auParty);
        System.out.println("ddd>" + partyId + "===>" + funcIds);
    }

    public String forward(String viewName, boolean isRedirect) {
        String baseUrl = "";
        if (isRedirect) {
            baseUrl = "redirect:/admin/authorize/";
            return baseUrl + viewName + ".jspx";
        } else {
            baseUrl = "/au/authorize/";
            return baseUrl + viewName;
        }
    }

}