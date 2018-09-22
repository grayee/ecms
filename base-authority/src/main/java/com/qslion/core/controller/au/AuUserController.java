package com.qslion.core.controller.au;

import com.alibaba.fastjson.JSON;
import com.qslion.core.entity.AuUser;
import com.qslion.core.service.AuRoleService;
import com.qslion.core.service.AuUserService;
import com.qslion.core.service.PartyRelationService;
import com.qslion.core.util.TreeNode;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.controller.BaseController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户控制类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@RestController
@RequestMapping(value = "/au/user")
public class AuUserController extends BaseController<AuUser, Long> {

    @Autowired
    private AuUserService auUserService;
    @Autowired
    private AuRoleService roleService;
    @Autowired
    private PartyRelationService partyRelationService;

    /**
     * 列表
     */
    @GetMapping(value = "/list")
    public String list(HttpServletRequest request, HttpServletResponse response,
        @ModelAttribute("pager") Pager<AuUser> pager) {
        //pager = auUserService.findByPager(pager);

        return "";
    }

    /**
     * 增加
     */
    @PostMapping
    public String save(HttpServletRequest request, HttpServletResponse response,
        @Validated @RequestBody AuUser auUser) {
        String parentRelId = "";
       /* AuPartyRelation relation = partyRelationService.get(parentRelId);
        AuParty auParty = relation.getAuParty();
        entity.setAuParty(auParty);
        entity.setLoginId(entity.getUsername());
        auUserService.insert(entity);*/
        auUserService.save(auUser);
        return "";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/admin/user/deletes.jspx")
    public String deletes(HttpServletRequest request, HttpServletResponse response, ModelMap model, String[] ids) {
        //auUserService.delete(ids);
        return "";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/admin/user/update.jspx")
    public String update(HttpServletRequest request, HttpServletResponse response, ModelMap model,
        @ModelAttribute("entity") AuUser entity) {
        auUserService.update(entity);
        return "";
    }

    /**
     * 编辑，新增
     */
    @RequestMapping(value = "/admin/user/input.jspx")
    public String input(HttpServletRequest request, HttpServletResponse response, ModelMap model,
        @ModelAttribute("entity") AuUser entity) {
        String id = request.getParameter("ids");
        if (StringUtils.isNotEmpty(id)) {
            //entity = auUserService.get(id);
            model.addAttribute("entity", entity);
        }
        List<TreeNode> resultList = null;//partyRelationService.getPartyRelationTree(GlobalConstants.getRelTypeComp(), false);
        model.addAttribute("data", JSON.toJSON(resultList));
        return "";
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
        return "";
    }

    /**
     * 授权管理>>用户授权
     */
    @RequestMapping(value = "/admin/user/getAuthUser.jspx")
    public String getAuthUser(HttpServletRequest request, HttpServletResponse response, ModelMap model,
        @ModelAttribute("pager") Pager<AuUser> pager) {
        // pager = auUserService.findByPager(pager);
        model.addAttribute("pager", pager);
        return "";
    }

    /**
     * 授权管理>>用户授权>>用户关联角色
     */
    @RequestMapping(value = "/admin/user/setReferenceRole.jspx")
    public String setReferenceRole(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String roleIds = "";
        String userId = "";
        // AuUser user = auUserService.get(userId);
        String[] roleIdArray = roleIds.split(",");
        for (int i = 0; i < roleIdArray.length; i++) {
            // AuRole role = roleService.get(roleIdArray[i]);
            //role.getAuUserSet().add(user);
            // user.getAuRoleSet().add(role);
        }
        // auUserService.insert(user);
        return "";
    }

    /**
     * 跳转用户授权
     */
    @RequestMapping(value = "/admin/user/grantAuthDetail.jspx")
    public String grantAuthDetail(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String userId = "";
        // AuUser user = auUserService.get(userId);
        List<TreeNode> resultList = null;//partyRelationService.getPartyDetailRelationTree(user.getAuParty().getId(), GlobalConstants.getRelTypeComp());
        model.addAttribute("data", JSON.toJSON(resultList));
        // model.addAttribute("user", user);
        return "";
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