/**
 *
 */
package com.qslion.core.service.impl;

import com.google.common.collect.Maps;
import com.qslion.core.dao.AuMenuRepository;
import com.qslion.core.dao.AuResourceRepository;
import com.qslion.core.entity.AuMenu;
import com.qslion.core.entity.AuParty;
import com.qslion.core.service.AuMenuService;
import com.qslion.core.util.TreeNode;
import com.qslion.framework.enums.ResultCode;
import com.qslion.framework.exception.BusinessException;
import com.qslion.framework.service.impl.GenericServiceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 修改备注：
 */
@Service
public class AuMenuServiceImpl extends GenericServiceImpl<AuMenu, Long> implements AuMenuService {

    @Autowired
    private AuMenuRepository auMenuRepository;
    @Autowired
    public AuResourceRepository resourceRepository;


    public List<TreeNode> getFuncMenuTree(AuParty visitor, String path) {
        //System.out.println(visitor.getName()+"====>>>>>"+visitor.getAuPartyType().getId()+"===>"+ GlobalConstants.getPartyTypeEmpl());

        //visitor.getAuAuthorize().getAuResources();
        List<TreeNode> resultList = new ArrayList<TreeNode>();
        //所有菜单功能树,后期改成根据登录用户的权限查询
        List<AuMenu> funcMenus = null;
        //转换成ztree控件需要的格式
        for (AuMenu funcTree : funcMenus) {
            //获取跟节点
            if (funcTree.getParentId().equals(funcTree.getId())) {
                //TreeNode rootNode = new TreeNode(funcTree.getId(), funcTree.getName());
                //rootNode.setIconCls(funcTree.getIconClass());

                //存在子节点则添加子节点到父节点中，并设置标记
                if (!funcTree.isLeaf()) {
                    // List<TreeNode> leafNodeList = this.getChildTreeNode(funcTree.getId(), funcMenus, path);
                    // rootNode.setChildren(leafNodeList);
                } else if (funcTree.isLeaf()) {
                    Map<String, Object> attributeMap = Maps.newHashMap();
                    attributeMap
                        .put("url", String.format(path + "/pages/function/menu/input?menuId=%s", funcTree.getId()));
                    // rootNode.setAttributes(attributeMap);
                }

                //rootNode.setHasChildren(false);
                //将根节点及子节点数据添加到结果集中
                // resultList.add(rootNode);
            }
        }
        return resultList;
    }

    private List<TreeNode> getChildTreeNode(String parentId, List<AuMenu> nodeList, String path) {
        List<TreeNode> resultList = new ArrayList<TreeNode>();
        for (AuMenu funcTree : nodeList) {
            //TreeNode leafNode = new TreeNode(funcTree.getId(), funcTree.getName());
            if (funcTree.getParentId() != null && !funcTree.getId().equals(parentId) && funcTree.getParentId()
                .equals(parentId)) {
                //存在子节点则添加子节点到父节点中，并设置标记
                if (!funcTree.isLeaf()) {
                    // List<TreeNode> leafNodeList = this.getChildTreeNode(funcTree.getId(), nodeList, path);
                    // leafNode.setChildren(leafNodeList);
                } else if (funcTree.isLeaf()) {
                    Map<String, Object> attributeMap = Maps.newHashMap();
                    attributeMap
                        .put("url", String.format(path + "/pages/function/menu/input?menuId=%s", funcTree.getId()));
                    // leafNode.setAttributes(attributeMap);
                }
                // resultList.add(leafNode);
            }
        }
        return resultList;
    }

    public List<AuMenu> getChildrens(AuMenu funcTree) {
        // TODO Auto-generated method stub
        List<AuMenu> result = null;
        String propertyName = "parentCode";
        //result=this.auMenuRepository.findByProperty(propertyName, funcTree.getTotalCode());
        return result;
    }

    public List<AuMenu> getParentChilds(AuMenu funcTree) {
        // TODO Auto-generated method stub
        List<AuMenu> result;
        String propertyName = "parentCode";
        result = null;//this.auMenuRepository.findByProperty(propertyName, funcTree.getParentCode());
        return result;
    }

    public AuMenu getParent(AuMenu funcTree) {
        // TODO Auto-generated method stub
        String propertyName = "totalCode";
        AuMenu parent = null;//(AuMenu) this.auMenuRepository.findByProperty(propertyName, funcTree.getParentCode()).get(0);
        return parent;
    }

    @Override
    public boolean checkUnique(AuMenu funcTree) {
        // TODO Auto-generated method stub
        return false;//auMenuRepository.checkUnique(funcTree);
    }

    @Override
    public AuMenu insert(AuMenu menu) {
        if (checkUnique(menu)) {
            throw new BusinessException(ResultCode.DATA_ALREADY_EXISTED);
        }
        AuMenu parent = getParent(menu);
        if (parent.isLeaf()) {
            parent.setLeaf(false);
            parent.setModifyDate(new Date());
            update(parent);
        }
        return save(menu);
    }

    @Override
    public boolean remove(List<Long> ids) {
        for (Long id : ids) {
            AuMenu auMenu = findById(id);
            if (auMenu.isLeaf()) {
                if (getParentChilds(auMenu).size() < 0) {
                    //父节点没有子节点则更新isLeaf状态
                    AuMenu parent = getParent(auMenu);
                    parent.setLeaf(true);
                    update(parent);
                }
                delete(id);
            } else {
                //此处删除子节点，亦可抛出异常不删除
                List<AuMenu> childrens = getChildrens(auMenu);
                delete(childrens);
            }
        }
        return true;
    }
}
