/**
 *
 */
package com.qslion.custom.service;


import com.qslion.core.entity.AuParty;
import com.qslion.custom.entity.AuEmployee;
import com.qslion.custom.entity.AuPosition;
import com.qslion.framework.service.IGenericService;

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
     * @param vo          用于添加的VO对象
     * @param parentRelId 上级节点团体关系主键
     * @return 若添加成功，则返回新添加记录的主键
     */
    String insert(AuEmployee vo, String parentRelId);


    AuEmployee findByParty(AuParty party);

}
