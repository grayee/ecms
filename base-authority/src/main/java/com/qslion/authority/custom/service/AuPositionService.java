package com.qslion.authority.custom.service;


import com.qslion.authority.custom.entity.AuPosition;
import com.qslion.framework.service.IGenericService;

/**
 *    
 * 项目名称：authority   
 * 类名称：PositionService   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2011-8-8 下午03:00:29   
 * 修改人：Administrator   
 * 修改时间：2011-8-8 下午03:00:29   
 * 修改备注：   
 * @version    
 *    
 */

public interface AuPositionService extends IGenericService<AuPosition, Long> {
	 /**
     * 添加新记录，同时添加团体、团体关系（如果parentRelId为空则不添加团体关系）
     * 
     * @param vo 用于添加的VO对象
     * @param parentRelId 上级节点团体关系主键
     * @return 若添加成功，则返回新添加记录的主键
     */
    public String insert(AuPosition vo, String parentRelId);
  



}
