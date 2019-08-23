package com.qslion.core.controller.au;

import com.google.common.collect.Lists;
import com.qslion.core.entity.AuAuthorize;
import com.qslion.core.entity.AuRole;
import com.qslion.core.entity.AuUser;
import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.core.service.AuRoleService;
import com.qslion.core.service.AuUserService;
import com.qslion.core.service.PartyRelationService;
import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.bean.TreeNode;
import com.qslion.framework.controller.BaseController;
import io.swagger.annotations.Api;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 角色控制类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@Api(value = "角色Controller", description = "角色Controller", tags = {"角色控制器"})
@ResponseResult
@RestController
@RequestMapping(value = "/au/role")
public class AuRoleController extends BaseController<AuRole> {

    @Autowired
    public AuRoleService auRoleService;
    @Autowired
    public PartyRelationService partyRelationService;
    @Autowired
    public AuUserService auUserService;

    @GetMapping(value = "/list/{typeId}")
    public Pager<AuRole> list(@PathVariable Long typeId, Pageable pageable) {
        return auRoleService.findByType(typeId, pageable);
    }

    @PostMapping
    public Long save(@Validated @RequestBody AuRole role, @RequestParam(required = false) Long parentId) {
        AuRole auRole = auRoleService.insert(role, parentId);
        return auRole.getId();
    }

    @DeleteMapping
    public boolean delete(@RequestBody List<Long> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            return auRoleService.remove(Lists.newArrayList(ids));
        }
        return false;
    }

    /**
     * 更新
     */
    @PutMapping
    public boolean update(@RequestBody AuRole role) {
        AuRole auRole = auRoleService.update(role);
        return auRole == null;
    }

    /**
     * 查看，明细
     */
    @GetMapping(value = "/detail/{id}")
    public AuRole detail(@PathVariable Long id) {
        return auRoleService.findById(id);
    }

    /**
     * 角色关系树
     */
    @GetMapping(value = "/tree")
    public List<TreeNode> getRoleTree(@AuthenticationPrincipal AuUser user) {
        return partyRelationService.getPartyRelationTree(AuPartyRelationType.ROLE, user.getRoles());
    }

    /**
     * 角色管理>关联用户
     */
    @GetMapping(value = "/user")
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
        return "userRefList";
    }

    /**
     * 角色管理>关联用户
     */
    @PostMapping(value = "/user")
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
        return "view";
    }

    /**
     * 授权管理>>角色授权
     */
    @PostMapping(value = "/auth")
    public String getAuthUser(HttpServletRequest request, HttpServletResponse response, ModelMap model,
        @ModelAttribute("pager") Pager<AuRole> pager) {
        //pager = auRoleService.findByPager(pager);
        model.addAttribute("pager", pager);
        return "authRoleList";
    }
}