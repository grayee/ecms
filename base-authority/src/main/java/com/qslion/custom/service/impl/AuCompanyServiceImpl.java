
package com.qslion.custom.service.impl;


import com.google.common.collect.Lists;
import com.qslion.core.dao.PartyRelationRepository;
import com.qslion.core.entity.AuParty;
import com.qslion.core.entity.AuPartyRelation;
import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.core.service.PartyRelationService;
import com.qslion.custom.dao.AuCompanyRepository;
import com.qslion.custom.entity.AuCompany;
import com.qslion.custom.service.AuCompanyService;
import com.qslion.framework.enums.ResultCode;
import com.qslion.framework.exception.BusinessException;
import com.qslion.framework.service.impl.GenericServiceImpl;
import com.qslion.framework.util.CopyUtils;
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
    private PartyRelationRepository partyRelationRepository;

    @Autowired
    private PartyRelationService partyRelationService;


    @Override
    public AuCompany insert(AuCompany company, Long parentCode) {
        //添加公司团体
        AuParty auParty = company.buildAuParty();
        company.setAuParty(auParty);

        partyRelationService.addPartyRelation(parentCode, auParty, AuPartyRelationType.ADMINISTRATIVE);

        //如果用户不手工编号，则系统自动编号
        if (StringUtils.isEmpty(company.getCompanyNo())) {
            company.setCompanyNo(RandomStringUtils.random(10));
        }
        return companyRepository.save(company);
    }

    @Override
    public boolean remove(List<Long> ids) {
        List<AuCompany> companyList = Lists.newArrayList();
        List<AuPartyRelation> relationList = Lists.newArrayList();
        ids.forEach(companyId -> {
            AuCompany company = companyRepository.findById(companyId).get();

            AuPartyRelation partyRelation = partyRelationRepository.findByAuParty(company.getAuParty());
            if (partyRelation != null && partyRelation.isLeaf()) {
                companyList.add(company);
                relationList.add(partyRelation);
            } else {
                throw new BusinessException(ResultCode.PARAMETER_ERROR, "包含非叶子节点数据，请确认!");
            }
        });
        companyRepository.deleteAll(companyList);
        partyRelationRepository.deleteAll(relationList);
        return true;
    }

    /**
     * 更新单条记录，同时更新相应的团体、团体关系记录
     *
     * @param company 用于更新的VO对象
     * @return 成功更新的记录数
     */
    @Override
    public AuCompany update(AuCompany company) {
        AuCompany auCompany = companyRepository.findById(company.getId()).get();
        AuParty party = auCompany.getAuParty();
        party.setName(company.getCompanyName());
        party.setRemark(company.getRemark());
        AuPartyRelation partyRelation = partyRelationRepository.findByAuParty(party);
        partyRelation.setName(company.getCompanyName());
        partyRelation.setRemark(company.getRemark());
        partyRelationRepository.saveAndFlush(partyRelation);
        CopyUtils.copyProperties(company, auCompany);
        return companyRepository.saveAndFlush(auCompany);
    }
}
