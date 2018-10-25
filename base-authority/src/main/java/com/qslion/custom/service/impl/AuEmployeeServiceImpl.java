package com.qslion.custom.service.impl;


import com.qslion.core.dao.AuPartyRepository;
import com.qslion.core.dao.PartyRelationRepository;
import com.qslion.core.entity.AuParty;
import com.qslion.custom.dao.AuEmployeeRepository;
import com.qslion.custom.entity.AuEmployee;
import com.qslion.custom.service.AuEmployeeService;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.service.impl.GenericServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：authority 类名称：EmployeeServiceImpl 类描述： 创建人：Administrator 创建时间：2011-8-8 下午02:58:36
 * 修改人：Administrator 修改时间：2011-8-8 下午02:58:36 修改备注：
 */
@Service("employeeService")
public class AuEmployeeServiceImpl extends GenericServiceImpl<AuEmployee, Long> implements AuEmployeeService {
    @Autowired
    private AuEmployeeRepository employeeDao;
    @Autowired
    private AuPartyRepository partyRepository;

    @Autowired
    private PartyRelationRepository partyRelationDao;


    /**
     * 添加新记录，同时添加团体、团体关系（如果parentRelId为空则不添加团体关系）
     *
     * @param vo          用于添加的VO对象
     * @param parentRelId 上级节点团体关系主键
     * @return 若添加成功，则返回新添加记录的主键
     */
    public String insert(AuEmployee vo, String parentRelId) {
        AuParty auParty = new AuParty();
        //auParty.setAuPartyType(partyTypeDao.get(GlobalConstants.getPartyTypeEmpl()));
        auParty.setName(vo.getPersonName());
        auParty.setRemark(vo.getRemark());
        //auParty.setEnableStatus(vo.getEnableStatus());
        auParty.setIsInherit("1");
        auParty.setReal(true);
       // vo.setAuParty(auParty);
        //如果用户不手工编号，则系统自动编号
        if (StringUtils.isEmpty(vo.getPersonNo())) {
            //vo.setPersonNo(auParty.getId());
        }
        String partyId ="";// employeeDao.save(vo);
        //添加团体关系
        if (StringUtils.isNotEmpty(parentRelId) && StringUtils.isNotEmpty(partyId)) {
            String relTypeId = "";//GlobalConstants.getRelTypeComp();
           // OrgHelper.addAuPartyRelation(partyId, parentRelId, relTypeId);
        }
        return partyId;
    }

    /**
     * 如果是一人多岗或人员既属于部门又属于岗位，只删除当前关系 如果是一人一岗，把该人员挂到部门下面 如果是人员只属于部门，彻底删除该人员
     *
     * @param partyRelationId 人员的团体关系id
     */
    public boolean delete(String partyRelationId) {
        //注：这里依赖简版连接规则：公司-公司，公司-部门，部门-部门，部门-岗位，部门-人员，岗位-人员
        int n = 0;
        boolean result = false;
   /*     String partyid = OrgHelper.getPartyIDByRelationID(partyRelationId);
        String codes[] = OrgHelper.getRelationCode(partyid);
        if (codes.length > 1) {//如果是一人多岗或人员既属于部门又属于岗位，只删除当前关系
            //result = OrgHelper.deletePartyRelation(partyRelationId);//调用接口删除当前关系和与其相应的权限等记录
            n = result ? 1 : 0;
        } else if (codes.length == 1) {//如果是一人一岗或人员只属于部门
          *//*  AuPartyRelation parentVo = OrgHelper.getUpRelationVoByID(partyRelationId);
            if ("GlobalConstants.getPartyType_posi()".equals(parentVo.getPartytypeId())) {//如果父节点是岗位，把该人员挂到部门下面
                AuPartyRelation deptVo = OrgHelper.getUpRelationVoByID(parentVo.getId());//取到部门节点
                //OrgHelper.addPartyRelation(partyid, deptVo.getId(), deptVo.getRelationtypeId());//将当前节点挂到部门节点底下
                //result = OrgHelper.deletePartyRelation(partyRelationId);//删除当前节点
                n = result ? 1 : 0;
            } else if ("GlobalConstants.getPartyType_dept()".equals(parentVo.getPartytypeId())) {//如果父节点是部门，彻底删除该人员
                result = employeeDao.delete(partyid);
                OrgHelper.deleteParty(partyid);//调用接口删除相应的团体、团体关系、帐户、权限等记录
            }*//*
        }*/
        return result;
    }

    /**
     * 删除多条记录，删除自身并同时删除相应的团体、团体关系、帐户、权限等记录
     *
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public boolean delete(String id[]) {
        boolean flag = false;//employeeDao.delete(id);
    /*	for(int i=0; i<id.length; i++) {
		    OrgHelper.deleteParty(id[i]);//调用接口删除相应的团体、团体关系、帐户、权限等记录
		}
        //RmLogHelper.log(TABLE_LOG_TYPE_NAME, "删除了" + sum + "条记录,id=" + String.valueOf(id));
*/
        return flag;
    }

    public Pager<AuEmployee> findByPager(Pager<AuEmployee> pager) {
        return null;
    }

    /**
     * 更新单条记录，同时调用接口更新相应的团体、团体关系记录
     *
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public AuEmployee update(AuEmployee vo) {
        AuParty party = partyRepository.getOne(vo.getId());
        party.setName(vo.getPersonName());//团体名称
        party.setRemark(vo.getRemark());//备注
        //vo.setAuParty(party);
       /* employeeDao.clear();
        boolean flag = employeeDao.update(vo);
        AuPartyRelation relation = partyRelationDao.findByPartyId(vo.getId(), GlobalConstants.getRelTypeComp());
        relation.setName(vo.getPersonName());
        partyRelationDao.update(relation);*/
        //RmLogHelper.log(TABLE_LOG_TYPE_NAME, "更新了" + sum + "条记录,id=" + String.valueOf(vo.getId()));
        return null;
    }
}
