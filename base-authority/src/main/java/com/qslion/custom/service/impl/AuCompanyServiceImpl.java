
package com.qslion.custom.service.impl;


import com.qslion.core.dao.AuPartyRepository;
import com.qslion.core.dao.PartyRelationRepository;
import com.qslion.core.entity.AuParty;
import com.qslion.core.entity.AuPartyRelation;
import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.core.enums.AuPartyType;
import com.qslion.custom.dao.AuCompanyRepository;
import com.qslion.custom.entity.AuCompany;
import com.qslion.custom.service.AuCompanyService;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.service.impl.GenericServiceImpl;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("companyService")
public class AuCompanyServiceImpl extends GenericServiceImpl<AuCompany, Long> implements AuCompanyService {

    @Autowired
    private AuCompanyRepository companyRepository;
    @Autowired
    private AuPartyRepository partyRepository;
    @Autowired
    private PartyRelationRepository partyRelationRepository;

    @Override
    public AuCompany insert(AuCompany company, Long parentCode) {
        //添加公司团体
        AuParty auParty = new AuParty();
        auParty.setAuPartyType(AuPartyType.COMPANY);
        auParty.setName(company.getCompanyName());
        auParty.setRemark(company.getRemark());
        auParty.setEnableStatus(company.getEnableStatus());
        auParty.setInherit(true);
        auParty.setReal(true);

        //添加团体关系
        AuPartyRelation auPartyRelation = new AuPartyRelation();
        auPartyRelation.setAuParty(auParty);
        auPartyRelation.setName(company.getCompanyName());
        if (parentCode != null) {
            auPartyRelation.setParentId(parentCode);
            auPartyRelation.setLeaf(true);
            //更新父节点isLeaf 为false
            partyRelationRepository.updateLeaf(parentCode, false);
        }
        auPartyRelation.setRemark(company.getRemark());
        auPartyRelation.setAuPartyRelationType(AuPartyRelationType.ADMINISTRATIVE);
        partyRelationRepository.save(auPartyRelation);

        //添加公司
        company.setAuParty(auParty);
        //如果用户不手工编号，则系统自动编号
        if (StringUtils.isEmpty(company.getCompanyNo())) {
            company.setCompanyNo(RandomStringUtils.random(10));
        }
        return companyRepository.save(company);
    }


    /**
     * 删除单条记录，同时删除团体关系及相关的权限记录
     *
     * @param partyRelationId 用于删除的记录的id
     */
    public boolean delete(String partyRelationId) {
        //String partyid = OrgHelper.getPartyIDByRelationID(partyRelationId);
        //companyRepository.delete(partyid);
        // OrgHelper.deleteParty(partyid);//调用接口删除相应的团体、团体关系、帐户、权限等记录
        return true;
    }



    /**
     * 删除多条记录，删除自身并同时删除相应的团体、团体关系、帐户、权限等记录
     *
     * @param ids 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public boolean delete1(List<Long> ids) {
        //companyRepository.delete(Lists.newArrayList());
        return true;
    }

    public Pager<AuCompany> findByPager(Pager<AuCompany> pager) {
        return null;
    }


    /**
     * 更新单条记录，同时更新相应的团体、团体关系记录
     *
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    @Override
    public AuCompany update(AuCompany vo) {
        AuParty party = partyRepository.getOne(vo.getId());
        party.setName(vo.getCompanyName());//团体名称
        party.setRemark(vo.getRemark());//备注
        //vo.setAuParty(party);

        companyRepository.save(vo);
        AuPartyRelation relation = null;// partyRelationRepository.findByPartyId(vo.getId(), AuPartyRelationType.ADMINISTRATIVE.getId() + "");
        relation.setName(vo.getCompanyName());
        //partyRelationRepository.update(relation);
        //RmLogHelper.log(TABLE_LOG_TYPE_NAME, "更新了" + sum + "条记录,id=" + String.valueOf(vo.getId()));
        return null;
    }
}
