package com.qslion.core.controller.au;

import com.google.common.collect.Lists;
import com.qslion.core.entity.AuRole;
import com.qslion.core.entity.AuUser;
import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.core.service.AuRoleService;
import com.qslion.core.service.AuUserService;
import com.qslion.core.service.PartyRelationService;
import com.qslion.framework.bean.*;
import com.qslion.framework.controller.BaseController;
import io.swagger.annotations.Api;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    @PostMapping(value = "/getRefUser/{roleId}")
    public Pager<EntityVo> getReferenceUser(@PathVariable Long roleId, @RequestBody Pageable pageable) {
        AuRole role = auRoleService.findById(roleId);
        Pager<AuUser> pager = auUserService.findPage(pageable);
        return pager.wrap(user -> {
            EntityVo ev = null;
            if (!role.getUsers().contains(user)) {
                ev = EntityVo.getResult(user);
            }
            return ev;
        });
    }

    /**
     * 角色管理>关联用户
     */
    @PostMapping(value = "/refUser/{roleId}")
    public Boolean setReferenceUser(@PathVariable Long roleId, @RequestBody List<Long> userIds) {
        AuRole role = auRoleService.findById(roleId);
        List<AuUser> userList = auUserService.findList(userIds.toArray(new Long[0]));
        role.getUsers().addAll(userList);
        for (AuUser auUser : userList) {
            auUser.getRoles().add(role);
        }
        return auRoleService.saveOrUpdate(role) == null;
    }

    /**
     * 授权管理>>角色授权
     */
    @PostMapping(value = "/auth")
    public Pager<AuRole> roleAuth(@RequestBody Pageable pageable) {
        return auRoleService.findPage(pageable);
    }
}