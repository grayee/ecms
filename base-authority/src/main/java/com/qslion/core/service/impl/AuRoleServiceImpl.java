package com.qslion.core.service.impl;


import com.qslion.core.dao.AuRoleRepository;
import com.qslion.core.entity.AuRole;
import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.core.service.AuRoleService;
import com.qslion.core.service.PartyRelationService;
import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.bean.QueryFilter;
import com.qslion.framework.bean.QueryFilter.Operator;
import com.qslion.framework.service.impl.GenericServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 角色Service类
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Service("roleService")
public class AuRoleServiceImpl extends GenericServiceImpl<AuRole, Long> implements AuRoleService {

    @Autowired
    private AuRoleRepository auRoleRepository;

    @Autowired
    private PartyRelationService partyRelationService;


    @Override
    public Pager<AuRole> findByType(Long typeId, Pageable pageable) {
        QueryFilter queryFilter = new QueryFilter("roleType", Operator.equal, typeId);
        List<QueryFilter> queryFilterList = pageable.getQueryFilters();
        queryFilterList.add(queryFilter);
        return findPage(pageable);
    }

    /**
     * 功能: 添加新记录，同时添加团体、团体关系（如果parentRelId为空则不添加团体关系）
     *
     * @param role     用于添加的VO对象
     * @param parentId 上级节点团体关系主键
     * @return 若添加成功，则返回新添加记录的主键
     */
    @Override
    public AuRole insert(AuRole role, Long parentId) {
        partyRelationService.addPartyRelation(role, AuPartyRelationType.ADMINISTRATIVE);
        return auRoleRepository.save(role);
    }

    @Override
    public AuRole update(AuRole role) {
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

    @Override
    public boolean remove(List<Long> ids) {
        return false;
    }

}
