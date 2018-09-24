
package com.qslion.custom.service.impl;


import com.qslion.core.dao.AuPartyRepository;
import com.qslion.core.dao.PartyRelationRepository;
import com.qslion.core.entity.AuParty;
import com.qslion.custom.dao.AuPositionRepository;
import com.qslion.custom.entity.AuPosition;
import com.qslion.custom.service.AuPositionService;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.service.impl.GenericServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：authority 类名称：PositionServiceImpl 类描述： 创建人：Administrator 创建时间：2011-8-8 下午03:10:43
 * 修改人：Administrator 修改时间：2011-8-8 下午03:10:43 修改备注：
 */
@Service("positionService")
public class AuPositionServiceImpl extends GenericServiceImpl<AuPosition, Long> implements AuPositionService {
    @Autowired
    private AuPositionRepository positionDao;
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
    public String insert(AuPosition vo, String parentRelId) {
        AuParty auParty = new AuParty();
        //auParty.setAuPartyType(partyTypeDao.get(GlobalConstants.getPartyTypePosi()));
        auParty.setName(vo.getPositionName());
        auParty.setRemark(vo.getRemark());
        //auParty.setEnableStatus(vo.getEnableStatus());
        auParty.setIsInherit("1");
        auParty.setIsReal("1");
        //vo.setAuParty(auParty);
        //如果用户不手工编号，则系统自动编号
        if (StringUtils.isEmpty(vo.getPositionNo())) {
            //vo.setPositionNo(auParty.getId());
        }
        String partyId = "";//positionDao.save(vo);
        //添加团体关系
        if (StringUtils.isNotEmpty(parentRelId) && StringUtils.isNotEmpty(partyId)) {
            String relTypeId = "";//GlobalConstants.getRelTypeComp();
           // OrgHelper.addAuPartyRelation(partyId, parentRelId, relTypeId);
        }
        return partyId;
    }

    /**
     * 删除单条记录，同时删除团体关系及相关的权限记录
     *
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public boolean delete(String partyRelationId) {
       // String partyid = OrgHelper.getPartyIDByRelationID(partyRelationId);
        boolean flag = false;//positionDao.delete(partyid);
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
        boolean flag = false;//positionDao.delete(id);
        /*for(int i=0; i<id.length; i++) {
		    OrgHelper.deleteParty(id[i]);//调用接口删除相应的团体、团体关系、帐户、权限等记录
		}
        //RmLogHelper.log(TABLE_LOG_TYPE_NAME, "删除了" + sum + "条记录,id=" + String.valueOf(id));
*/
        return flag;
    }

    public Pager<AuPosition> findByPager(Pager<AuPosition> pager) {
        return null;
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
