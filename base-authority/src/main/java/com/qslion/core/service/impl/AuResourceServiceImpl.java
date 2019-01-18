package com.qslion.core.service.impl;

import com.qslion.core.dao.AuResourceRepository;
import com.qslion.core.entity.AuResource;
import com.qslion.core.entity.AuUser;
import com.qslion.core.service.AuResourceService;
import com.qslion.framework.bean.TreeNode;
import com.qslion.framework.enums.ResultCode;
import com.qslion.framework.exception.BusinessException;
import com.qslion.framework.service.impl.GenericServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
    public List<TreeNode> getResourceTree(AuUser user, String status, String path) {
        List<TreeNode> resultList = new ArrayList<>();
        //所有菜单功能树,后期改成根据登录用户的权限查询
        List<AuResource> funcTreeList = null;//this.resourceDao.findByEnableStatus("1");
        //转换成ztree控件需要的格式
        for (AuResource funcTree : funcTreeList) {
            //获取跟节点
            if (funcTree.getParentId() == null || "".equals(funcTree.getParentId())) {
/*
                TreeNode rootNode = new TreeNode();
                rootNode.setId(funcTree.getId());
                rootNode.setText(funcTree.getName());

                //存在子节点则添加子节点到父节点中，并设置标记
                if (funcTree.getIsLeaf().equals("0")) {
                */
/*    if (status != null && status.equals("menu")) {
                        rootNode.setName(funcTree.getName());
                        rootNode.setUrl("admin/functree/getMenuDetail.jspx?menuId=" + funcTree.getId());
                        rootNode.setTarget("menuRightFrame");
                    } else if (status != null && status.equals("auth")) {
                        rootNode.setName(funcTree.getName());
                    }*//*

                    List<TreeNode> leafNodeList = this.getChildTreeNode(funcTree.getId(), funcTreeList, status, path);
                    rootNode.setChildren(leafNodeList);
                } else if (funcTree.getIsLeaf().equals("1")) {
                   */
/* if (status != null && status.equals("menu")) {
                        rootNode.setName(funcTree.getName());
                        rootNode.setUrl("admin/functree/getMenuDetail.jspx?menuId=" + funcTree.getId());
                        rootNode.setTarget("menuRightFrame");
                    } else if (status != null && status.equals("auth")) {
                        rootNode.setName(funcTree.getName());
                    } else {
                        rootNode.setName(funcTree.getName());
                        rootNode.setUrl(path + funcTree.getValue());
                        rootNode.setTarget("mainFrame");
                    }*//*

                }
                //rootNode.setHasChildren(false);
                //将根节点及子节点数据添加到结果集中
                resultList.add(rootNode);
*/
            }
        }
        return resultList;
    }

    private List<TreeNode> getChildTreeNode(String parentId, List<AuResource> nodeList, String status, String path) {
        List<TreeNode> resultList = new ArrayList<TreeNode>();
        //for (AuResource funcTree : nodeList)
            /*{
            TreeNode leafNode = null;
            if (funcTree.getParentId() != null && funcTree.getParentId().equals(parentId)) {
                leafNode = new TreeNode();
                leafNode.setId(funcTree.getId());
                leafNode.setText(funcTree.getName());
                //存在子节点则添加子节点到父节点中，并设置标记
                if (funcTree.getIsLeaf().equals("0")) {
                   *//* if (status != null && status.equals("menu")) {
                        leafNode.setName(funcTree.getName());
                        leafNode.setUrl("admin/functree/getMenuDetail.jspx?menuId=" + funcTree.getId());
                        leafNode.setTarget("menuRightFrame");
                    } else if (status != null && status.equals("auth")) {
                        leafNode.setOpen(true);
                    }*//*
                    List<TreeNode> leafNodeList = this.getChildTreeNode(funcTree.getId(), nodeList, status, path);
                    leafNode.setChildren(leafNodeList);
                } else if (funcTree.getIsLeaf().equals("1")) {
                    //leafNode.setClasses("file");
                *//*    if (status != null && status.equals("menu")) {
                        leafNode.setName(funcTree.getName());
                        leafNode.setUrl("admin/functree/getMenuDetail.jspx?menuId=" + funcTree.getId());
                        leafNode.setTarget("menuRightFrame");
                    } else if (status != null && status.equals("auth")) {
                        leafNode.setOpen(true);
                        leafNode.setName(funcTree.getName());
                    } else {
                        leafNode.setName(funcTree.getName());
                        leafNode.setUrl(path + funcTree.getValue());
                        leafNode.setTarget("mainFrame");
                    }*//*
                }
                resultList.add(leafNode);
            }
        }*/
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
}
