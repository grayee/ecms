/**
 *
 */
package com.qslion.core.service.impl;

import com.qslion.core.dao.PartyRelationRepository;
import com.qslion.core.entity.*;
import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.core.enums.AuPartyType;
import com.qslion.core.service.ConnectionRuleService;
import com.qslion.core.service.PartyRelationService;
import com.qslion.framework.bean.TreeNode;
import com.qslion.framework.enums.ResultCode;
import com.qslion.framework.exception.BusinessException;
import com.qslion.framework.service.impl.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

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
                    partyRelation.setName(partyEntity.getPartyName());
                    partyRelation.setRemark(partyEntity.getRemark());
                    partyRelation.setPartyRelationType(relationType);
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
        AuPartyRelation partyRelation = partyRelationRepository.findByPartyIdAndPartyRelationType(partyEntity.getId(),AuPartyRelationType.ADMINISTRATIVE);
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
        partyRelation.setPartyRelationType(relationType);
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
    public List<TreeNode> getPartyRelationTree(AuPartyType partyType) {
        return getPartyRelationTree(AuPartyRelationType.ADMINISTRATIVE, partyType);
    }

    @Override
    public List<TreeNode> getPartyRelationTree(AuPartyRelationType relationType, AuPartyType partyType) {
        return getTreeNodes(relationType, partyType, null);
    }

    @Override
    public List<TreeNode> getPartyRelationTree(AuPartyRelationType relationType, Set<AuRole> roleSet) {
        return getTreeNodes(relationType, null, roleSet);
    }

    private List<TreeNode> getTreeNodes(AuPartyRelationType relationType, AuPartyType partyType, Set<AuRole> roleSet) {
        List<TreeNode> resultList = new ArrayList<>();
        List<AuPartyRelation> partyRelationList = partyRelationRepository.findByPartyRelationType(relationType);
        if (partyType != null) {
            List<AuPartyType> parentPartyTypes = connectionRuleService.findAll().stream()
                    .filter(auConnectionRule -> auConnectionRule.getSubPartyType() == partyType)
                    .map(AuConnectionRule::getCurPartyType).collect(Collectors.toList());
            partyRelationList = partyRelationList.stream().filter(auPartyRelation ->
                    parentPartyTypes.contains(auPartyRelation.getPartyType())).collect(Collectors.toList());
        }
        for (AuPartyRelation partyRelation : partyRelationList) {
            //从根节点开始查找，如果PARENTCODE与ID相同则为根节点
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
                resultList.add(rootNode);
            }
        }
        return resultList;
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
            if (partyRelation.getParentId() != null && partyRelation.getParentId().equals(parentId) &&
                    !partyRelation.getParentId().equals(partyRelation.getId())) {
                TreeNode leafNode = new TreeNode(partyRelation.getId().toString(), partyRelation.getName());

                AuPartyType partyType = partyRelation.getPartyType();
                if (partyType == AuPartyType.ROLE) {
                    for (AuRole role : roleSet) {
                        if (role.getId().toString().equals(leafNode.getId())) {
                            leafNode.setText(role.getName() + "(已关联)");
                            leafNode.setChecked(true);
                        }
                    }
                }

                if (!partyRelation.getLeaf()) {
                    nodeList = nodeList.stream().filter(relation ->
                            !partyRelation.getId().equals(relation.getId())).collect(Collectors.toList());
                    List<TreeNode> leafNodes = this.getChildTreeNode(partyRelation.getId(), nodeList, roleSet);
                    leafNode.setChildren(leafNodes);
                }
                if (partyRelation.getLevel() <= nodeStateOpenLevel || partyRelation.getLeaf()) {
                    leafNode.setState(TreeNode.NodeState.OPEN);
                }
                resultList.add(leafNode);
            }
        }
        return resultList;
    }

    public boolean hasCustomRoot(String relTypeId) {
        // TODO Auto-generated method stub
        return false;//partyRelationRepository.findByProperty("auPartyRelationType.id", relTypeId).size() > 0;
    }

    public AuPartyRelation getRelationByPartyId(String partyId, String relTypeId) {
        // TODO Auto-generated method stub
        return null;//partyRelationRepository.findByPartyId(partyId, relTypeId);
    }

    public List<AuPartyRelation> queryPartyRelation(
            AuPartyRelation auPartyRelationVo) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<TreeNode> getPartyDetailRelationTree(String partyId, String relationTypeId) {
        // TODO Auto-generated method stub
        List<TreeNode> resultList = new ArrayList<TreeNode>();
        List<AuPartyRelation> partyRelationList = null;//partyRelationRepository.getSinglePartyRelation(partyId, relationTypeId);
        for (AuPartyRelation partyRelation : partyRelationList) {
            //从根节点开始查找，如果PARENTCODE与ID相同则为根节点
            if (null == partyRelation.getParentId() || "".equals(partyRelation.getParentId())) {
                //TreeNode rootNode = new TreeNode(partyRelation.getId(), partyRelation.getName());

                //有子节点递归遍历
                // if (partyRelation.getIsLeaf().equals("0")) {
                //List<TreeNode> childrenList = this.getChildTreeNode(partyRelation.getId(), partyRelationList, false);
                //rootNode.setChildren(childrenList);
                //}
                //resultList.add(rootNode);
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
