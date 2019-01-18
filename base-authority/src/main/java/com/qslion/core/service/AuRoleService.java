package com.qslion.core.service;


import com.qslion.core.entity.AuRole;
import com.qslion.framework.service.IGenericService;
import java.util.List;

/**
 * @descrip: qslion 代码自动生成
 */

public interface AuRoleService extends IGenericService<AuRole, Long> {

    /**
     * 新增角色
     *
     * @param role 角色
     * @param parentId 上级ID
     * @return 角色
     */
    AuRole insert(AuRole role, Long parentId);


    //授权
    void grantedAuthorities();

    /**
     * 删除多条记录
     *
     * @param ids ids
     * @return boolean
     */
    boolean remove(List<Long> ids);
}