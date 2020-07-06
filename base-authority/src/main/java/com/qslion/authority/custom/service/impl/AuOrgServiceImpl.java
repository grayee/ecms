package com.qslion.authority.custom.service.impl;

import com.qslion.authority.core.dao.AuOrgRelationRepository;
import com.qslion.authority.core.entity.AuOrgRelation;
import com.qslion.authority.core.entity.IOrg;
import com.qslion.authority.core.enums.AuOrgRelationType;
import com.qslion.authority.core.service.AuOrgRelationService;
import com.qslion.framework.entity.BaseEntity;
import com.qslion.framework.service.impl.GenericServiceImpl;
import com.qslion.framework.util.CopyUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * 组织Service类
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
public class AuOrgServiceImpl<T extends BaseEntity<ID>, ID extends Serializable> extends GenericServiceImpl<T, ID> {

    @Autowired
    private AuOrgRelationService auOrgRelationService;
    @Autowired
    private AuOrgRelationRepository auOrgRelationRepository;

    @Override
    public T insert(T org) {
        T t = this.save(org);
        if (t != null) {
            //添加行政关系
            auOrgRelationService.addOrgRelation((IOrg) t, AuOrgRelationType.ADMINISTRATIVE);
        }
        return t;
    }

    @Override
    public void deleteByIds(List<ID> ids) {
        ids.forEach(id -> {
            T t = this.findById(id);
            if (t != null) {
                this.delete(t);
                auOrgRelationService.removeOrgRelation((IOrg) t);
            }
        });
    }

    @Override
    public T update(T entity) {
        T t = this.findById(entity.getId());
        IOrg org = (IOrg) t;
        AuOrgRelation orgRelation = auOrgRelationRepository.findByOrgIdAndOrgTypeAndRelationType(org.getOrgId(), org.getOrgType(), AuOrgRelationType.ADMINISTRATIVE);
        orgRelation.setName(org.getOrgName());
        orgRelation.setRemark(org.getRemark());
        auOrgRelationRepository.saveAndFlush(orgRelation);
        CopyUtils.copyProperties(entity, t);
        return this.save(t);
    }
}
