package com.qslion.core.service.impl;


import com.google.common.collect.Sets;
import com.qslion.core.dao.AuPermissionRepository;
import com.qslion.core.dao.AuRoleRepository;
import com.qslion.core.dao.PartyRelationRepository;
import com.qslion.core.entity.AuPartyRelation;
import com.qslion.core.entity.AuPermission;
import com.qslion.core.entity.AuRole;
import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.core.service.AuRoleService;
import com.qslion.core.service.PartyRelationService;
import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.bean.QueryFilter;
import com.qslion.framework.bean.QueryFilter.Operator;
import com.qslion.framework.enums.EnableStatus;
import com.qslion.framework.service.impl.GenericServiceImpl;
import com.qslion.framework.util.CopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


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
    private AuPermissionRepository auPermissionRepository;
    @Autowired
    private PartyRelationRepository partyRelationRepository;
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
     * @param role 用于添加的VO对象
     * @return 若添加成功，则返回新添加记录的主键
     */
    @Override
    public AuRole insert(AuRole role) {
        AuRole auRole = auRoleRepository.save(role);
        partyRelationService.addPartyRelation(auRole, AuPartyRelationType.ROLE);
        return auRole;
    }

    @Override
    public Boolean grantFuncAuth(AuRole role, List<Long> permissionIds) {
        List<AuPermission> pList = auPermissionRepository.findAllById(permissionIds);
        role.setPermissions(Sets.newHashSet(pList));
        AuRole auRole = auRoleRepository.saveAndFlush(role);
        return auRole.getId() == null;
    }

    @Override
    public Boolean grantDataAuth(AuRole role, List<AuPartyRelation> partyRelations) {
        List<AuPermission> pList = partyRelations.stream().map(partyRelation -> {
            AuPermission permission = auPermissionRepository.findByTypeAndValue(AuPermission.PermitType.DATA, partyRelation.getPartyId().toString());
            if (permission == null) {
                permission = new AuPermission();
                permission.setName(partyRelation.getPartyType() + ":" + partyRelation.getName());
                permission.setValue(partyRelation.getId().toString());
                permission.setType(AuPermission.PermitType.DATA);
                permission.setDescription(partyRelation.getName());
                permission.setEnableStatus(EnableStatus.ENABLE);
                permission.setSystem(false);
                auPermissionRepository.save(permission);
            }
            return permission;
        }).collect(Collectors.toList());

        Set<AuPermission> perms = Sets.newHashSet(pList);
        perms.addAll(role.getPermissions());
        role.setPermissions(perms);
        AuRole auRole = auRoleRepository.saveAndFlush(role);
        return auRole.getId() == null;
    }

    @Override
    public AuRole update(AuRole role) {
        AuRole auRole = auRoleRepository.findById(role.getId()).get();
        AuPartyRelation partyRelation = partyRelationRepository.findByPartyIdAndPartyTypeAndRelationType(role.getId(), role.getPartyType(), AuPartyRelationType.ROLE);
        partyRelation.setName(role.getName());
        partyRelation.setRemark(role.getRemark());
        partyRelationRepository.saveAndFlush(partyRelation);
        CopyUtils.copyProperties(role, auRole);
        return auRoleRepository.saveAndFlush(auRole);
    }


    @Override
    public boolean remove(List<Long> ids) {
        ids.forEach(roleId -> {
            AuRole role = auRoleRepository.findById(roleId).orElse(null);
            if (role != null) {
                auRoleRepository.delete(role);
                partyRelationService.removePartyRelation(role);
            }
        });
        return true;
    }

}
