package com.qslion.authority.core.service.impl;/*package com.qslion.authority.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qslion.authority.core.dao.PartyRelationRepository;
import com.qslion.authority.core.dao.AuVisitorDao;
import com.qslion.authority.core.entity.AuPartyRelation;
import com.qslion.authority.core.entity.AuVisitor;
import com.qslion.authority.core.service.AuVisitorService;
import com.qslion.authority.core.util.GlobalConstants;

import com.qslion.framework.service.impl.GenericHibernateServiceImpl;

*//**
 * @descrip: qslion code genarator
 * @copyright: "Copyright © 2013-2020 qslion, All Rights Reserved";
 * @author: "zhangrg";  
 * @link: "<a href=http://www.qslion.com>qslion public limit corperation</a>"; 
 * @create_date: 2014-03-06 14:11:56
 * @update_date: 2014-03-06 14:11:56
 *
 *//*
 
@Service
public class AuVisitorServiceImpl extends GenericHibernateServiceImpl<AuVisitor, String> implements AuVisitorService {
    
	@Autowired
	private AuVisitorDao auVisitorDao;
	@Autowired
	private PartyRelationRepository partyRelationDao;
	
	@Autowired
	public void setBaseDao(AuVisitorDao auVisitorDao) {
		super.setBaseDao(auVisitorDao);
	}

    
     * 本方法实现访问者设计模式
     * 根据团体关系ID和团体类型查询相应的访问者Vo，如果查不到则自动生成一个访问者vo并添加到访问者表中，然后返回新添加的访问者vo
     * 
	@Override
	public AuVisitor getVisitorByRelationId(String partyRelationId,String partyTypeId) {
		// TODO Auto-generated method stub
		 AuPartyRelation relation=new AuPartyRelation();
		 relation.setId(partyRelationId);
		 AuVisitor vo=new AuVisitor();
		 vo.setAuPartyRelation(relation);
		 vo.setVisitorType(GlobalConstants.getVisiTypeByPartyType(partyTypeId));
		 List<AuVisitor> visitors= auVisitorDao.findByExample(vo);
		    if (visitors.size()==0) {
		      relation =partyRelationDao.get(partyRelationId);
		      vo = new AuVisitor();
		      vo.setCode(relation.getCode());
		      vo.setName(relation.getName());
		      vo.setAuPartyRelation(relation);
		      vo.setPartyrelationtypeId(relation.getAuPartyRelationType().getId());
		      vo.setPartytypeId(relation.getAuParty().getAuPartyType().getId());
		      vo.setVisitorType(GlobalConstants.getVisiTypeByPartyType(vo.getPartytypeId()));
		      auVisitorDao.insert(vo);
		    }
		    return visitors.get(0);
	}

}
*/