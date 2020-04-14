/**
 *
 */
package com.qslion.authority.core.service.impl;

import com.google.common.collect.Sets;
import com.qslion.authority.core.dao.AuMenuRepository;
import com.qslion.authority.core.dao.AuPermissionRepository;
import com.qslion.authority.core.dao.AuResourceRepository;
import com.qslion.authority.core.dao.AuUserRepository;
import com.qslion.authority.core.entity.*;
import com.qslion.authority.core.enums.MenuType;
import com.qslion.authority.core.service.AuMenuService;
import com.qslion.authority.core.util.TreeTools;
import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.bean.TreeNode;
import com.qslion.framework.bean.TreeNode.NodeState;
import com.qslion.framework.enums.EnableStatus;
import com.qslion.framework.enums.ResultCode;
import com.qslion.framework.exception.BusinessException;
import com.qslion.framework.service.impl.GenericServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 菜单Service实现
 *
 * @author Gray.Z
 * @date 2018/4/30 19:15.
 */
@Service
@Transactional(value = "transactionManager", rollbackFor = Exception.class)
public class AuMenuServiceImpl extends GenericServiceImpl<AuMenu, Long> implements AuMenuService {

    @Autowired
    private AuMenuRepository auMenuRepository;
    @Autowired
    public AuResourceRepository resourceRepository;
    @Autowired
    private AuUserRepository auUserRepository;
    @Autowired
    private AuPermissionRepository auPermissionRepository;

    @Override
    public List<AuMenu> getMenuList(String username) {
        List<AuMenu> menuList = auMenuRepository.findAll();
        return getFilteredMenu(username, menuList);
    }

    @Override
    public Pager<AuMenu> getMenuList(String username, Pageable pageable) {
        if (pageable == null) {
            pageable = new Pageable();
        }
        Pager<AuMenu> pager = findPage(pageable);
        List<AuMenu> menuList = pager.getContent();
        menuList = getFilteredMenu(username, menuList);
        menuList = menuList.stream().filter(m -> m.getParentId() != null).collect(Collectors.toList());
        return new Pager<>(menuList, menuList.size(), pageable, pager.getExtras());
    }

    private List<AuMenu> getFilteredMenu(String username, List<AuMenu> menuList) {
        AuUser auUser = auUserRepository.findUserByUsername(username);
        if (!auUser.isAdmin()) {
            Set<AuRole> userRoleSet = auUser.getRoles();
            Set<AuUserGroup> userGroupSet = auUser.getUserGroups();
            if (CollectionUtils.isNotEmpty(userGroupSet)) {
                userGroupSet.forEach(userGroup -> userRoleSet.addAll(userGroup.getRoles()));
            }

            Set<AuPermission> permissionSet = Sets.newHashSet();
            userRoleSet.forEach(userRole -> permissionSet.addAll(userRole.getPermissions()));
            //当前用户所拥有的功能权限菜单
            List<Long> menuIdList = permissionSet.stream().filter(perm -> perm.getType() == AuPermission.PermitType.FUNCTION)
                    .map(AuPermission::getResource).map(AuResource::getMenu).map(AuMenu::getId).collect(Collectors.toList());

            menuList = TreeTools.getTargetTreePath(menuList, menuIdList);
        }
        menuList.sort(Comparator.comparing(AuMenu::getOrderCode));
        return menuList;
    }

    @Override
    public List<AuMenu> getChildren(AuMenu menu) {
        return auMenuRepository.findAll(
                (Specification<AuMenu>) (root, criteriaQuery, criteriaBuilder) ->
                        criteriaQuery.where(criteriaBuilder.equal(root.get("parentId"), menu.getId()))
                                .getRestriction());
    }

    @Override
    public List<AuMenu> getParentChildren(AuMenu menu) {
        return auMenuRepository.findAll(
                (Specification<AuMenu>) (root, criteriaQuery, criteriaBuilder) ->
                        criteriaQuery.where(criteriaBuilder.equal(root.get("parentId"), menu.getParentId()))
                                .getRestriction());
    }

    @Override
    public AuMenu getParent(AuMenu menu) {
        Long parentId = menu.getParentId();
        return auMenuRepository.findById(parentId).orElse(menu);
    }

    @Override
    public boolean checkUnique(AuMenu menu) {
        return auMenuRepository.exists(Example.of(menu));
    }

    @Override
    public AuMenu insert(AuMenu menu) {
        if (menu.getParentId() == null) {
            //根节点
            menu.setParentId(1L);
            menu.setLevel(1);
        }
        if (checkUnique(menu)) {
            throw new BusinessException(ResultCode.DATA_ALREADY_EXISTED);
        }
        AuPermission perm = new AuPermission();
        Set<AuPermission> perms = Sets.newHashSet();

        menu.setLeaf(true);
        menu.setStatus((short) NodeState.CLOSED.ordinal());
        menu.setEnableStatus(EnableStatus.ENABLE);
        AuResource resource = menu.buildResource();

        AuMenu parent = getParent(menu);
        if (parent != null) {
            if (parent.getType() == MenuType.PAGE_BUTTON) {
                throw new BusinessException(ResultCode.SPECIFIED_QUESTIONED_USER_NOT_EXIST);
            } else if (menu.getType() == MenuType.PAGE_BUTTON && parent.getType() != MenuType.FUNCTION_MENU) {
                throw new BusinessException(ResultCode.SPECIFIED_QUESTIONED_USER_NOT_EXIST);
            } else {
                if (parent.getLeaf()) {
                    parent.setLeaf(false);
                    if (menu.getType() == MenuType.FUNCTION_MENU && parent.getType() != MenuType.CATALOG) {
                        parent.setType(MenuType.CATALOG);
                        parent.setUrl(StringUtils.EMPTY);
                        parent.setAuthCode(StringUtils.EMPTY);
                    }
                }
                if (menu.getType() == MenuType.PAGE_BUTTON) {
                    perms.addAll(parent.getResource().getPermissions());
                    //将按钮注册为权限
                    perm.setName(menu.getName());
                    perm.setValue(menu.getAuthCode());
                    perm.setDescription(menu.getRemark());
                    perm.setEnableStatus(EnableStatus.ENABLE);
                    auPermissionRepository.save(perm);
                    perms.add(perm);
                    parent.getResource().setPermissions(perms);
                }
                update(parent);

                if (parent.getLevel() != null) {
                    menu.setLevel(parent.getLevel() + 1);
                } else {
                    Integer level = 1;
                    AuMenu temMenu = menu;
                    while (temMenu.getParentId() != null && level < 100) {
                        temMenu = getParent(temMenu);
                        level++;
                    }
                    menu.setLevel(level);
                }
            }
        }

        if (menu.getType() == MenuType.FUNCTION_MENU) {
            //默认权限
            perm.setName("默认权限");
            perm.setValue(StringUtils.defaultString(menu.getAuthCode(), "common:view"));
            perm.setDescription("系统默认权限");
            perm.setSystem(true);
            perm.setEnableStatus(EnableStatus.ENABLE);
            auPermissionRepository.save(perm);
            perms.add(perm);
            resource.setPermissions(perms);
            menu.setResource(resource);
        }
        return save(menu);
    }

    @Override
    public boolean remove(List<Long> ids) {
        for (Long id : ids) {
            AuMenu auMenu = findById(id);
            if (auMenu.getLeaf()) {
                if (getParentChildren(auMenu).size() < 0) {
                    //父节点没有子节点则更新isLeaf状态
                    AuMenu parent = getParent(auMenu);
                    parent.setLeaf(true);
                    update(parent);
                }
            } else {
                //此处删除子节点，亦可抛出异常不删除
                List<AuMenu> children = getChildren(auMenu);
                delete(children);
            }
            delete(id);
        }
        return true;
    }
}
