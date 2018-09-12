package com.qslion.authority.core.dao;

import com.qslion.authority.core.entity.AuFuncMenu;
import com.qslion.framework.dao.IGenericRepository;

import java.util.List;

/**
 * Dao实现类 - 功能菜单
 */
public interface AuFuncTreeRepository extends IGenericRepository<AuFuncMenu, String> {


    List<AuFuncMenu> findByStatus(AuFuncMenu.MenuStatus status);
}