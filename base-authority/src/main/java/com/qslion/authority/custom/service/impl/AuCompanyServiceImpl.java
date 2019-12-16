
package com.qslion.authority.custom.service.impl;


import com.qslion.authority.core.dao.PartyRelationRepository;
import com.qslion.authority.core.entity.AuPartyRelation;
import com.qslion.authority.core.enums.AuPartyRelationType;
import com.qslion.authority.core.service.PartyRelationService;
import com.qslion.authority.custom.dao.AuCompanyRepository;
import com.qslion.authority.custom.entity.AuCompany;
import com.qslion.authority.custom.service.AuCompanyService;
import com.qslion.framework.enums.EnableStatus;
import com.qslion.framework.service.impl.GenericServiceImpl;
import com.qslion.framework.util.CopyUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 公司Service类
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Service("companyService")
@Transactional(value = "transactionManager", rollbackFor = Exception.class)
public class AuCompanyServiceImpl extends GenericServiceImpl<AuCompany, Long> implements AuCompanyService {

    @Autowired
    private AuCompanyRepository companyRepository;

    @Autowired
    private PartyRelationRepository partyRelationRepository;

    @Autowired
    private PartyRelationService partyRelationService;

    @Override
    public AuCompany insert(AuCompany company) {
        //如果用户不手工编号，则系统自动编号
        if (StringUtils.isEmpty(company.getCompanyNo())) {
            company.setCompanyNo(String.valueOf(RandomUtils.nextInt(1000, 9999)));
        }
        if (company.getEnableStatus() == null) {
            company.setEnableStatus(EnableStatus.ENABLE);
            company.setEnableDate(DateTime.now().toDate());
        }
        AuCompany auCompany = companyRepository.save(company);
        //添加公司团体
        partyRelationService.addPartyRelation(auCompany, AuPartyRelationType.ADMINISTRATIVE);
        return auCompany;
    }

    @Override
    public boolean remove(List<Long> ids) {
        ids.forEach(companyId -> {
            AuCompany company = companyRepository.findById(companyId).orElse(null);
            if (company != null) {
                companyRepository.delete(company);
                partyRelationService.removePartyRelation(company);
            }
        });
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
        AuPartyRelation partyRelation = partyRelationRepository.findByPartyIdAndPartyTypeAndRelationType(company.getId(), company.getPartyType(), AuPartyRelationType.ADMINISTRATIVE);
        partyRelation.setName(company.getCompanyName());
        partyRelation.setRemark(company.getRemark());
        partyRelationRepository.saveAndFlush(partyRelation);
        CopyUtils.copyProperties(company, auCompany);
        return companyRepository.saveAndFlush(auCompany);
    }
}
