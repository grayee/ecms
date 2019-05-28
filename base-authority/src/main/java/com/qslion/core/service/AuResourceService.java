/**
 *
 */
package com.qslion.core.service;

import com.qslion.core.entity.AuMenu;
import com.qslion.core.entity.AuResource;
import com.qslion.core.entity.AuUser;
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
     */
    List<TreeNode> getResourceTree(AuUser user, String status, String path);

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
     * @param auResource  资源对象
     * @return 资源对象
     */
    AuResource getParent(AuResource auResource);

    /**
     * 检查功能节点名字是否重复
     */
    boolean checkUnique(AuResource auResource);

    AuResource findByMenu(AuMenu menu);
}
