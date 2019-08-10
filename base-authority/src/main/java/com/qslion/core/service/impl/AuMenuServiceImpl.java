/**
 *
 */
package com.qslion.core.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.qslion.core.dao.AuMenuRepository;
import com.qslion.core.dao.AuResourceRepository;
import com.qslion.core.dao.AuUserRepository;
import com.qslion.core.entity.AuMenu;
import com.qslion.core.entity.AuPermission;
import com.qslion.core.entity.AuRole;
import com.qslion.core.entity.AuUser;
import com.qslion.core.enums.MenuType;
import com.qslion.core.service.AuMenuService;
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

import java.util.*;
import java.util.stream.Collectors;

/**
 * 菜单Service实现
 *
 * @author Gray.Z
 * @date 2018/4/30 19:15.
 */
@Service
public class AuMenuServiceImpl extends GenericServiceImpl<AuMenu, Long> implements AuMenuService {

    @Autowired
    private AuMenuRepository auMenuRepository;
    @Autowired
    public AuResourceRepository resourceRepository;
    @Autowired
    private AuUserRepository auUserRepository;

    @Override
    public List<TreeNode> getMenuTree(String username) {
        AuUser auUser = auUserRepository.findUserByUsername(username);
        //当前用户所拥有的权限菜单集合
        List<AuMenu> menuList = Lists.newArrayList();
        if (auUser.isAdmin()) {
            menuList.addAll(auMenuRepository.findAll());
        } else {
            Set<AuRole> auRoleSet = auUser.getRoles();
            auUser.getUserGroups().forEach(auUserGroup -> auRoleSet.addAll(auUserGroup.getRoles()));
            Set<AuPermission> permissionSet = Sets.newHashSet();
            auRoleSet.forEach(auRole -> permissionSet.addAll(auRole.getPermissions()));
            permissionSet.forEach(permission -> menuList.add(permission.getResource().getMenu()));
        }

        menuList.sort(Comparator.comparing(AuMenu::getOrderNo));
        return menuList.stream().filter(menu -> menu.getParentId() == null || menu.getParentId().equals(menu.getId()))
                .map(menu -> getTreeNode(menuList, menu)).collect(Collectors.toList());
    }

    private TreeNode getTreeNode(List<AuMenu> nodeList, AuMenu menu) {
        TreeNode treeNode = new TreeNode(String.valueOf(menu.getId()), menu.getName());
        treeNode.setIconCls(menu.getIcon());
        if (menu.isLeaf() || StringUtils.isNotEmpty(menu.getUrl())) {
            treeNode.setPath(menu.getUrl());
            treeNode.setState(NodeState.OPEN);
        } else {
            nodeList = nodeList.stream().filter(auMenu -> !auMenu.getId().equals(menu.getId()))
                    .collect(Collectors.toList());
            List<TreeNode> leafNodeList = this.getChildTreeNode(menu.getId(), nodeList);
            treeNode.setChildren(leafNodeList);
        }

        Map<String, Object> attributeMap = getAttrMap(menu);

        List<TreeNode> pageBtnList = nodeList.stream().filter(auMenu -> auMenu.getType() == MenuType.PAGE_BUTTON
                && Objects.equals(auMenu.getParentId(), menu.getId())).map(auMenu -> {
            TreeNode btnNode = new TreeNode(String.valueOf(auMenu.getId()), auMenu.getName());
            btnNode.setPath(auMenu.getUrl());
            btnNode.setState(NodeState.OPEN);
            btnNode.setAttributes(getAttrMap(auMenu));
            return btnNode;
        }).collect(Collectors.toList());

        if (CollectionUtils.isNotEmpty(pageBtnList)) {
            attributeMap.put("pageBtn", pageBtnList);
        }

        treeNode.setAttributes(attributeMap);
        return treeNode;
    }

    private Map<String, Object> getAttrMap(AuMenu menu) {
        Map<String, Object> attributeMap = Maps.newHashMap();
        attributeMap.put("modifyDate", menu.getModifyDate());
        attributeMap.put("menuType", menu.getType().ordinal());
        attributeMap.put("orderNo", menu.getOrderNo());
        attributeMap.put("component", menu.getComponent());
        return attributeMap;
    }

    private List<TreeNode> getChildTreeNode(Long parentId, List<AuMenu> nodeList) {
        return nodeList.stream().filter(
                menu -> menu.getParentId() != null && !menu.getId().equals(parentId) && menu.getParentId().equals(parentId))
                .map(menu -> getTreeNode(nodeList, menu)).collect(Collectors.toList());
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
        }
        if (checkUnique(menu)) {
            throw new BusinessException(ResultCode.DATA_ALREADY_EXISTED);
        }
        AuMenu parent = getParent(menu);
        if (parent != null && parent.isLeaf()) {
            if (parent.getType() == MenuType.PAGE_BUTTON) {
                throw new BusinessException(ResultCode.SPECIFIED_QUESTIONED_USER_NOT_EXIST);
            } else if (menu.getType() == MenuType.PAGE_BUTTON && parent.getType() != MenuType.FUNCTION_MENU) {
                throw new BusinessException(ResultCode.SPECIFIED_QUESTIONED_USER_NOT_EXIST);
            } else {
                parent.setLeaf(false);
                parent.setType(MenuType.CATALOG);
                parent.setUrl(StringUtils.EMPTY);
                update(parent);
                menu.setLevel((short) (parent.getLevel() + 1));
            }
        }

        menu.setLeaf(true);
        menu.setStatus((short) NodeState.CLOSED.ordinal());
        menu.setEnableStatus(EnableStatus.ENABLE);
        menu.setResource(menu.buildResource());
        return save(menu);
    }

    @Override
    public boolean remove(List<Long> ids) {
        for (Long id : ids) {
            AuMenu auMenu = findById(id);
            if (auMenu.isLeaf()) {
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
