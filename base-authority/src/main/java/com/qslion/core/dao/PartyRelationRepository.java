package com.qslion.core.dao;

import com.qslion.core.entity.AuPartyRelation;
import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.framework.dao.IGenericRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Dao实现类 - 团体关系
 */
public interface PartyRelationRepository extends IGenericRepository<AuPartyRelation, Long> {


    /**
     * 更新节点是否为叶子节点状态
     *
     * @param id     ID
     * @param isLeaf leaf status
     * @return update count
     */
    @Modifying
    @Query(value = "update AuPartyRelation pr set pr.leaf =:isLeaf WHERE pr.id=:id ")
    Integer updateLeaf(@Param("id") Long id, @Param("isLeaf") boolean isLeaf);

    /**
     * 根据团体获取团体关系
     *
     * @param partyId 团体id
     * @return 团体关系
     */
    AuPartyRelation findByPartyId(Long partyId);

    /**
     * 根据团体关系类型获取团体关系
     *
     * @param partyRelationType 团体关系类型
     * @return 团体关系List
     */
    List<AuPartyRelation> findByPartyRelationType(AuPartyRelationType partyRelationType);


    /**
     * 根据团体获取团体关系
     *
     * @param partyId           团体
     * @param partyRelationType 团体关系类型
     * @return 团体关系
     */
    AuPartyRelation findByPartyIdAndPartyRelationType(Long partyId, AuPartyRelationType partyRelationType);


}