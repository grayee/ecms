/**
 *
 */
package com.qslion.core.service;

import com.qslion.core.entity.AuFuncMenu;
import com.qslion.core.entity.AuParty;
import com.qslion.core.util.TreeNode;
import com.qslion.framework.service.IGenericService;
import java.util.List;

/**
 * 修改备注：
 */

public interface FuncMenuService extends IGenericService<AuFuncMenu, Long> {


    List<AuFuncMenu> queryByCondition(String hql);

    /**
     * 根据访问者（用户，机构，角色）获得菜单树
     */
    List<TreeNode> getFuncMenuTree(AuParty visitor, String path);

    /**
     * 取得当前菜单节点下的所有子节点
     */
    List<AuFuncMenu> getChildrens(AuFuncMenu funcTree);

    /**
     * 取得当前菜单节点的父节点下的所有子节点
     */
    List<AuFuncMenu> getParentChilds(AuFuncMenu funcTree);

    /**
     * 取得当前菜单节点的父节点
     */
    public AuFuncMenu getParent(AuFuncMenu funcTree);

    /**
     * 检查功能节点名字是否重复
     */
    boolean checkUnique(AuFuncMenu funcTree);

}
