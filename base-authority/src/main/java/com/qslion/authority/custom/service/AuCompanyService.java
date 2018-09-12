

package com.qslion.authority.custom.service;


import com.qslion.authority.custom.entity.AuCompany;
import com.qslion.framework.service.IGenericService;

/**
 * 功能、用途、现存BUG:
 * 
 * @author zhangrg
 * @version 1.0.0
 * 需要参见的其它类
 * @since 1.0.0
 */

public interface AuCompanyService extends IGenericService<AuCompany, Long> {
    /**
     * 添加新记录，同时添加团体、团体关系（如果parentRelId为空则不添加团体关系）
     * @param vo 用于添加的VO对象
     * @param parentRelId 上级节点团体关系主键
     * @return 若添加成功，则返回新添加记录的主键
     */
    public String insert(AuCompany vo, String parentRelId);

    /**
     *
     * 功能: 添加新记录，同时添加团体、团体关系根节点
     * @param vo 用于添加的VO对象
     * @return
     */
    public String insertRoot(AuCompany vo);
    
}
