package com.qslion.authority.custom.dao;

import com.qslion.authority.core.entity.AuMenu;
import com.qslion.framework.dao.IGenericRepository;
import org.springframework.stereotype.Repository;


/**
 * Dao实现类 - 菜单
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Repository
public interface AuMenuRepository extends IGenericRepository<AuMenu, Long> {

}