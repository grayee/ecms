package com.qslion.core.service;

import com.qslion.core.entity.AuPartyRelation;
import com.qslion.core.entity.AuRole;
import com.qslion.core.entity.PartyEntity;
import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.core.enums.AuPartyType;
import com.qslion.framework.bean.TreeNode;
import com.qslion.framework.service.IGenericService;

import java.util.List;
import java.util.Set;

public interface PartyRelationService extends IGenericService<AuPartyRelation, Long> {


    /**
     * 局部团体关系树
     *
     * @param partyType 团体类型
     * @return 树
     */
    List<TreeNode> getPartyRelationTree(AuPartyType partyType);

    /**
     * 局部团体关系树
     *
     * @param relationType 关系类型
     * @param partyType    团体类型
     * @return 树
     */
    List<TreeNode> getPartyRelationTree(AuPartyRelationType relationType, AuPartyType partyType);

    /**
     * 团体关系树
     *
     * @param relationType 关系类型，角色
     * @param roleSet      已选角色集合
     * @return 树
     */
    List<TreeNode> getPartyRelationTree(AuPartyRelationType relationType, Set<AuRole> roleSet);

    //明细关系树
    List<TreeNode> getPartyDetailRelationTree(String partyId, String relationTypeId);

    //全局团体关系树
    List<TreeNode> getGlobalRelationTree(Set<AuRole> roleSet);


    /**
     * 添加团体关系
     *
     * @param party        团体
     * @param relationType 关系类型
     */
    boolean addPartyRelation(PartyEntity party, AuPartyRelationType relationType);


    /**
     * 删除团体关系
     *
     * @param party 团体
     * @return boolean
     */
    boolean removePartyRelation(PartyEntity partyEntity);

    /**
     * 初始化根节点
     *
     * @param partyEntity        团体
     * @param relationType 团体关系类型
     * @return boolean
     */
    boolean initRoot(PartyEntity partyEntity, AuPartyRelationType relationType);

    AuPartyRelation getRelationByPartyId(String partyId, String relTypeId);


    //检查简化版机构根节点
    boolean hasCustomRoot(String orgCode);

}
