
package com.qslion.custom.service.impl;


import com.qslion.core.dao.AuPartyRepository;
import com.qslion.core.dao.PartyRelationRepository;
import com.qslion.core.entity.AuParty;
import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.core.service.PartyRelationService;
import com.qslion.custom.dao.AuPositionRepository;
import com.qslion.custom.entity.AuDepartment;
import com.qslion.custom.entity.AuPosition;
import com.qslion.custom.service.AuPositionService;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.service.impl.GenericServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 职位Service类
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Service("positionService")
public class AuPositionServiceImpl extends GenericServiceImpl<AuPosition, Long> implements AuPositionService {
    @Autowired
    private AuPositionRepository positionRepository;
    @Autowired
    private AuPartyRepository partyRepository;
    @Autowired
    private PartyRelationRepository partyRelationRepository;
    @Autowired
    private PartyRelationService partyRelationService;

    /**
     * 添加新记录，同时添加团体、团体关系（如果parentRelId为空则不添加团体关系）
     *
     * @param position 用于添加的VO对象
     * @return 若添加成功，则返回新添加记录的主键
     */
    public AuPosition insert(AuPosition position) {
        //如果用户不手工编号，则系统自动编号
        if (StringUtils.isEmpty(position.getPositionNo())) {
            position.setPositionNo(String.valueOf(RandomUtils.nextInt(1000, 9999)));
        }
        //添加团体关系
        partyRelationService.addPartyRelation(position, AuPartyRelationType.ADMINISTRATIVE);
        return positionRepository.save(position);
    }

    @Override
    public AuPosition findByParty(AuParty party) {
        return positionRepository.findByAuParty(party);
    }

    @Override
    public boolean remove(List<Long> ids) {
        ids.forEach(positionId -> {
            AuPosition position = positionRepository.findById(positionId).orElse(null);
            if (position != null) {
                positionRepository.delete(position);
                partyRelationService.removePartyRelation(position.getAuParty());
            }
        });
        return true;
    }


    /**
     * 更新单条记录，同时调用接口更新相应的团体、团体关系记录
     *
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public AuPosition update(AuPosition vo) {
        AuParty party = partyRepository.getOne(vo.getId());
        party.setName(vo.getPositionName());//团体名称
        party.setRemark(vo.getRemark());//备注
        //vo.setAuParty(party);
//        positionDao.clear();
        //      boolean flag = positionDao.update(vo);
        //    AuPartyRelation relation = partyRelationDao.findByPartyId(vo.getId(), GlobalConstants.getRelTypeComp());
        //  relation.setName(vo.getPositionName());
        //partyRelationDao.update(relation);
        //RmLogHelper.log(TABLE_LOG_TYPE_NAME, "更新了" + sum + "条记录,id=" + String.valueOf(vo.getId()));
        return null;
    }


}
