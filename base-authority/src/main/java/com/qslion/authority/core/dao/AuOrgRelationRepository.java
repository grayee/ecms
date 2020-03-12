package com.qslion.authority.core.dao;

import com.qslion.authority.core.entity.AuOrgRelation;
import com.qslion.authority.core.enums.AuOrgType;
import com.qslion.authority.core.enums.AuOrgRelationType;
import com.qslion.framework.dao.IGenericRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Dao实现类 - 组织关系
 */
public interface AuOrgRelationRepository extends IGenericRepository<AuOrgRelation, Long> {


    /**
     * 更新节点是否为叶子节点状态
     *
     * @param id     ID
     * @param isLeaf leaf status
     * @return update count
     */
    @Modifying
    @Query(value = "update AuOrgRelation pr set pr.leaf =:isLeaf WHERE pr.id=:id ")
    Integer updateLeaf(@Param("id") Long id, @Param("isLeaf") boolean isLeaf);

    /**
     * 根据组织关系类型获取组织关系
     *
     * @param relationType 团体关系类型
     * @return 团体关系List
     */
    List<AuOrgRelation> findByRelationType(AuOrgRelationType relationType);


    /**
     * 根据组织获取组织关系
     *
     * @param orgId        组织id
     * @param orgType      组织类型
     * @param relationType 组织关系类型
     * @return 组织关系
     */
    AuOrgRelation findByOrgIdAndOrgTypeAndRelationType(Long orgId, AuOrgType orgType, AuOrgRelationType relationType);


}