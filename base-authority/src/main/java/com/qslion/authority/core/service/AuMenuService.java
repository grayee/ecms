/**
 *
 */
package com.qslion.authority.core.service;

import com.qslion.authority.core.entity.AuMenu;
import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.bean.TreeNode;
import com.qslion.framework.service.IGenericService;

import java.util.List;

/**
 * 菜单Service接口
 *
 * @author Gray.Z
 * @date 2018/4/30 19:15.
 */
public interface AuMenuService extends IGenericService<AuMenu, Long> {

    /**
     * 菜单列表
     *
     * @param username 用户名
     * @param pageable pageable
     * @return pager List
     */
    Pager<AuMenu>  getMenuList(String username, Pageable pageable);

    /**
     * 根据访问者（用户，机构，角色）获得菜单树
     */
    List<TreeNode> getMenuTree(String username);

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
