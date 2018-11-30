/**
 *
 */
package com.qslion.core.service;

import com.qslion.core.entity.AuMenu;
import com.qslion.core.entity.AuParty;
import com.qslion.core.util.TreeNode;
import com.qslion.framework.service.IGenericService;
import java.util.List;

/**
 * 修改备注：
 *
 * @author Gray.Z
 * @date 2018/4/30 19:15.
 */
public interface AuMenuService extends IGenericService<AuMenu, Long> {


    /**
     * 根据访问者（用户，机构，角色）获得菜单树
     */
    List<TreeNode> getMenuTree(AuParty visitor, String path);

    /**
     * 取得当前菜单节点下的所有子节点
     */
    List<AuMenu> getChildren(AuMenu auMenu);

    /**
     * 取得当前菜单节点的父节点下的所有子节点
     */
    List<AuMenu> getParentChildren(AuMenu auMenu);

    /**
     * 取得当前菜单节点的父节点
     */
    AuMenu getParent(AuMenu auMenu);

    /**
     * 检查功能节点名字是否重复
     */
    boolean checkUnique(AuMenu auMenu);

    /**
     * 保存
     *
     * @param auMenu 菜单
     * @return 菜单
     */
    AuMenu insert(AuMenu auMenu);

    /**
     * 删除
     *
     * @param ids 菜单ID
     * @return 结果
     */
    boolean remove(List<Long> ids);

}
