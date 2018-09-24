package com.qslion.custom.service.impl;


import com.qslion.core.dao.AuPartyRepository;
import com.qslion.core.dao.PartyRelationRepository;
import com.qslion.core.entity.AuParty;
import com.qslion.custom.dao.AuDepartmentRepository;
import com.qslion.custom.entity.AuDepartment;
import com.qslion.custom.service.AuDepartmentService;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.service.impl.GenericServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 功能、用途、现存BUG:
 * @version 1.0.0
 *需要参见的其它类
 * @since 1.0.0
 */
@Service("departmentService")
public class AuDepartmentServiceImpl extends GenericServiceImpl<AuDepartment, Long> implements AuDepartmentService {
    
	@Autowired
    private AuDepartmentRepository departmentDao;
	@Autowired
	private AuPartyRepository partyDao;

	@Autowired
	private PartyRelationRepository partyRelationDao;

    /**
     * 添加新记录，同时添加团体、团体关系（如果parentRelId为空则不添加团体关系）
     * @param vo 用于添加的VO对象
     * @param parentRelId 上级节点团体关系主键
     * @return 若添加成功，则返回新添加记录的主键
     */
    public String insert(AuDepartment vo, String parentRelId) {
    	 AuParty auParty = new AuParty();
         //auParty.setAuPartyType(partyTypeDao.get(GlobalConstants.getPartyTypeDept()));
         auParty.setName(vo.getDeptName());
         auParty.setRemark(vo.getRemark());	
         //auParty.setEnableStatus(vo.getEnableStatus());
         auParty.setIsInherit("1");
         auParty.setIsReal("1");
 	    // vo.setAuParty(auParty);
 		 //如果用户不手工编号，则系统自动编号
         if(StringUtils.isEmpty(vo.getDeptNo())) {
             //vo.setDeptNo(auParty.getId());
         }
         String partyId="";//departmentDao.save(vo);
         //添加团体关系
         if(StringUtils.isNotEmpty(parentRelId)&&StringUtils.isNotEmpty(partyId)) {
             String relTypeId ="";//GlobalConstants.getRelTypeComp();
             //OrgHelper.addAuPartyRelation(partyId, parentRelId, relTypeId);
         }
 		return partyId;
    }
    
    /**
     * 删除单条记录，同时删除团体关系及相关的权限记录
     * 
     * @param partyRelationId 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public boolean delete(String partyRelationId) {
    	//String partyid = OrgHelper.getPartyIDByRelationID(partyRelationId);
    	boolean flag=false;//departmentDao.delete(partyid);
    	//OrgHelper.deleteParty(partyid);//调用接口删除相应的团体、团体关系、帐户、权限等记录
        return flag;
    }

    /**
     * 删除多条记录，删除自身并同时删除相应的团体、团体关系、帐户、权限等记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public boolean delete(String id[]) {
    	boolean flag=false;//departmentDao.delete(id);
        return flag;
    }

    public Pager<AuDepartment> findByPager(Pager<AuDepartment> pager) {
        return null;
    }


    /**
     * 更新单条记录，同时调用接口更新相应的团体、团体关系记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public AuDepartment update(AuDepartment vo) {
    	/*  AuParty party =partyDao.load(vo.getId());
          party.setName(vo.getDeptName());//团体名称
          party.setEmail("");//团体EMAIL（对员工类型为必填）
          party.setRemark(vo.getRemark());//备注	
          vo.setAuParty(party);
          departmentDao.clear();
          boolean flag= departmentDao.update(vo);
          AuPartyRelation relation=partyRelationDao.findByPartyId(vo.getId(), GlobalConstants.getRelTypeComp());
          relation.setName(vo.getDeptName());
          partyRelationDao.update(relation);*/
          //RmLogHelper.log(TABLE_LOG_TYPE_NAME, "更新了" + sum + "条记录,id=" + String.valueOf(vo.getId()));
          return null;
    }

}
