package com.qslion.core.controller.au;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.qslion.core.entity.AuPartyRelation;
import com.qslion.core.entity.AuPermission;
import com.qslion.core.entity.AuRole;
import com.qslion.core.entity.AuUser;
import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.core.enums.AuPartyType;
import com.qslion.core.service.AuResourceService;
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
import java.util.stream.Collectors;


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
    private AuRoleService auRoleService;
    @Autowired
    private PartyRelationService partyRelationService;
    @Autowired
    private AuUserService auUserService;

    @Autowired
    private AuResourceService resourceService;

    @GetMapping(value = "/list/{typeId}")
    public Pager<AuRole> list(@PathVariable Long typeId, Pageable pageable) {
        return auRoleService.findByType(typeId, pageable);
    }

    @PostMapping
    public Long save(@Validated @RequestBody AuRole role) {
        AuRole auRole = auRoleService.insert(role);
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
        List<Long> userIds = role.getUsers().stream().map(AuUser::getId).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(userIds)) {
            pageable.getQueryFilters().add(new QueryFilter("id", QueryFilter.Operator.notIn, userIds));
        }
        Pager<AuUser> pager = auUserService.findPage(pageable);
        return pager.wrap(EntityVo::getResult);
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
     * 角色管理>删除关联用户
     */
    @DeleteMapping(value = "/refUser/{roleId}")
    public Boolean removeReferenceUser(@PathVariable Long roleId, @RequestBody List<Long> userIds) {
        AuRole role = auRoleService.findById(roleId);
        List<AuUser> userList = auUserService.findList(userIds.toArray(new Long[0]));
        role.getUsers().removeAll(userList);
        for (AuUser auUser : userList) {
            auUser.getRoles().remove(role);
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


    /**
     * 角色管理>功能授权
     */
    @PostMapping(value = "/function/{roleId}")
    public Boolean grantFuncAuth(@PathVariable Long roleId, @RequestBody List<String> checkedIds) {
        AuRole role = auRoleService.findById(roleId);
        List<Long> permissionIds = Lists.newArrayList();
        List<Long> resIds = Lists.newArrayList();
        checkedIds.forEach(id -> {
            if (Boolean.valueOf(id.split("@")[1])) {
                permissionIds.add(Long.valueOf(id.split("@")[0]));
            } else {
                Long resId = Long.valueOf(id.split("@")[0]);
                resIds.add(resId);
            }
        });
        //resourceService.findList(resIds.toArray());
        return auRoleService.grantFuncAuth(role, permissionIds);
    }


    /**
     * 角色管理>数据授权
     */
    @PostMapping(value = "/data/{roleId}")
    public Boolean grantDataAuth(@PathVariable Long roleId, @RequestBody List<Long> ids) {
        AuRole role = auRoleService.findById(roleId);
        List<AuPartyRelation> partyRelations = partyRelationService.findList(ids.toArray(new Long[0]));
        return auRoleService.grantDataAuth(role, partyRelations);
    }

    /**
     * 功能权限树
     *
     * @param roleId 角色ID
     * @return tree
     */
    @GetMapping(value = "/func/tree/{roleId}")
    public List<TreeNode> getPermResourceTree(@PathVariable Long roleId) {
        AuRole role = auRoleService.findById(roleId);
        List<AuPermission> perms = role.getPermissions().stream()
                .filter(permission -> permission.getType() == AuPermission.PermitType.FUNCTION).collect(Collectors.toList());
        return resourceService.getResourceTree(perms, true);
    }


    /**
     * 数据权限树
     *
     * @param roleId 角色ID
     * @return tree
     */
    @GetMapping(value = "/org/tree/{roleId}")
    public List<TreeNode> getPermOrgTree(@PathVariable Long roleId) {
        AuRole role = auRoleService.findById(roleId);
        return partyRelationService.getPartyRelationTree(AuPartyType.COMPANY, Sets.newHashSet(role));
    }

}