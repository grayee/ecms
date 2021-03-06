package com.qslion.authority.core.service;


import com.qslion.authority.core.entity.AuOrgRelation;
import com.qslion.authority.core.entity.AuRole;
import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.service.IGenericService;

import java.util.List;

/**
 * Service 接口类 - 角色
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
public interface AuRoleService extends IGenericService<AuRole, Long> {


    /**
     * 根据类型查询角色
     *
     * @param typeId   类型ID
     * @param pageable 查询条件
     * @return 类型对应的角色
     */
    Pager<AuRole> findByType(Long typeId, Pageable pageable);

    /**
     * 新增角色
     *
     * @param role     角色
     * @return 角色
     */
    AuRole insert(AuRole role);


    /**
     * 功能授权
     *
     * @param role          角色
     * @param permAndResIds 资源权限ID，逗号分隔，@区分是资源还是权限
     * @return boolean
     */
    Boolean grantFuncAuth(AuRole role, List<String> permAndResIds);


    /**
     * 数据授权
     *
     * @param role          角色
     * @param partyRelations 机构
     * @return
     */
    Boolean grantDataAuth(AuRole role, List<AuOrgRelation> partyRelations);

    /**
     * 删除多条记录
     *
     * @param ids ids
     * @return boolean
     */
    boolean remove(List<Long> ids);
}