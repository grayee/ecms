/*
 * 系统名称:单表模板 --> test
 * 
 * 文件名称: gap.authority.sample.department.bs --> IDepartmentBs.java
 * 
 * 功能描述:
 * 
 * 版本历史: 2007-01-31 14:20:09.9 创建1.0.0版 (甘硕)
 *  
 */

package com.qslion.custom.service;


import com.qslion.custom.entity.AuDepartment;
import com.qslion.framework.service.IGenericService;

/**
 * 功能、用途、现存BUG:
 * @version 1.0.0
 *  需要参见的其它类
 * @since 1.0.0
 */

public interface AuDepartmentService extends IGenericService<AuDepartment, Long> {

    /**
     * 添加新记录，同时添加团体、团体关系（如果parentRelId为空则不添加团体关系）
     * 
     * @param vo 用于添加的VO对象
     * @param parentRelId 上级节点团体关系主键
     * @return 若添加成功，则返回新添加记录的主键
     */
    public String insert(AuDepartment vo, String parentRelId);
    
    
}
