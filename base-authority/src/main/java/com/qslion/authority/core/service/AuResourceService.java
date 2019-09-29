/**
 *
 */
package com.qslion.authority.core.service;

import com.qslion.authority.core.entity.AuMenu;
import com.qslion.authority.core.entity.AuPermission;
import com.qslion.authority.core.entity.AuResource;
import com.qslion.framework.bean.TreeNode;
import com.qslion.framework.service.IGenericService;

import java.util.List;

/**
 * 修改备注：
 */

public interface AuResourceService extends IGenericService<AuResource, Long> {


    /**
     * 插入
     *
     * @param auResource 资源对象
     * @return 资源对象
     */
    AuResource insert(AuResource auResource);

    /**
     * 根据用户获得资源树,status用来区分展示和修改状态
     *
     * @return list
     */
    List<TreeNode> getResourceTree(List<AuPermission> rolePerms, boolean showPermission);


    /**
     * 获取已授权功能权限树
     *
     * @param rolePerms
     * @return tree
     */
    List<TreeNode> getGrantedFuncTree(List<AuPermission> rolePerms);

    /**
     * 取得当前菜单节点下的所有子节点
     *
     * @param auResource 资源对象
     * @return 资源列表
     */
    List<AuResource> getChildren(AuResource auResource);

    /**
     * 取得当前菜单节点的父节点下的所有子节点
     */
    List<AuResource> getParentChildren(AuResource auResource);

    /**
     * 取得当前菜单节点的父节点
     *
     * @param auResource 资源对象
     * @return 资源对象
     */
    AuResource getParent(AuResource auResource);

    /**
     * 检查功能节点名字是否重复
     */
    boolean checkUnique(AuResource auResource);

    AuResource findByMenu(AuMenu menu);

    /**
     * 资源增加权限
     *
     * @param id         资源id
     * @param permission permission
     * @return boolean
     */
    boolean addPermission(Long id, AuPermission permission);


    /**
     * 资源删除权限
     *
     * @param ids 权限id
     * @return boolean
     */
    boolean removePermission(List<Long> ids);

    /**
     * 更新权限信息
     *
     * @param id         权限id
     * @param permission permission
     * @return boolean
     */
    boolean updatePermission(Long id, AuPermission permission);
}
