/**
 *
 */
package com.qslion.authority.core.service.impl;

import com.google.common.collect.Lists;
import com.qslion.authority.core.dao.AuOrgRelationRepository;
import com.qslion.authority.core.entity.*;
import com.qslion.authority.core.enums.AuOrgType;
import com.qslion.authority.core.enums.AuOrgRelationType;
import com.qslion.authority.core.service.AuOrgRelationService;
import com.qslion.authority.core.service.ConnectionRuleService;
import com.qslion.authority.core.util.TreeTools;
import com.qslion.framework.bean.TreeNode;
import com.qslion.framework.enums.ResultCode;
import com.qslion.framework.exception.BusinessException;
import com.qslion.framework.service.impl.GenericServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 团体关系Service实现
 *
 * @author Gray.Z
 * @date 2018/4/30 19:15.
 */
@Service("auOrgRelationService")
@Transactional(value = "transactionManager", rollbackFor = Exception.class)
public class AuOrgRelationServiceImpl extends GenericServiceImpl<AuOrgRelation, Long> implements
        AuOrgRelationService {

    @Autowired
    private AuOrgRelationRepository auOrgRelationRepository;
    @Autowired
    public ConnectionRuleService connectionRuleService;

    @Override
    public boolean addOrgRelation(IOrg org, AuOrgRelationType relationType) {
        Long parentId = org.getParentRelId();
        if (parentId != null) {
            AuOrgRelation orgRelation = auOrgRelationRepository.findByOrgIdAndOrgTypeAndRelationType(org.getOrgId(), org.getOrgType(), relationType);
            if (orgRelation != null) {
                this.updateOrgRelation(org, orgRelation);
            } else {
                AuOrgRelation parentRelation = auOrgRelationRepository.findById(parentId).orElse(null);
                if (parentRelation != null) {
                    AuOrgType curPartyType = parentRelation.getOrgType();
                    if (connectionRuleService.checkRule(curPartyType, org.getOrgType(), relationType)) {
                        orgRelation = new AuOrgRelation();
                        orgRelation.setParentId(parentId);
                        orgRelation.setLeaf(true);
                        orgRelation.setOrgId(org.getOrgId());
                        orgRelation.setOrgType(org.getOrgType());
                        orgRelation.setName(org.getOrgName());
                        orgRelation.setRemark(org.getRemark());
                        orgRelation.setRelationType(relationType);
                        orgRelation.setLevel(parentRelation.getLevel() + 1);

                        AuOrgRelation levelRelation = new AuOrgRelation();
                        levelRelation.setLevel(orgRelation.getLevel());
                        levelRelation.setParentId(parentId);
                        Integer topLevelCnt = new BigDecimal(auOrgRelationRepository.count(Example.of(levelRelation))).intValue();
                        orgRelation.setOrderCode(topLevelCnt + 1);
                        auOrgRelationRepository.save(orgRelation);
                        if (parentRelation.getLeaf()) {
                            //更新父节点isLeaf 为false
                            auOrgRelationRepository.updateLeaf(parentId, false);
                        }
                    } else {
                        logger.error("添加团系关系失败，没有找到符合要求的连接规则....");
                        throw new BusinessException(ResultCode.SPECIFIED_QUESTIONED_USER_NOT_EXIST);
                    }
                } else {
                    throw new BusinessException(ResultCode.PARAMETER_IS_INVALID);
                }
            }
        } else {
            return initRoot(org, relationType);
        }
        return true;
    }

    @Override
    public boolean updateOrgRelation(IOrg org, AuOrgRelationType relationType) {
        AuOrgRelation orgRelation = auOrgRelationRepository.findByOrgIdAndOrgTypeAndRelationType(org.getOrgId(), org.getOrgType(), relationType);
        updateOrgRelation(org, orgRelation);
        return true;
    }

    private void updateOrgRelation(IOrg org, AuOrgRelation orgRelation) {
        Long newPid = org.getParentRelId();
        Long oldPid = orgRelation.getParentId();
        if (newPid != null && !newPid.equals(oldPid)) {
            orgRelation.setParentId(newPid);
        }
        orgRelation.setName(org.getOrgName());
        orgRelation.setRemark(org.getRemark());
        auOrgRelationRepository.saveAndFlush(orgRelation);
    }

    @Override
    public boolean removeOrgRelation(IOrg org, AuOrgRelationType relationType) {
        AuOrgRelation orgRelation = auOrgRelationRepository.findByOrgIdAndOrgTypeAndRelationType(org.getOrgId(), org.getOrgType(), relationType);
        if (orgRelation != null) {
            if (orgRelation.getLeaf()) {
                auOrgRelationRepository.delete(orgRelation);
            } else {
                throw new BusinessException(ResultCode.PARAMETER_ERROR, "包含非叶子节点数据，请确认!");
            }
        }
        return true;
    }


    @Override
    public boolean initRoot(IOrg org, AuOrgRelationType relationType) {
        AuOrgRelation orgRelation = new AuOrgRelation();
        orgRelation.setOrgId(org.getOrgId());
        orgRelation.setOrgType(org.getOrgType());
        orgRelation.setName(org.getOrgName());
        orgRelation.setRemark(org.getRemark());
        orgRelation.setRelationType(relationType);
        orgRelation.setLevel(0);
        orgRelation.setLeaf(true);
        orgRelation.setParentId(null);
        AuOrgRelation levelRelation = new AuOrgRelation();
        levelRelation.setLevel(0);
        Integer topLevelCnt = new BigDecimal(auOrgRelationRepository.count(Example.of(levelRelation))).intValue();
        orgRelation.setOrderCode(topLevelCnt + 1);
        auOrgRelationRepository.save(orgRelation);
        return true;
    }

    @Override
    public List<AuOrgRelation> findByRelationType(AuOrgRelationType relationType) {
        return auOrgRelationRepository.findByRelationType(relationType);
    }

    @Override
    public AuOrgRelation findByOrg(AuOrgRelationType relationType, AuOrgType orgType, Long orgId) {
        return auOrgRelationRepository.findByOrgIdAndOrgTypeAndRelationType(orgId, orgType, relationType);
    }

    @Override
    public List<TreeNode> getTargetTree(AuOrgType orgType, Set<AuRole> roleSet) {
        List<AuOrgRelation> relations = auOrgRelationRepository.findByRelationType(AuOrgRelationType.ADMINISTRATIVE);
        List<Long> filteredIds = relations.stream().filter(r -> orgType == r.getOrgType())
                .map(AuOrgRelation::getId).collect(Collectors.toList());
        relations = TreeTools.getTargetTreePath(relations, filteredIds);
        List<String> permIds = getCheckedPerms(roleSet);
        return TreeTools.getTreeList(relations, permIds);
    }

    @Override
    public List<TreeNode> getGrantedDataTree(AuOrgType orgType, Set<AuRole> roleSet) {
        List<AuOrgRelation> relations = auOrgRelationRepository.findByRelationType(AuOrgRelationType.ADMINISTRATIVE);
        if (orgType != null) {
            List<AuOrgType> pTypes = connectionRuleService.getRuleBySubOrg(AuOrgRelationType.ADMINISTRATIVE, orgType).stream()
                    .map(AuConnectionRule::getCurOrgType).collect(Collectors.toList());
            List<Long> filteredIds = relations.stream().filter(r -> pTypes.contains(r.getOrgType())).map(AuOrgRelation::getId).collect(Collectors.toList());
            relations = TreeTools.getTargetTreePath(relations, filteredIds);
        }
        List<AuPermission> auPermissions = Lists.newArrayList();
        roleSet.forEach(role -> auPermissions.addAll(role.getPermissions().stream()
                .filter(perm -> perm.getType() == AuPermission.PermitType.DATA).collect(Collectors.toList())));
        relations = TreeTools.getTargetTreePath(relations, auPermissions.stream().map(perm -> Long.valueOf(perm.getValue())).collect(Collectors.toList()));
        List<String> permIds = getCheckedPerms(roleSet);
        return TreeTools.getTreeList(relations, permIds);
    }

    @Override
    public List<TreeNode> getOrgRelationTree(AuOrgRelationType relationType, AuOrgType orgType, Set<AuRole> roleSet) {
        return getTreeNodes(relationType, orgType, roleSet);
    }

    private List<TreeNode> getTreeNodes(AuOrgRelationType relationType, AuOrgType orgType, Set<AuRole> roleSet) {
        List<AuOrgRelation> relations = auOrgRelationRepository.findByRelationType(relationType);

        if (orgType != null) {
            List<AuOrgType> pTypes = connectionRuleService.getRuleBySubOrg(relationType, orgType).stream()
                    .map(AuConnectionRule::getCurOrgType).collect(Collectors.toList());
            List<Long> filteredIds = relations.stream().filter(r -> pTypes.contains(r.getOrgType()))
                    .map(AuOrgRelation::getId).collect(Collectors.toList());
            relations = TreeTools.getTargetTreePath(relations, filteredIds);
        }

        List<String> permIds = getCheckedPerms(roleSet);
        if (relationType == AuOrgRelationType.ROLE) {
            List<Long> roleIds = roleSet.stream().map(AuRole::getId).collect(Collectors.toList());
            permIds = relations.stream().filter(r -> roleIds.contains(r.getOrgId()))
                    .map(r -> String.valueOf(r.getId())).collect(Collectors.toList());
        }
        return TreeTools.getTreeList(relations, permIds);
    }

    private List<String> getCheckedPerms(Set<AuRole> roleSet) {
        List<String> permIds = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(roleSet)) {
            List<AuPermission> perms = Lists.newArrayList();
            roleSet.forEach(role -> perms.addAll(role.getPermissions()));
            permIds = perms.stream().map(AuPermission::getValue).collect(Collectors.toList());
        }
        return permIds;
    }

    @Override
    public List<TreeNode> getGlobalRelationTree(Set<AuRole> roleSet) {
        List<TreeNode> resultList = new ArrayList<TreeNode>();
        //以所有团体关系类型为根节点
        AuOrgRelationType[] relTypes = AuOrgRelationType.values();
        for (AuOrgRelationType relType : relTypes) {
            resultList.addAll(getTreeNodes(relType, null, roleSet));
        }
        return resultList;
    }

    @Override
    public void delete(AuOrgRelation entity) {
        super.delete(entity);
        if (entity.getParentId() != null) {
            AuOrgRelation parentRelation = auOrgRelationRepository.findById(entity.getParentId()).orElse(entity);
            AuOrgRelation queryRelation = new AuOrgRelation();
            queryRelation.setParentId(parentRelation.getId());
            long parentCnt = auOrgRelationRepository.count(Example.of(queryRelation));
            if (parentCnt <= 0) {
                auOrgRelationRepository.updateLeaf(parentRelation.getId(), true);
            }
        }
    }
}
