package com.qslion.authority.custom.service;


import com.qslion.authority.custom.entity.AuPosition;
import com.qslion.framework.service.IGenericService;

import java.util.List;

/**
 * 职位Service
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
public interface AuPositionService extends IGenericService<AuPosition, Long> {
    /**
     * 添加新记录，同时添加组织和对应关系（如果parentRelId为空则不添加组织关系）
     *
     * @param position 用于添加的VO对象
     * @return AuPosition
     */
    AuPosition insert(AuPosition position);

    /**
     * 删除多条记录，删除自身并同时删除相应的组织和关系
     *
     * @param ids ids
     * @return boolean
     */
    boolean remove(List<Long> ids);
}
