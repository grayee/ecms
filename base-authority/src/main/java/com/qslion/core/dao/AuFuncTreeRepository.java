package com.qslion.core.dao;

import com.qslion.core.entity.AuFuncMenu;
import com.qslion.framework.dao.IGenericRepository;

import java.util.List;

/**
 * Dao实现类 - 功能菜单
 */
public interface AuFuncTreeRepository extends IGenericRepository<AuFuncMenu, Long> {


    List<AuFuncMenu> findByStatus(AuFuncMenu.MenuStatus status);
}