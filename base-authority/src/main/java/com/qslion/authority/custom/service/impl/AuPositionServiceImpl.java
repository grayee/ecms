
package com.qslion.authority.custom.service.impl;


import com.qslion.authority.core.dao.PartyRelationRepository;
import com.qslion.authority.core.enums.AuPartyRelationType;
import com.qslion.authority.core.service.PartyRelationService;
import com.qslion.authority.custom.dao.AuPositionRepository;
import com.qslion.authority.custom.entity.AuPosition;
import com.qslion.authority.custom.service.AuPositionService;
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
        AuPosition auPosition= positionRepository.save(position);
        //添加团体关系
        partyRelationService.addPartyRelation(auPosition, AuPartyRelationType.ADMINISTRATIVE);
        return auPosition;
    }

    @Override
    public boolean remove(List<Long> ids) {
        ids.forEach(positionId -> {
            AuPosition position = positionRepository.findById(positionId).orElse(null);
            if (position != null) {
                positionRepository.delete(position);
                partyRelationService.removePartyRelation(position);
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
