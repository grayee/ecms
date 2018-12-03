/**
 *
 */
package com.qslion.core.service.impl;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.qslion.core.dao.AuMenuRepository;
import com.qslion.core.dao.AuResourceRepository;
import com.qslion.core.dao.AuUserRepository;
import com.qslion.core.entity.AuMenu;
import com.qslion.core.entity.AuPermission;
import com.qslion.core.entity.AuRole;
import com.qslion.core.entity.AuUser;
import com.qslion.core.service.AuMenuService;
import com.qslion.core.util.TreeNode;
import com.qslion.framework.enums.ResultCode;
import com.qslion.framework.exception.BusinessException;
import com.qslion.framework.service.impl.GenericServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
        Set<AuRole> auRoleSet = auUser.getRoles();
        auUser.getUserGroups().forEach(auUserGroup -> auRoleSet.addAll(auUserGroup.getRoles()));

        Set<AuPermission> permissionSet = Sets.newHashSet();
        auRoleSet.forEach(auRole -> permissionSet.addAll(auRole.getPermissions()));

        //当前用户所拥有的权限菜单集合
        Set<AuMenu> menuSet = Sets.newHashSet();
        permissionSet.forEach(permission -> menuSet.add(permission.getResource().getMenu()));

        List<TreeNode> menuTree = new ArrayList<>();
        menuSet.stream().filter(menu -> menu.getParentId() == null || menu.getParentId().equals(menu.getId())).forEach(menu -> {
            TreeNode rootNode = getTreeNode(menuSet, menu);
            menuTree.add(rootNode);
        });
        return menuTree;
    }

    private List<TreeNode> getChildTreeNode(Long parentId, Set<AuMenu> nodeSet) {
        List<TreeNode> treeNodes = new ArrayList<>();
        nodeSet.stream().filter(menu -> menu.getParentId() != null && !menu.getId().equals(parentId) && menu.getParentId().equals(parentId))
            .forEach(menu -> {
                TreeNode leafNode = getTreeNode(nodeSet, menu);
                treeNodes.add(leafNode);
            });
        return treeNodes;
    }

    private TreeNode getTreeNode(Set<AuMenu> nodeList, AuMenu menu) {
        TreeNode treeNode = new TreeNode(String.valueOf(menu.getId()), menu.getName());
        treeNode.setIconCls(menu.getIcon());
        if (menu.isLeaf()) {
            Map<String, Object> attributeMap = Maps.newHashMap();
            attributeMap.put("url", String.format("/au/menu/detail?menuId=%s", menu.getId()));
            treeNode.setAttributes(attributeMap);
        } else {
            List<TreeNode> leafNodeList = this.getChildTreeNode(menu.getId(), nodeList);
            treeNode.setChildren(leafNodeList);
        }
        return treeNode;
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
        // TODO Auto-generated method stub
        return auMenuRepository.exists(Example.of(menu));
    }

    @Override
    public AuMenu insert(AuMenu menu) {
        if (checkUnique(menu)) {
            throw new BusinessException(ResultCode.DATA_ALREADY_EXISTED);
        }
        AuMenu parent = getParent(menu);
        if (parent.isLeaf()) {
            parent.setLeaf(false);
            update(parent);
        }
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
                delete(id);
            } else {
                //此处删除子节点，亦可抛出异常不删除
                List<AuMenu> children = getChildren(auMenu);
                delete(children);
            }
        }
        return true;
    }
}
