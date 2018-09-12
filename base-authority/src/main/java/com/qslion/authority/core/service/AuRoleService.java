package com.qslion.authority.core.service;


import com.qslion.authority.core.entity.AuRole;
import com.qslion.framework.service.IGenericService;

/**
 * @descrip: qslion 代码自动生成
 *
 */
 
public interface AuRoleService extends IGenericService<AuRole, Long> {

       public String insert(AuRole vo, String parentRelId);
       public String insertRoot(AuRole vo);
       //授权
       public void grantedAuthorities();
}