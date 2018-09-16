/**
 *
 */
package com.qslion.core.service;

import com.qslion.core.entity.AuResource;
import com.qslion.core.entity.AuUser;
import com.qslion.core.util.TreeNode;
import com.qslion.framework.service.IGenericService;
import java.util.List;

/**
 * 修改备注：
 */

 public interface AuResourceService extends IGenericService<AuResource, Long> {


      List<AuResource> queryByCondition(String hql);

    /**
     * 根据用户获得菜单树,status用来区分展示和修改状态
     */
     List<TreeNode> getFuncMenuTree(AuUser user, String status, String path);

    /**
     * 取得当前菜单节点下的所有子节点
     */
     List<AuResource> getChildrens(AuResource auResource);

    /**
     * 取得当前菜单节点的父节点下的所有子节点
     */
     List<AuResource> getParentChilds(AuResource auResource);

    /**
     * 取得当前菜单节点的父节点
     */
     AuResource getParent(AuResource auResource);

    /**
     * 检查功能节点名字是否重复
     */
     boolean checkUnique(AuResource auResource);

}
