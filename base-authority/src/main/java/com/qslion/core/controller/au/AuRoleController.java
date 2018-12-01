package com.qslion.core.controller.au;

import com.alibaba.fastjson.JSON;
import com.qslion.core.entity.AuAuthorize;
import com.qslion.core.entity.AuRole;
import com.qslion.core.entity.AuUser;
import com.qslion.core.service.AuRoleService;
import com.qslion.core.service.AuUserService;
import com.qslion.core.service.PartyRelationService;
import com.qslion.core.util.TreeNode;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.controller.BaseController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
 * 角色控制类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */

@Controller
public class AuRoleController extends BaseController<AuRole> {

    @Autowired
    public AuRoleService auRoleService;
    @Autowired
    public PartyRelationService partyRelationService;
    @Autowired
    public AuUserService auUserService;


    /**
     * 管理主页面
     */
    @RequestMapping(value = "/admin/role/manage.jspx")
    public String getManage() {
        return this.forward("manage", false);
    }

    /**
     * 默认页面
     */
    @RequestMapping(value = "/admin/role/default.jspx")
    public String getDefault() {
        return this.forward("default", false);
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/admin/role/index.jspx")
    public String list(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("pager") Pager<AuRole> pager) {

        model.addAttribute("pager", pager);
        return forward("list", false);
    }

    /**
     * 增加
     */
    @RequestMapping(value = "/admin/role/save.jspx")
    public String insert(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") AuRole entity) {
        String parentCode = "";
        boolean isRoot = Boolean.valueOf(request.getParameter("isRoot"));
        if (isRoot) {
            auRoleService.insertRoot(entity);
        } else {
            auRoleService.insert(entity, parentCode);
        }
        return forward("manage", true);
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/admin/role/deletes.jspx")
    public String deletes(HttpServletRequest request, HttpServletResponse response, ModelMap model, String id) {

        return forward("index", true);
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/admin/role/update.jspx")
    public String update(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") AuRole entity) {
        auRoleService.update(entity);
        return forward("manage", true);
    }

    /**
     * 编辑，新增
     */
    @RequestMapping(value = "/admin/role/input.jspx")
    public String input(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") AuRole entity) {
        String id = request.getParameter("id");
        String parentRelId = request.getParameter("parentRelId");
        if (StringUtils.isNotEmpty(id)) {

            model.addAttribute("entity", entity);
        }
        model.addAttribute("parentRelId", parentRelId);
        return forward("input", false);
    }

    /**
     * 查看，明细
     */
    @RequestMapping(value = "/admin/role/view.jspx")
    public String detail(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String id = request.getParameter("id");
        if (StringUtils.isNotEmpty(id)) {
            AuRole entity = null;
            model.addAttribute("entity", entity);
        }
        model.addAttribute("rid", request.getParameter("rid"));
        return forward("view", false);
    }

    /**
     * 角色关系树
     */
    @RequestMapping(value = "/admin/role/getRoleTree.jspx")
    public String getRoleTree(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String userId = "";
        List<TreeNode> resultList = new ArrayList<TreeNode>();
        if (StringUtils.isNotEmpty(userId)) {
            Map<String, Object> map = new HashMap<String, Object>();
            //map.put("user", auUserService.get(userId));
           // resultList = partyRelationService.getPartyRelationTree(GlobalConstants.getRelTypeRole(), false, map);
        } else {
            //resultList = partyRelationService.getPartyRelationTree(GlobalConstants.getRelTypeRole(), true);
        }

        model.addAttribute("data", JSON.toJSON(resultList));
        return forward("tree", false);
    }

    /**
     * 角色管理>关联用户
     */
    @RequestMapping(value = "/admin/role/getReferenceUser.jspx")
    public String getReferenceUser(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        Pager<AuUser> pager = new Pager<AuUser>();
        //Pager<AuUser> allUser = auUserService.findByPager(pager);
        String roleId = "";
       // AuRole role = auRoleService.get(roleId);
        //Assert.notNull(role.getAuUserSet(), "role userset must be not null!");
        //Set<AuUser> refUserSet = role.getAuUserSet();
       /* for (AuUser user : allUser.getList()) {
            Iterator<AuUser> iterator = refUserSet.iterator();
            while (iterator.hasNext()) {
                AuUser refUser = iterator.next();
                if (user.getId().equals(refUser.getId())) {
                    //user.setIsReference("1");
                }
            }
        }
        model.addAttribute("pager", allUser);*/
        return forward("userRefList", false);
    }

    /**
     * 角色管理>关联用户
     */
    @RequestMapping(value = "/admin/role/setReferenceUser.jspx")
    public String setReferenceUser(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String roleId = "";
        String userIds = "";
        String rid = request.getParameter("rid");
        //AuRole role = auRoleService.get(roleId);
        String[] userIdArray = userIds.split(",");
        for (int i = 0; i < userIdArray.length; i++) {
           // AuUser user = auUserService.get(userIdArray[i]);
            //role.getAuUserSet().add(user);
            //user.getAuRoleSet().add(role);
            AuAuthorize authorize = new AuAuthorize();
            //authorize.setAuParty(user.getAuParty());
            ////authorize.setPartyType(user.getAuParty().getAuPartyType().getKeyword());
            authorize.setResourceType("role");
            //authorize.setResourceCode(user.getId());
            authorize.setAuthorizeStatus("1");
            //authorize.setAuResource(AuResource)
        }
        //auRoleService.insert(role);
        return forward("view", true) + "?id=" + roleId + "&rid=" + rid;
    }

    /**
     * 授权管理>>角色授权
     */
    @RequestMapping(value = "/admin/role/getAuthRole.jspx")
    public String getAuthUser(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("pager") Pager<AuRole> pager) {
        //pager = auRoleService.findByPager(pager);
        model.addAttribute("pager", pager);
        return forward("authRoleList", false);
    }

    public String forward(String viewName, boolean isRedirect) {
        String targetUrl = "";
        if (isRedirect) {
            targetUrl = "redirect:/admin/role/" + viewName + ".jspx";
            return targetUrl;
        } else {
            targetUrl = "authority/au/role/" + viewName;
            logger.info("view page:" + targetUrl + ".jsp");
            return targetUrl;
        }
    }

}