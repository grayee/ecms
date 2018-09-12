/**
 *
 */
package com.qslion.authority.core.service.impl;

import com.google.common.collect.Maps;
import com.qslion.authority.core.dao.AuFuncTreeRepository;
import com.qslion.authority.core.dao.AuResourceRepository;
import com.qslion.authority.core.entity.AuFuncMenu;
import com.qslion.authority.core.entity.AuParty;
import com.qslion.authority.core.service.FuncMenuService;
import com.qslion.authority.core.util.TreeNode;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.service.impl.GenericServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 修改备注：
 */
@Service("funcTreeService")
public class FuncMenuServiceImpl extends GenericServiceImpl<AuFuncMenu, Long> implements FuncMenuService {

    @Autowired
    private AuFuncTreeRepository funcTreeRepository;
    @Autowired
    public AuResourceRepository resourceRepository;

    public List<AuFuncMenu> queryByCondition(String paramString) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<TreeNode> getFuncMenuTree(AuParty visitor, String path) {
        //System.out.println(visitor.getName()+"====>>>>>"+visitor.getAuPartyType().getId()+"===>"+ GlobalConstants.getPartyTypeEmpl());

        //visitor.getAuAuthorize().getAuResources();
        List<TreeNode> resultList = new ArrayList<TreeNode>();
        //所有菜单功能树,后期改成根据登录用户的权限查询
        List<AuFuncMenu> funcMenus = this.funcTreeRepository.findByStatus(AuFuncMenu.MenuStatus.enable);
        //转换成ztree控件需要的格式
        for (AuFuncMenu funcTree : funcMenus) {
            //获取跟节点
            if (funcTree.getParentId().equals(funcTree.getId())) {
                TreeNode rootNode = new TreeNode(funcTree.getId(), funcTree.getName());
                rootNode.setIconCls(funcTree.getIconClass());

                //存在子节点则添加子节点到父节点中，并设置标记
                if (!funcTree.isLeaf()) {
                    List<TreeNode> leafNodeList = this.getChildTreeNode(funcTree.getId(), funcMenus, path);
                    rootNode.setChildren(leafNodeList);
                } else if (funcTree.isLeaf()) {
                    Map<String, Object> attributeMap = Maps.newHashMap();
                    attributeMap.put("url", String.format(path + "/pages/function/menu/input?menuId=%s", funcTree.getId()));
                    rootNode.setAttributes(attributeMap);
                }

                //rootNode.setHasChildren(false);
                //将根节点及子节点数据添加到结果集中
                resultList.add(rootNode);
            }
        }
        return resultList;
    }

    private List<TreeNode> getChildTreeNode(String parentId, List<AuFuncMenu> nodeList, String path) {
        List<TreeNode> resultList = new ArrayList<TreeNode>();
        for (AuFuncMenu funcTree : nodeList) {
            TreeNode leafNode = new TreeNode(funcTree.getId(), funcTree.getName());
            if (funcTree.getParentId() != null && !funcTree.getId().equals(parentId) && funcTree.getParentId().equals(parentId)) {
                //存在子节点则添加子节点到父节点中，并设置标记
                if (!funcTree.isLeaf()) {
                    List<TreeNode> leafNodeList = this.getChildTreeNode(funcTree.getId(), nodeList, path);
                    leafNode.setChildren(leafNodeList);
                } else if (funcTree.isLeaf()) {
                    Map<String, Object> attributeMap = Maps.newHashMap();
                    attributeMap.put("url", String.format(path + "/pages/function/menu/input?menuId=%s", funcTree.getId()));
                    leafNode.setAttributes(attributeMap);
                }
                resultList.add(leafNode);
            }
        }
        return resultList;
    }

    public List<AuFuncMenu> getChildrens(AuFuncMenu funcTree) {
        // TODO Auto-generated method stub
        List<AuFuncMenu> result = null;
        String propertyName = "parentCode";
        //result=this.funcTreeRepository.findByProperty(propertyName, funcTree.getTotalCode());
        return result;
    }

    public List<AuFuncMenu> getParentChilds(AuFuncMenu funcTree) {
        // TODO Auto-generated method stub
        List<AuFuncMenu> result;
        String propertyName = "parentCode";
        result = null;//this.funcTreeRepository.findByProperty(propertyName, funcTree.getParentCode());
        return result;
    }

    public AuFuncMenu getParent(AuFuncMenu funcTree) {
        // TODO Auto-generated method stub
        String propertyName = "totalCode";
        AuFuncMenu parent = null;//(AuFuncMenu) this.funcTreeRepository.findByProperty(propertyName, funcTree.getParentCode()).get(0);
        return parent;
    }

    @Override
    public boolean checkUnique(AuFuncMenu funcTree) {
        // TODO Auto-generated method stub
        return false;//funcTreeRepository.checkUnique(funcTree);
    }
}
