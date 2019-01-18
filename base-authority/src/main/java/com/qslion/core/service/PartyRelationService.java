package com.qslion.core.service;

import com.qslion.core.entity.AuParty;
import com.qslion.core.entity.AuPartyRelation;
import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.core.enums.AuPartyType;
import com.qslion.framework.bean.TreeNode;
import com.qslion.framework.service.IGenericService;
import java.util.List;
import java.util.Map;

public interface PartyRelationService extends IGenericService<AuPartyRelation, Long> {

    List<AuPartyRelation> queryPartyRelation(AuPartyRelation auPartyRelationVo);

    //根据团体关系类型ID查找团体关系
    List<AuPartyRelation> getPartyRelationsByTypeId(String typeId);

    //局部团体关系树
    List<TreeNode> getPartyRelationTree(AuPartyRelationType relationType);

    //局部团体关系树带参数
    List<TreeNode> getPartyRelationTree(AuPartyRelationType relationType, Map<String, Object> map);

    //明细关系树
    List<TreeNode> getPartyDetailRelationTree(String partyId, String relationTypeId);

    //全局团体关系树
    List<TreeNode> getGlobalRelationTree();

    //根据团体类型获得关系树
    List<TreeNode> getRelationTreeByPartyTypes(List<AuPartyType> partyTypes);

    /**
     * 添加团体关系
     *
     * @param parentId 父团体关系ID
     * @param party 团体
     * @param relationType 关系类型
     */
    boolean addPartyRelation(Long parentId, AuParty party, AuPartyRelationType relationType);

    /**
     * 初始化根节点
     *
     * @param party 团体
     * @param relationType 团体关系类型
     * @return boolean
     */
    boolean initRoot(AuParty party, AuPartyRelationType relationType);

    AuPartyRelation getRelationByPartyId(String partyId, String relTypeId);


    //检查简化版机构根节点
    boolean hasCustomRoot(String orgCode);

    //获取树的最大左右值
    Integer getMaxValue(String dir);

}
