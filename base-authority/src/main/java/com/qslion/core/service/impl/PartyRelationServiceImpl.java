/**
 *
 */
package com.qslion.core.service.impl;

import com.qslion.core.dao.AuPartyRepository;
import com.qslion.core.dao.PartyRelationRepository;
import com.qslion.core.entity.AuParty;
import com.qslion.core.entity.AuPartyRelation;
import com.qslion.core.entity.AuUser;
import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.core.enums.AuPartyType;
import com.qslion.core.service.ConnectionRuleService;
import com.qslion.core.service.PartyRelationService;
import com.qslion.core.util.TreeNode;
import com.qslion.framework.enums.ResultCode;
import com.qslion.framework.exception.BusinessException;
import com.qslion.framework.service.impl.GenericServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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
    public boolean addPartyRelation(Long parentId, AuParty party, AuPartyRelationType relationType) {
        if (parentId != null) {
            AuPartyRelation parentRelation = partyRelationRepository.findById(parentId).orElse(null);
            if (parentRelation != null) {
                AuPartyType curPartyType = parentRelation.getAuParty().getAuPartyType();
                if (connectionRuleService.checkRule(curPartyType, party.getAuPartyType(), relationType)) {
                    AuPartyRelation partyRelation = new AuPartyRelation();
                    partyRelation.setParentId(parentId);
                    partyRelation.setLeaf(true);
                    //更新父节点isLeaf 为false
                    partyRelationRepository.updateLeaf(parentId, false);

                    partyRelation.setAuParty(party);
                    partyRelation.setName(party.getName());
                    partyRelation.setRemark(party.getRemark());
                    partyRelation.setAuPartyRelationType(relationType);
                    partyRelationRepository.save(partyRelation);
                } else {
                    logger.error("添加团系关系失败，没有找到符合要求的连接规则....");
                    return false;
                }
            } else {
                throw  new BusinessException(ResultCode.PARAMETER_IS_INVALID);
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
        partyRelation.setAuPartyRelationType(relationType);
        partyRelationRepository.save(partyRelation);
        return true;
    }

    public List<AuPartyRelation> queryPartyRelation(String relationTypeId) {
        // TODO Auto-generated method stub
        return null;//partyRelationRepository.findByProperty("auPartyRelationType.id", relationTypeId);
    }

    public List<AuPartyRelation> getPartyRelationsByTypeId(String typeId) {
        // TODO Auto-generated method stub
        return null;//this.partyRelationRepository.findByProperty("auPartyRelationType.id", typeId);
    }

    public List<TreeNode> getPartyRelationTree(String relationTypeId, boolean hasHref) {
        return getPartyRelationTree(relationTypeId, hasHref, null);
    }

    public List<TreeNode> getPartyRelationTree(String relationTypeId, boolean hasHref, Map<String, Object> map) {
        // TODO Auto-generated method stub
        List<TreeNode> resultList = new ArrayList<TreeNode>();
        List<AuPartyRelation> partyRelationList = queryPartyRelation(relationTypeId);
        for (AuPartyRelation partyRelation : partyRelationList) {
            //从根节点开始查找，如果PARENTCODE与ID相同则为根节点
            if (null == partyRelation.getParentId() || "".equals(partyRelation.getParentId())) {
                //TreeNode rootNode = new TreeNode(partyRelation.getId(), partyRelation.getName());

                if (hasHref) {
                   /* if (relationTypeId.equals(GlobalConstants.getRelTypeComp())) {
                        //行政关系
                        rootNode.setUrl("admin/relation/view.jspx?id=" + partyRelation.getAuParty().getId() + "&rid=" + partyRelation.getId());
                    } else if (relationTypeId.equals(GlobalConstants.getRelTypeRole())) {
                        //角色关系
                        rootNode.setUrl("admin/role/view.jspx?id=" + partyRelation.getAuParty().getId() + "&rid=" + partyRelation.getId());
                        rootNode.setNocheck(true);
                    }*/

                }
                //有子节点递归遍历
                //if (partyRelation.getIsLeaf().equals("0")) {
                // List<TreeNode> childrenList = this.getChildTreeNode(partyRelation.getId(), partyRelationList, hasHref, map);
                // rootNode.setChildren(childrenList);
                // }
                // resultList.add(rootNode);
            }
        }
        return resultList;
    }

    public List<TreeNode> getGlobalRelationTree() {
        // TODO Auto-generated method stub
        List<TreeNode> resultList = new ArrayList<TreeNode>();
        //以所有团体关系类型为根节点
        AuPartyRelationType[] relTypes = AuPartyRelationType.values();
        for (AuPartyRelationType relType : relTypes) {
            TreeNode rootNode = new TreeNode(relType.getId() + "", relType.getName());

            //以当前团体类型的团体关系为子节点
            List<AuPartyRelation> relations = getPartyRelationsByTypeId(relType.getId() + "");
            List<TreeNode> parentNodes = new ArrayList<TreeNode>();
            for (AuPartyRelation partyRelation : relations) {
                if (null == partyRelation.getParentId() || "".equals(partyRelation.getParentId())) {
                    // TreeNode parentNode = new TreeNode(partyRelation.getId(), partyRelation.getName());
                    //if (partyRelation.getIsLeaf().equals("0")) {
                    // List<TreeNode> leafNodeList = this.getChildTreeNode(partyRelation.getId(), relations, false);
                    // parentNode.setChildren(leafNodeList);
                    //}
                    // parentNodes.add(parentNode);
                    rootNode.setChildren(parentNodes);
                }
            }
            resultList.add(rootNode);
        }
        return resultList;
    }

    private List<TreeNode> getChildTreeNode(String parentId, List<AuPartyRelation> nodeList, boolean hasHref) {
        return getChildTreeNode(parentId, nodeList, hasHref, null);
    }

    private List<TreeNode> getChildTreeNode(String parentId, List<AuPartyRelation> nodeList, boolean hasHref,
        Map<String, Object> map) {
        List<TreeNode> resultList = new ArrayList<TreeNode>();
        for (AuPartyRelation partyRelation : nodeList) {
            TreeNode leafNode = null;
            if (partyRelation.getParentId() != null && partyRelation.getParentId().equals(parentId) && !partyRelation
                .getParentId().equals(partyRelation.getId())) {
                //leafNode = new TreeNode(partyRelation.getId(), partyRelation.getName());

                if (hasHref) {
                   /* leafNode.setUrl("admin/relation/view.jspx?id=" + partyRelation.getAuParty().getId() + "&rid=" + partyRelation.getId());
                    //行政关系
                    if (partyRelation.getAuPartyRelationType() == AuPartyRelationType.ADMINISTRATIVE) {
                        leafNode.setUrl("admin/relation/view.jspx?id=" + partyRelation.getAuParty().getId() + "&rid=" + partyRelation.getId());
                    } else if (partyRelation.getAuPartyRelationType() == AuPartyRelationType.ROLE) {
                        //角色关系
                        leafNode.setUrl("admin/role/view.jspx?id=" + partyRelation.getAuParty().getId() + "&rid=" + partyRelation.getId());
                        leafNode.setNocheck(true);
                    }
                    leafNode.setTarget("rightFrame");*/
                }

                if (map != null && map.containsKey("user")) {
                    //用户分配角色，叶子节点ID设置为团体ID，团体ID就是角色ID
                    // leafNode.setId(partyRelation.getAuParty().getId());
                    AuUser user = (AuUser) map.get("user");
                    //Set<AuRole> roleSet = user.getAuRoleSet();
                    // Iterator<AuRole> roleIter = roleSet.iterator();
                    // while (roleIter.hasNext()) {
                    //    AuRole role = roleIter.next();
                      /*  if (role.getName().equals(leafNode.getName())) {
                            leafNode.setName(role.getName() + "(已关联)");
                            leafNode.setNocheck(true);
                        }*/
                    //  }
                }

                //if (partyRelation.getIsLeaf().equals("0")) {
                //List<TreeNode> leafNodeList = this.getChildTreeNode(partyRelation.getId(), nodeList, hasHref);
                // leafNode.setChildren(leafNodeList);
                //}
                resultList.add(leafNode);
            }
        }
        return resultList;
    }

    public boolean hasCustomRoot(String relTypeId) {
        // TODO Auto-generated method stub
        return false;//partyRelationRepository.findByProperty("auPartyRelationType.id", relTypeId).size() > 0;
    }

    @Override
    public Integer getMaxValue(String dir) {
        return null;
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
