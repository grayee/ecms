package com.qslion.authority.core.service.impl;


import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.qslion.authority.core.dao.AuPermissionRepository;
import com.qslion.authority.core.dao.AuResourceRepository;
import com.qslion.authority.core.dao.AuRoleRepository;
import com.qslion.authority.core.dao.AuOrgRelationRepository;
import com.qslion.authority.core.entity.AuOrgRelation;
import com.qslion.authority.core.entity.AuPermission;
import com.qslion.authority.core.entity.AuResource;
import com.qslion.authority.core.entity.AuRole;
import com.qslion.authority.core.enums.AuOrgRelationType;
import com.qslion.authority.core.service.AuOrgRelationService;
import com.qslion.authority.core.service.AuRoleService;
import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.bean.QueryFilter;
import com.qslion.framework.bean.QueryFilter.Operator;
import com.qslion.framework.enums.EnableStatus;
import com.qslion.framework.service.impl.GenericServiceImpl;
import com.qslion.framework.util.CopyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(value = "transactionManager", rollbackFor = Exception.class)
public class AuRoleServiceImpl extends GenericServiceImpl<AuRole, Long> implements AuRoleService {

    @Autowired
    private AuRoleRepository auRoleRepository;
    @Autowired
    private AuPermissionRepository auPermissionRepository;
    @Autowired
    private AuOrgRelationRepository auOrgRelationRepository;
    @Autowired
    private AuOrgRelationService auOrgRelationService;
    @Autowired
    private AuResourceRepository auResourceRepository;


    @Override
    public Pager<AuRole> findByType(Long typeId, Pageable pageable) {
        QueryFilter queryFilter = new QueryFilter("roleType", Operator.EQUAL, typeId);
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
        auOrgRelationService.addOrgRelation(auRole, AuOrgRelationType.ROLE);
        return auRole;
    }

    @Override
    public Boolean grantFuncAuth(AuRole role, List<String> permAndResIds) {
        List<Long> permissionIds = Lists.newArrayList();
        List<Long> resIds = Lists.newArrayList();
        permAndResIds.forEach(id -> {
            String[] pars = id.split("@");
            if (Boolean.valueOf(pars[1])) {
                permissionIds.add(Long.valueOf(pars[0]));
            } else {
                Long resId = Long.valueOf(pars[0]);
                resIds.add(resId);
            }
        });
        List<AuResource> resources = auResourceRepository.findAllById(resIds);
        if (CollectionUtils.isNotEmpty(resources)) {
            resources.forEach(res -> {
                Set<AuPermission> perms = res.getPermissions();
                if (CollectionUtils.isEmpty(perms)) {
                    AuPermission perm = new AuPermission();
                    perm.setName("默认权限");
                    perm.setValue("common:view");
                    perm.setDescription("系统默认权限");
                    perm.setSystem(true);
                    perm.setEnableStatus(EnableStatus.ENABLE);
                    perm.setResource(res);
                    auPermissionRepository.save(perm);
                    perms.add(perm);
                }
                permissionIds.addAll(perms.stream().map(AuPermission::getId).collect(Collectors.toList()));
            });
        }
        //删除已有权限
        auPermissionRepository.deleteAll(role.getPermissions());
        List<AuPermission> pList = auPermissionRepository.findAllById(permissionIds);
        role.setPermissions(Sets.newHashSet(pList));
        AuRole auRole = auRoleRepository.saveAndFlush(role);
        return auRole.getId() == null;
    }

    @Override
    public Boolean grantDataAuth(AuRole role, List<AuOrgRelation> partyRelations) {
        List<AuPermission> pList = partyRelations.stream().map(partyRelation -> {
            AuPermission permission = auPermissionRepository.findByTypeAndValue(AuPermission.PermitType.DATA, partyRelation.getId().toString());
            if (permission == null) {
                permission = new AuPermission();
                permission.setName(partyRelation.getOrgType() + ":" + partyRelation.getName());
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
        AuOrgRelation partyRelation = auOrgRelationRepository.findByOrgIdAndOrgTypeAndRelationType(role.getId(), role.getOrgType(), AuOrgRelationType.ROLE);
        partyRelation.setName(role.getName());
        partyRelation.setRemark(role.getRemark());
        auOrgRelationRepository.saveAndFlush(partyRelation);
        CopyUtils.copyProperties(role, auRole);
        return auRoleRepository.saveAndFlush(auRole);
    }


    @Override
    public boolean remove(List<Long> ids) {
        ids.forEach(roleId -> {
            AuRole role = auRoleRepository.findById(roleId).orElse(null);
            if (role != null) {
                auRoleRepository.delete(role);
                auOrgRelationService.removeOrgRelation(role, AuOrgRelationType.ROLE);
            }
        });
        return true;
    }

}
