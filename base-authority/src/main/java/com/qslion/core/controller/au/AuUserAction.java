package com.qslion.core.controller.au;

import com.alibaba.fastjson.JSON;
import com.qslion.core.entity.AuUser;
import com.qslion.core.service.AuRoleService;
import com.qslion.core.service.AuUserService;
import com.qslion.core.service.PartyRelationService;
import com.qslion.core.util.TreeNode;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.controller.BaseController;
import com.qslion.framework.util.ParameterUtil;
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
 * @descrip: ecms 代码自动生成
 * @copyright: Copyright © 2013-2020 ecms, All Rights Reserved
 * @author: zhangrg
 * @link: <a href=http://www.ecms.com>北京青胜蓝科技股份有限公司</a>
 * @create_date: 2013-02-22 23:00:27
 * @update_date: 2013-02-22 23:00:27
 */

@Controller
public class AuUserAction extends BaseController<AuUser, Long> {

    @Autowired
    public AuUserService auUserService;
    @Autowired
    public AuRoleService roleService;
    @Autowired
    public PartyRelationService partyRelationService;


    /**
     * 列表
     */
    @RequestMapping(value = "/admin/user/index.jspx")
    public String list(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("pager") Pager<AuUser> pager) {
        //pager = auUserService.findByPager(pager);
        model.addAttribute("pager", pager);
        return forward("list", false);
    }

    /**
     * 增加
     */
    @RequestMapping(value = "/admin/user/save.jspx")
    public String insert(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") AuUser entity) {
        String parentRelId = ParameterUtil.getParameter(request, "parentRelId");
       /* AuPartyRelation relation = partyRelationService.get(parentRelId);
        AuParty auParty = relation.getAuParty();
        entity.setAuParty(auParty);
        entity.setLoginId(entity.getUsername());
        auUserService.insert(entity);*/
        return forward("index", true);
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/admin/user/deletes.jspx")
    public String deletes(HttpServletRequest request, HttpServletResponse response, ModelMap model, String[] ids) {
        //auUserService.delete(ids);
        return forward("index", true);
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/admin/user/update.jspx")
    public String update(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") AuUser entity) {
        auUserService.update(entity);
        return forward("index", true);
    }

    /**
     * 编辑，新增
     */
    @RequestMapping(value = "/admin/user/input.jspx")
    public String input(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") AuUser entity) {
        String id = request.getParameter("ids");
        if (StringUtils.isNotEmpty(id)) {
            //entity = auUserService.get(id);
            model.addAttribute("entity", entity);
        }
        List<TreeNode> resultList = null;//partyRelationService.getPartyRelationTree(GlobalConstants.getRelTypeComp(), false);
        model.addAttribute("data", JSON.toJSON(resultList));
        return forward("input", false);
    }

    /**
     * 查看，明细
     */
    @RequestMapping(value = "/admin/user/view.jspx")
    public String detail(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String id = request.getParameter("ids");
        if (StringUtils.isNotEmpty(id)) {
            //AuUser entity = auUserService.get(id);
            //model.addAttribute("entity", entity);
        }
        return forward("view", false);
    }

    /**
     * 授权管理>>用户授权
     */
    @RequestMapping(value = "/admin/user/getAuthUser.jspx")
    public String getAuthUser(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("pager") Pager<AuUser> pager) {
       // pager = auUserService.findByPager(pager);
        model.addAttribute("pager", pager);
        return forward("authUserList", false);
    }

    /**
     * 授权管理>>用户授权>>用户关联角色
     */
    @RequestMapping(value = "/admin/user/setReferenceRole.jspx")
    public String setReferenceRole(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String roleIds = ParameterUtil.getParameter(request, "roleIds");
        String userId = ParameterUtil.getParameter(request, "userId");
       // AuUser user = auUserService.get(userId);
        String[] roleIdArray = roleIds.split(",");
        for (int i = 0; i < roleIdArray.length; i++) {
           // AuRole role = roleService.get(roleIdArray[i]);
            //role.getAuUserSet().add(user);
           // user.getAuRoleSet().add(role);
        }
       // auUserService.insert(user);
        return forward("getAuthUser", true);
    }

    /**
     * 跳转用户授权
     */
    @RequestMapping(value = "/admin/user/grantAuthDetail.jspx")
    public String grantAuthDetail(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String userId = ParameterUtil.getParameter(request, "userId");
       // AuUser user = auUserService.get(userId);
        List<TreeNode> resultList = null;//partyRelationService.getPartyDetailRelationTree(user.getAuParty().getId(), GlobalConstants.getRelTypeComp());
        model.addAttribute("data", JSON.toJSON(resultList));
       // model.addAttribute("user", user);
        return forward("grantAuthDetail", false);
    }

    public String forward(String viewName, boolean isRedirect) {
        String baseUrl = "";
        if (isRedirect) {
            baseUrl = "redirect:/admin/user/";
            return baseUrl + viewName + ".jspx";
        } else {
            baseUrl = "authority/au/user/";
            return baseUrl + viewName;
        }
    }

/*    SELECT parent.*
    FROM au_partyrelation AS node,
    au_partyrelation AS parent
    WHERE node.lft BETWEEN parent.lft AND parent.rgt
    AND node.name='test001'
    ORDER BY parent.lft;


    SELECT node.*
    FROM au_partyrelation AS node,
    au_partyrelation AS parent
    WHERE node.lft BETWEEN parent.lft AND parent.rgt
    AND parent.name='青胜蓝科技股份有限公司'
    ORDER BY node.lft;
*/
}