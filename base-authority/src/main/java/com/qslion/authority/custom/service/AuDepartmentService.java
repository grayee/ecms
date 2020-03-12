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

package com.qslion.authority.custom.service;


import com.qslion.authority.custom.entity.AuDepartment;
import com.qslion.framework.service.IGenericService;

import java.util.List;

/**
 * 部门Service类
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
public interface AuDepartmentService extends IGenericService<AuDepartment, Long> {


    /**
     * 添加新记录，同时添加组织和对应关系（如果parentRelId为空则不添加组织关系）
     *
     * @param vo 用于添加的VO对象
     * @return 若添加成功，则返回新添加记录的主键
     */
    AuDepartment insert(AuDepartment vo);


    /**
     * 删除多条记录，删除自身并同时删除相应的组织和关系
     *
     * @param ids ids
     * @return boolean
     */
    boolean remove(List<Long> ids);

}
