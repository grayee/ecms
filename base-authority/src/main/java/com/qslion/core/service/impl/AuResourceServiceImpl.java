package com.qslion.core.service.impl;

import com.qslion.core.dao.AuResourceRepository;
import com.qslion.core.entity.AuMenu;
import com.qslion.core.entity.AuResource;
import com.qslion.core.service.AuResourceService;
import com.qslion.framework.bean.TreeNode;
import com.qslion.framework.enums.EnableStatus;
import com.qslion.framework.enums.ResultCode;
import com.qslion.framework.exception.BusinessException;
import com.qslion.framework.service.impl.GenericServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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


    @Override
    public AuResource insert(AuResource auResource) {
        if (checkUnique(auResource)) {
            throw new BusinessException(ResultCode.DATA_ALREADY_EXISTED);
        }
        return save(auResource);
    }

    @Override
    public List<TreeNode> getResourceTree() {
        List<TreeNode> resultList = new ArrayList<>();
        List<AuResource> resourceList = auResourceRepository.findByEnableStatus(EnableStatus.ENABLE);
        for (AuResource resource : resourceList) {
            //获取跟节点
            if (resource.getParentId() == null || "".equals(resource.getParentId())) {
                TreeNode rootNode = new TreeNode(resource.getId().toString(), resource.getName());
                if (StringUtils.isEmpty(resource.getValue())) {
                    resourceList = resourceList.stream().filter(auResource ->
                            !resource.getId().equals(auResource.getId())).collect(Collectors.toList());

                    List<TreeNode> children = this.getChildTreeNode(resource.getId(), resourceList);
                    rootNode.setChildren(children);
                }
                resultList.add(rootNode);
            }
        }
        return resultList;
    }

    private List<TreeNode> getChildTreeNode(Long parentId, List<AuResource> nodeList) {
        List<TreeNode> resultList = new ArrayList<>();
        for (AuResource resource : nodeList) {
            if (resource.getParentId() != null && parentId.equals(resource.getParentId())) {
                TreeNode leafNode = new TreeNode(resource.getId().toString(), resource.getName());
                if (StringUtils.isEmpty(resource.getValue())) {
                    nodeList = nodeList.stream().filter(auResource ->
                            !resource.getId().equals(auResource.getId())).collect(Collectors.toList());

                    List<TreeNode> children = this.getChildTreeNode(resource.getId(), nodeList);
                    leafNode.setChildren(children);
                }
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
}
