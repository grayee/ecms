package com.qslion.core.service.impl;

import com.google.common.collect.Lists;
import com.qslion.core.dao.AuPermissionRepository;
import com.qslion.core.dao.AuResourceRepository;
import com.qslion.core.entity.AuMenu;
import com.qslion.core.entity.AuPermission;
import com.qslion.core.entity.AuResource;
import com.qslion.core.service.AuResourceService;
import com.qslion.core.util.TreeTools;
import com.qslion.framework.bean.TreeNode;
import com.qslion.framework.enums.EnableStatus;
import com.qslion.framework.enums.ResultCode;
import com.qslion.framework.exception.BusinessException;
import com.qslion.framework.service.impl.GenericServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 资源Service实现
 *
 * @author Gray.Z
 * @date 2018/4/30 19:15.
 */
@Service
public class AuResourceServiceImpl extends GenericServiceImpl<AuResource, Long> implements AuResourceService {

    @Autowired
    public AuResourceRepository auResourceRepository;

    @Autowired
    private AuPermissionRepository auPermissionRepository;

    @Override
    public AuResource insert(AuResource auResource) {
        if (checkUnique(auResource)) {
            throw new BusinessException(ResultCode.DATA_ALREADY_EXISTED);
        }
        return save(auResource);
    }

    @Override
    public List<TreeNode> getResourceTree(List<AuPermission> rolePerms, boolean showPermission) {
        List<AuResource> resourceList = auResourceRepository.findByEnableStatus(EnableStatus.ENABLE);
        return getTreeNodes(rolePerms, showPermission, resourceList);
    }

    @Override
    public List<TreeNode> getAuthedResourceTree(List<AuPermission> rolePerms) {
        List<AuResource> resourceList = auResourceRepository.findByEnableStatus(EnableStatus.ENABLE);
        List<Long> resIds = rolePerms.stream().filter(perm -> perm.getType() == AuPermission.PermitType.FUNCTION)
                .map(perm -> perm.getResource().getId()).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(resIds)) {
            resourceList = TreeTools.getPathTree(resourceList, resIds);
        }
        return getTreeNodes(rolePerms, true, resourceList);
    }

    private List<TreeNode> getTreeNodes(List<AuPermission> rolePerms, boolean showPermission, List<AuResource> resourceList) {
        List<TreeNode> resultList = new ArrayList<>();
        for (AuResource resource : resourceList) {
            //获取跟节点
            if (resource.getParentId() == null || "".equals(resource.getParentId())) {
                TreeNode rootNode = new TreeNode(resource.getId().toString(), resource.getName());
                if (StringUtils.isEmpty(resource.getValue())) {
                    resourceList = resourceList.stream().filter(auResource ->
                            !resource.getId().equals(auResource.getId())).collect(Collectors.toList());

                    List<TreeNode> children = this.getChildTreeNode(resource.getId(), resourceList, showPermission, rolePerms);
                    rootNode.setChildren(children);
                }
                setPerm(rolePerms, showPermission, resource, rootNode);
                resultList.add(rootNode);
            }
        }
        return resultList;
    }

    private void setPerm(List<AuPermission> rolePerms, boolean showPermission, AuResource resource, TreeNode treeNode) {
        Set<AuPermission> allPerms = resource.getPermissions();
        if (CollectionUtils.isNotEmpty(allPerms)) {
            Set<AuPermission> perms = allPerms.stream().filter(permission -> !permission.getSystem()).collect(Collectors.toSet());
            if (showPermission) {
                List<TreeNode> children = treeNode.getChildren();
                if (CollectionUtils.isEmpty(children)) {
                    children = Lists.newArrayList();
                }
                children.addAll(perms.stream().map(perm -> {
                    TreeNode permNode = new TreeNode(perm.getId().toString(), perm.getName());
                    if (rolePerms.contains(perm)) {
                        permNode.setChecked(true);
                    }
                    permNode.addAttribute("isPerm", true);
                    return permNode;
                }).collect(Collectors.toList()));
                treeNode.setChildren(children);
                Set<AuResource> sysPermRes = allPerms.stream().filter(AuPermission::getSystem)
                        .map(AuPermission::getResource).collect(Collectors.toSet());
                if (sysPermRes.contains(resource)) {
                    treeNode.setChecked(true);
                }
            } else {
                treeNode.addAttribute("permissions", perms);
            }
        }
    }

    private List<TreeNode> getChildTreeNode(Long parentId, List<AuResource> nodeList, boolean showPermission, List<AuPermission> userPerms) {
        List<TreeNode> resultList = new ArrayList<>();
        for (AuResource resource : nodeList) {
            if (resource.getParentId() != null && parentId.equals(resource.getParentId())) {
                TreeNode leafNode = new TreeNode(resource.getId().toString(), resource.getName());
                if (StringUtils.isEmpty(resource.getValue())) {
                    nodeList = nodeList.stream().filter(auResource ->
                            !resource.getId().equals(auResource.getId())).collect(Collectors.toList());

                    List<TreeNode> children = this.getChildTreeNode(resource.getId(), nodeList, showPermission, userPerms);
                    leafNode.setChildren(children);
                }

                setPerm(userPerms, showPermission, resource, leafNode);
                resultList.add(leafNode);
            }
        }
        return resultList;
    }

    @Override
    public List<AuResource> getChildren(AuResource auResource) {
        return auResourceRepository.findAll(
                (Specification<AuResource>) (root, criteriaQuery, criteriaBuilder) ->
                        criteriaQuery.where(criteriaBuilder.equal(root.get("parentId"), auResource.getId()))
                                .getRestriction());
    }

    @Override
    public List<AuResource> getParentChildren(AuResource auResource) {
        return auResourceRepository.findAll(
                (Specification<AuResource>) (root, criteriaQuery, criteriaBuilder) ->
                        criteriaQuery.where(criteriaBuilder.equal(root.get("parentId"), auResource.getParentId()))
                                .getRestriction());
    }

    @Override
    public AuResource getParent(AuResource auResource) {
        Long parentId = auResource.getParentId();
        return auResourceRepository.findById(parentId).orElse(auResource);
    }

    @Override
    public boolean checkUnique(AuResource auResource) {
        return auResourceRepository.exists(Example.of(auResource));
    }

    @Override
    public AuResource findByMenu(AuMenu menu) {
        return auResourceRepository.findByMenu(menu);
    }

    @Override
    public boolean addPermission(Long id, AuPermission permission) {
        permission.setType(AuPermission.PermitType.FUNCTION);
        Optional<AuResource> resource = auResourceRepository.findById(id);
        resource.ifPresent(permission::setResource);
        auPermissionRepository.save(permission);
        return true;
    }

    @Override
    public boolean removePermission(List<Long> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            List<AuPermission> permissions = auPermissionRepository.findAllById(ids);
            auPermissionRepository.deleteAll(permissions);
        }
        return true;
    }

    @Override
    public boolean updatePermission(Long id, AuPermission permission) {
        Optional<AuResource> resource = auResourceRepository.findById(id);
        permission.setResource(resource.get());
        auPermissionRepository.saveAndFlush(permission);
        return true;
    }
}
