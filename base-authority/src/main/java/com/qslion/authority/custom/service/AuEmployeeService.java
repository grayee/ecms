/**
 *
 */
package com.qslion.authority.custom.service;


import com.qslion.authority.custom.entity.AuEmployee;
import com.qslion.framework.service.IGenericService;

import java.util.List;

/**
 * 员工Service
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
public interface AuEmployeeService extends IGenericService<AuEmployee, Long> {

    /**
     * 添加新记录，同时添加团体、团体关系（如果parentRelId为空则不添加团体关系）
     *
     * @param employee 用于添加的VO对象
     * @return AuEmployee
     */
    AuEmployee insert(AuEmployee employee);


    /**
     * 删除多条记录，删除自身并同时删除相应的团体、团体关系
     *
     * @param ids ids
     * @return boolean
     */
    boolean remove(List<Long> ids);
}
