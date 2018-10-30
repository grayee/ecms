package com.qslion.core.service.impl;


import com.qslion.core.dao.AuPartyRepository;
import com.qslion.core.dao.AuRoleRepository;
import com.qslion.core.dao.PartyRelationRepository;
import com.qslion.core.entity.AuParty;
import com.qslion.core.entity.AuRole;
import com.qslion.core.service.AuRoleService;
import com.qslion.framework.service.impl.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @descrip: ecms 代码自动生成
 */

@Service("roleService")
public class AuRoleServiceImpl extends GenericServiceImpl<AuRole, Long> implements AuRoleService {

    @Autowired
    private AuRoleRepository auRoleRepository;
    @Autowired
    private AuPartyRepository partyRepository;
    @Autowired
    private PartyRelationRepository partyRelationRepository;


    /**
     * 功能: 添加新记录，同时添加团体、团体关系（如果parentRelId为空则不添加团体关系）
     *
     * @param vo          用于添加的VO对象
     * @param parentRelId 上级节点团体关系主键
     * @return 若添加成功，则返回新添加记录的主键
     */
    @Override
    public String insert(AuRole vo, String parentRelId) {
        AuParty auParty = new AuParty();
        auParty.setAuPartyType(null);
        auParty.setName(vo.getName());
        auParty.setRemark(vo.getDescription());
        //auParty.setEnableStatus("1");
        auParty.setInherit(true);
        auParty.setReal(true);
     /*   vo.setAuParty(auParty);
        //如果用户不手工编号，则系统自动编号
        if (StringUtils.isEmpty(vo.getRoleValue())) {
            vo.setRoleValue(auParty.getId());
        }
        String partyId = auRoleRepository.save(vo).getId();*/
        //添加团体关系
      /*  if (StringUtils.isNotEmpty(parentRelId) && StringUtils.isNotEmpty(partyId)) {
           *//* String relTypeId = GlobalConstants.getRelTypeRole();
            OrgHelper.addAuPartyRelation(partyId, parentRelId, relTypeId);*//*
        }*/
        return "";
    }


    @Override
    public String insertRoot(AuRole vo) {
        // TODO Auto-generated method stub
        String partyId = this.insert(vo, "");
       /* String relTypeId = GlobalConstants.getRelTypeRole();
        if (StringUtils.isNotEmpty(partyId)) {
            OrgHelper.addRoot(partyId, relTypeId);
        }*/
        return partyId;
    }

    @Override
    public AuRole update(AuRole vo) {
     /*   AuParty party = partyRepository.findOne(vo.getId());
        party.setName(vo.getName());//团体名称
        party.setEmail("");//团体EMAIL（对员工类型为必填）
        party.setRemark(vo.getDescription());//备注
        vo.setAuParty(party);*/
      //  roleRepository.clear();
       // boolean flag = roleRepository.update(vo);
       /* AuPartyRelation relation = partyRelationDao.findByPartyId(vo.getId(), GlobalConstants.getRelTypeRole());
        relation.setName(vo.getName());
        partyRelationDao.update(relation);*/
        //RmLogHelper.log(TABLE_LOG_TYPE_NAME, "更新了" + sum + "条记录,id=" + String.valueOf(vo.getId()));
        return null;
    }


    @Override
    public void grantedAuthorities() {
        // TODO Auto-generated method stub

    }

}
