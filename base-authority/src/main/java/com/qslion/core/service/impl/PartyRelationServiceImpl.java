/**
 *
 */
package com.qslion.core.service.impl;

import com.google.common.collect.Lists;
import com.qslion.core.dao.PartyRelationRepository;
import com.qslion.core.entity.*;
import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.core.enums.AuPartyType;
import com.qslion.core.service.ConnectionRuleService;
import com.qslion.core.service.PartyRelationService;
import com.qslion.core.util.TreeTools;
import com.qslion.framework.bean.TreeNode;
import com.qslion.framework.enums.ResultCode;
import com.qslion.framework.exception.BusinessException;
import com.qslion.framework.service.impl.GenericServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.qslion.core.enums.AuPartyRelationType.ADMINISTRATIVE;

/**
 * 团体关系Service实现
 *
 * @author Gray.Z
 * @date 2018/4/30 19:15.
 */
@Service("partyRelationService")
public class PartyRelationServiceImpl extends GenericServiceImpl<AuPartyRelation, Long> implements
        PartyRelationService {

    private static final Integer nodeStateOpenLevel = 2;
    @Autowired
    private PartyRelationRepository partyRelationRepository;
    @Autowired
    public ConnectionRuleService connectionRuleService;

    @Override
    public boolean addPartyRelation(PartyEntity partyEntity, AuPartyRelationType relationType) {
        Long parentId = partyEntity.getParentId();
        if (parentId != null) {
            AuPartyRelation parentRelation = partyRelationRepository.findById(parentId).orElse(null);
            if (parentRelation != null) {
                AuPartyType curPartyType = parentRelation.getPartyType();
                if (connectionRuleService.checkRule(curPartyType, partyEntity.getPartyType(), relationType)) {
                    AuPartyRelation partyRelation = new AuPartyRelation();
                    partyRelation.setParentId(parentId);
                    partyRelation.setLeaf(true);
                    partyRelation.setPartyId(partyEntity.getId());
                    partyRelation.setPartyType(partyEntity.getPartyType());
                    partyRelation.setName(partyEntity.getPartyName());
                    partyRelation.setRemark(partyEntity.getRemark());
                    partyRelation.setRelationType(relationType);
                    partyRelation.setLevel(parentRelation.getLevel() + 1);

                    AuPartyRelation levelRelation = new AuPartyRelation();
                    levelRelation.setLevel(partyRelation.getLevel());
                    levelRelation.setParentId(parentId);
                    long topLevelCnt = partyRelationRepository.count(Example.of(levelRelation));
                    partyRelation.setOrderCode(topLevelCnt + 1);
                    partyRelationRepository.save(partyRelation);
                    if (parentRelation.getLeaf()) {
                        //更新父节点isLeaf 为false
                        partyRelationRepository.updateLeaf(parentId, false);
                    }
                } else {
                    logger.error("添加团系关系失败，没有找到符合要求的连接规则....");
                    throw new BusinessException(ResultCode.SPECIFIED_QUESTIONED_USER_NOT_EXIST);
                }
            } else {
                throw new BusinessException(ResultCode.PARAMETER_IS_INVALID);
            }
        } else {
            return initRoot(partyEntity, relationType);
        }
        return true;
    }

    @Override
    public boolean removePartyRelation(PartyEntity partyEntity) {
        AuPartyRelationType relationType = AuPartyRelationType.ADMINISTRATIVE;
        if (partyEntity.getPartyType() == AuPartyType.ROLE) {
            relationType = AuPartyRelationType.ROLE;
        }
        AuPartyRelation partyRelation = partyRelationRepository.findByPartyIdAndPartyTypeAndRelationType(partyEntity.getId(), partyEntity.getPartyType(), relationType);
        if (partyRelation != null) {
            if (partyRelation.getLeaf()) {
                partyRelationRepository.delete(partyRelation);
            } else {
                throw new BusinessException(ResultCode.PARAMETER_ERROR, "包含非叶子节点数据，请确认!");
            }
        }
        return true;
    }


    @Override
    public boolean initRoot(PartyEntity partyEntity, AuPartyRelationType relationType) {
        AuPartyRelation partyRelation = new AuPartyRelation();
        partyRelation.setPartyId(partyEntity.getId());
        partyRelation.setPartyType(partyEntity.getPartyType());
        partyRelation.setName(partyEntity.getPartyName());
        partyRelation.setRemark(partyEntity.getRemark());
        partyRelation.setRelationType(relationType);
        partyRelation.setLevel(0);
        partyRelation.setLeaf(true);
        partyRelation.setParentId(null);
        AuPartyRelation levelRelation = new AuPartyRelation();
        levelRelation.setLevel(0);
        long topLevelCnt = partyRelationRepository.count(Example.of(levelRelation));
        partyRelation.setOrderCode(topLevelCnt + 1);
        partyRelationRepository.save(partyRelation);
        return true;
    }

    @Override
    public List<AuPartyRelation> findByRelationType(AuPartyRelationType relationType) {
        return partyRelationRepository.findByRelationType(relationType);
    }

    @Override
    public List<TreeNode> getPartyRelationTree(AuPartyType partyType, Set<AuRole> roleSet) {
        return getTreeNodes(ADMINISTRATIVE, partyType, roleSet);
    }

    @Override
    public List<TreeNode> getAuthedRelationTree(AuPartyType partyType, Set<AuRole> roleSet) {
        List<AuPartyRelation> partyRelationList = partyRelationRepository.findByRelationType(ADMINISTRATIVE);
        if (partyType != null) {
            List<AuPartyType> parentPartyTypes = connectionRuleService.getRuleBySubParty(ADMINISTRATIVE, partyType).stream()
                    .map(AuConnectionRule::getCurPartyType).collect(Collectors.toList());
            partyRelationList = TreeTools.getRelationByPartyType(partyRelationList, parentPartyTypes);
        }
        List<AuPermission> auPermissions = Lists.newArrayList();
        roleSet.forEach(role -> auPermissions.addAll(role.getPermissions().stream().filter(perm->perm.getType()== AuPermission.PermitType.DATA).collect(Collectors.toList())));
        partyRelationList = TreeTools.getPathTreeData(partyRelationList, auPermissions.stream().map(perm -> Long.valueOf(perm.getValue())).collect(Collectors.toList()));
        return getTreeNodes(roleSet, partyRelationList);
    }

    @Override
    public List<TreeNode> getPartyRelationTree(AuPartyRelationType relationType, Set<AuRole> roleSet) {
        List<AuPartyRelation> partyRelationList = partyRelationRepository.findByRelationType(relationType);
        return getTreeNodes(roleSet, partyRelationList);
    }

    private List<TreeNode> getTreeNodes(AuPartyRelationType relationType, AuPartyType partyType, Set<AuRole> roleSet) {
        List<AuPartyRelation> partyRelationList = partyRelationRepository.findByRelationType(relationType);
        if (partyType != null) {
            List<AuPartyType> parentPartyTypes = connectionRuleService.getRuleBySubParty(relationType, partyType).stream()
                    .map(AuConnectionRule::getCurPartyType).collect(Collectors.toList());
            partyRelationList = TreeTools.getRelationByPartyType(partyRelationList, parentPartyTypes);
        }
        return getTreeNodes(roleSet, partyRelationList);
    }

    private List<TreeNode> getTreeNodes(Set<AuRole> roleSet, List<AuPartyRelation> partyRelationList) {
        List<TreeNode> resultList = new ArrayList<>();
        for (AuPartyRelation partyRelation : partyRelationList) {
            if (null == partyRelation.getParentId()) {
                TreeNode rootNode = new TreeNode(partyRelation.getId().toString(), partyRelation.getName());
                //有子节点递归遍历
                if (!partyRelation.getLeaf()) {
                    partyRelationList = partyRelationList.stream().filter(relation ->
                            !partyRelation.getId().equals(relation.getId())).collect(Collectors.toList());
                    List<TreeNode> childrenList = this.getChildTreeNode(partyRelation.getId(), partyRelationList, roleSet);
                    rootNode.setChildren(childrenList);
                }
                rootNode.setState(TreeNode.NodeState.OPEN);
                setPermCheckState(roleSet, rootNode);
                resultList.add(rootNode);
            }
        }
        return resultList;
    }

    private void setPermCheckState(Set<AuRole> roleSet, TreeNode rootNode) {
        if (CollectionUtils.isNotEmpty(roleSet)) {
            List<AuPermission> perms = Lists.newArrayList();
            roleSet.forEach(role -> perms.addAll(role.getPermissions()));
            List<String> permIds = perms.stream().map(AuPermission::getValue).collect(Collectors.toList());
            if (permIds.contains(rootNode.getId())) {
                rootNode.setChecked(true);
            }
        }
    }

    @Override
    public List<TreeNode> getGlobalRelationTree(Set<AuRole> roleSet) {
        List<TreeNode> resultList = new ArrayList<TreeNode>();
        //以所有团体关系类型为根节点
        AuPartyRelationType[] relTypes = AuPartyRelationType.values();
        for (AuPartyRelationType relType : relTypes) {
            resultList.addAll(getTreeNodes(relType, null, roleSet));
        }
        return resultList;
    }

    private List<TreeNode> getChildTreeNode(Long parentId, List<AuPartyRelation> nodeList, Set<AuRole> roleSet) {
        List<TreeNode> resultList = new ArrayList<>();
        for (AuPartyRelation partyRelation : nodeList) {
            if (partyRelation.getParentId() != null && partyRelation.getParentId().equals(parentId)) {
                TreeNode leafNode = new TreeNode(partyRelation.getId().toString(), partyRelation.getName());

                if (!partyRelation.getLeaf()) {
                    nodeList = nodeList.stream().filter(relation ->
                            !partyRelation.getId().equals(relation.getId())).collect(Collectors.toList());
                    List<TreeNode> leafNodes = this.getChildTreeNode(partyRelation.getId(), nodeList, roleSet);
                    leafNode.setChildren(leafNodes);
                }
                if (partyRelation.getLevel() <= nodeStateOpenLevel || partyRelation.getLeaf()) {
                    leafNode.setState(TreeNode.NodeState.OPEN);
                }
                setPermCheckState(roleSet, leafNode);
                resultList.add(leafNode);
            }
        }
        return resultList;
    }


    @Override
    public void delete(AuPartyRelation entity) {
        super.delete(entity);
        if (entity.getParentId() != null) {
            AuPartyRelation parentRelation = partyRelationRepository.findById(entity.getParentId()).orElse(entity);
            AuPartyRelation queryRelation = new AuPartyRelation();
            queryRelation.setParentId(parentRelation.getId());
            long parentCnt = partyRelationRepository.count(Example.of(queryRelation));
            if (parentCnt <= 0) {
                partyRelationRepository.updateLeaf(parentRelation.getId(), true);
            }
        }
    }
}
