/**
 *
 */
package com.qslion.core.service.impl;

import com.qslion.core.dao.AuPartyRepository;
import com.qslion.core.dao.PartyRelationRepository;
import com.qslion.core.entity.AuParty;
import com.qslion.core.entity.AuPartyRelation;
import com.qslion.core.entity.AuRole;
import com.qslion.core.entity.PartyEntity;
import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.core.enums.AuPartyType;
import com.qslion.core.service.ConnectionRuleService;
import com.qslion.core.service.PartyRelationService;
import com.qslion.framework.bean.TreeNode;
import com.qslion.framework.enums.ResultCode;
import com.qslion.framework.exception.BusinessException;
import com.qslion.framework.service.impl.GenericServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * 团体关系Service实现
 *
 * @author Gray.Z
 * @date 2018/4/30 19:15.
 */
@Service("partyRelationService")
public class PartyRelationServiceImpl extends GenericServiceImpl<AuPartyRelation, Long> implements
        PartyRelationService {

    @Autowired
    private AuPartyRepository partyRepository;
    @Autowired
    private PartyRelationRepository partyRelationRepository;
    @Autowired
    public ConnectionRuleService connectionRuleService;

    @Override
    public boolean addPartyRelation(PartyEntity partyEntity, AuPartyRelationType relationType) {
        AuParty party = partyEntity.getAuParty();
        Long parentId = partyEntity.getParentId();
        if (parentId != null) {
            AuPartyRelation parentRelation = partyRelationRepository.findById(parentId).orElse(null);
            if (parentRelation != null) {
                AuPartyType curPartyType = parentRelation.getAuParty().getAuPartyType();
                if (connectionRuleService.checkRule(curPartyType, party.getAuPartyType(), relationType)) {
                    AuPartyRelation partyRelation = new AuPartyRelation();
                    partyRelation.setParentId(parentId);
                    partyRelation.setLeaf(true);
                    partyRelation.setAuParty(party);
                    partyRelation.setName(party.getName());
                    partyRelation.setRemark(party.getRemark());
                    partyRelation.setPartyRelationType(relationType);
                    partyRelation.setLevel(parentRelation.getLevel()+1);

                    AuPartyRelation levelRelation = new AuPartyRelation();
                    levelRelation.setLevel(partyRelation.getLevel());
                    levelRelation.setParentId(parentId);
                    long topLevelCnt = partyRelationRepository.count(Example.of(levelRelation));
                    partyRelation.setOrderCode(topLevelCnt + 1);
                    partyRelationRepository.save(partyRelation);
                    //更新父节点isLeaf 为false
                    partyRelationRepository.updateLeaf(parentId, false);
                } else {
                    logger.error("添加团系关系失败，没有找到符合要求的连接规则....");
                    return false;
                }
            } else {
                throw new BusinessException(ResultCode.PARAMETER_IS_INVALID);
            }
        } else {
            return initRoot(party, relationType);
        }
        return true;
    }

    public void addPartyRelation(AuPartyRelation paramAuPartyRelationVo) {
        // TODO Auto-generated method stub

    }


    @Override
    public boolean initRoot(AuParty party, AuPartyRelationType relationType) {
        AuPartyRelation partyRelation = new AuPartyRelation();
        partyRelation.setAuParty(party);
        partyRelation.setName(party.getName());
        partyRelation.setRemark(party.getRemark());
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
    public List<TreeNode> getPartyRelationTree(AuPartyRelationType relationType) {
        return getPartyRelationTree(relationType, null);
    }

    @Override
    public List<TreeNode> getPartyRelationTree(AuPartyRelationType relationType, Set<AuRole> roleSet) {
        return getTreeNodes(relationType, roleSet);
    }

    private List<TreeNode> getTreeNodes(AuPartyRelationType relationType, Set<AuRole> roleSet) {
        List<TreeNode> resultList = new ArrayList<>();
        List<AuPartyRelation> partyRelationList = partyRelationRepository.findByPartyRelationType(relationType);
        for (AuPartyRelation partyRelation : partyRelationList) {
            //从根节点开始查找，如果PARENTCODE与ID相同则为根节点
            if (null == partyRelation.getParentId()) {
                TreeNode rootNode = new TreeNode(partyRelation.getId().toString(),partyRelation.getName());
                //有子节点递归遍历
                if (!partyRelation.isLeaf()) {
                    partyRelationList = partyRelationList.stream().filter(relation ->
                            !partyRelation.getId().equals(relation.getId())).collect(Collectors.toList());
                    List<TreeNode> childrenList = this.getChildTreeNode(partyRelation.getId(), partyRelationList, roleSet);
                    rootNode.setChildren(childrenList);
                }
                resultList.add(rootNode);
            }
        }
        return resultList;
    }

    public List<TreeNode> getGlobalRelationTree(Set<AuRole> roleSet) {
        List<TreeNode> resultList = new ArrayList<TreeNode>();
        //以所有团体关系类型为根节点
        AuPartyRelationType[] relTypes = AuPartyRelationType.values();
        for (AuPartyRelationType relType : relTypes) {
            resultList.addAll(getTreeNodes(relType, roleSet));
        }
        return resultList;
    }

    private List<TreeNode> getChildTreeNode(Long parentId, List<AuPartyRelation> nodeList, Set<AuRole> roleSet) {
        List<TreeNode> resultList = new ArrayList<>();
        for (AuPartyRelation partyRelation : nodeList) {
            if (partyRelation.getParentId() != null && partyRelation.getParentId().equals(parentId) &&
                    !partyRelation.getParentId().equals(partyRelation.getId())) {
                TreeNode leafNode = new TreeNode(partyRelation.getId().toString(), partyRelation.getName());

                AuPartyType partyType = partyRelation.getAuParty().getAuPartyType();
                if (partyType == AuPartyType.ROLE) {
                    for (AuRole role : roleSet) {
                        if (role.getId().toString().equals(leafNode.getId())) {
                            leafNode.setText(role.getName() + "(已关联)");
                            leafNode.setChecked(true);
                        }
                    }
                }

                if (!partyRelation.isLeaf()) {
                    nodeList = nodeList.stream().filter(relation ->
                            !partyRelation.getId().equals(relation.getId())).collect(Collectors.toList());
                    List<TreeNode> leafNodes = this.getChildTreeNode(partyRelation.getId(), nodeList,roleSet);
                    leafNode.setChildren(leafNodes);
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

    public List<TreeNode> getRelationTreeByPartyTypes(List<AuPartyType> partyTypes) {
        // TODO Auto-generated method stub
        List<TreeNode> resultList = new ArrayList<TreeNode>();
        List<AuPartyRelation> relationList = new ArrayList<AuPartyRelation>();
        for (AuPartyType partyType : partyTypes) {
            System.out.println(partyType.getName() + "==>>>>>");
            //relationList.addAll(partyRelationRepository.findByPartytypeId(partyType.getId()));
        }
        for (AuPartyRelation relation : relationList) {
            if (null == relation.getParentId() || "".equals(relation.getParentId())) {
                // TreeNode rootNode = new TreeNode(relation.getId(), relation.getName());

                //有子节点递归遍历
                // if (relation.getIsLeaf().equals("0")) {
                //List<TreeNode> childrenList = this.getChildTreeNode(relation.getId(), relationList, false);
                //rootNode.setChildren(childrenList);
                //}
                //resultList.add(rootNode);
            } else {

            }
        }
        return resultList;
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
}
