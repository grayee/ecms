package com.qslion.authority.core.service;

import com.qslion.authority.core.entity.AuOrgRelation;
import com.qslion.authority.core.entity.AuRole;
import com.qslion.authority.core.entity.IOrg;
import com.qslion.authority.core.enums.AuOrgRelationType;
import com.qslion.authority.core.enums.AuOrgType;
import com.qslion.framework.bean.TreeNode;
import com.qslion.framework.service.IGenericService;

import java.util.List;
import java.util.Set;

public interface AuOrgRelationService extends IGenericService<AuOrgRelation, Long> {

    /**
     * 局部组织关系树
     *
     * @param relationType 关系类型
     * @param orgType 组织类型
     * @param roleSet  角色集合
     * @return 树
     */
    List<TreeNode> getOrgRelationTree(AuOrgRelationType relationType,AuOrgType orgType, Set<AuRole> roleSet);

    /**
     * 局部组织关系树
     *
     * @param orgType 组织类型
     * @return 树
     */
    List<TreeNode> getTargetTree(AuOrgType orgType, Set<AuRole> roleSet);

    /**
     * 已授权机构树
     *
     * @param orgType 组织类型
     * @param roleSet 角色集合
     * @return List<TreeNode>
     */
    List<TreeNode> getGrantedDataTree(AuOrgType orgType, Set<AuRole> roleSet);

    /**
     * 全局团体关系树
     *
     * @param roleSet 已选角色集合
     * @return List<TreeNode>
     */
    List<TreeNode> getGlobalRelationTree(Set<AuRole> roleSet);


    /**
     * 添加组织关系
     *
     * @param org          组织
     * @param relationType 关系类型
     */
    boolean addOrgRelation(IOrg org, AuOrgRelationType relationType);

    /**
     * 更新组织关系
     *
     * @param org          组织
     * @param relationType 关系类型
     */
    boolean updateOrgRelation(IOrg org, AuOrgRelationType relationType);

    /**
     * 删除组织关系
     *
     * @param org 组织
     * @return boolean
     */
    boolean removeOrgRelation(IOrg org, AuOrgRelationType relationType);

    /**
     * 初始化根节点
     *
     * @param org          组织
     * @param relationType 组织关系类型
     * @return boolean
     */
    boolean initRoot(IOrg org, AuOrgRelationType relationType);


    List<AuOrgRelation> findByRelationType(AuOrgRelationType relationType);

    AuOrgRelation findByOrg(AuOrgRelationType relationType,AuOrgType orgType,Long orgId);

}
