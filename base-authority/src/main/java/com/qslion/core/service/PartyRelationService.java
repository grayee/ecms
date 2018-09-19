package com.qslion.core.service;

import com.qslion.core.entity.AuPartyRelation;
import com.qslion.core.enums.AuPartyType;
import com.qslion.core.util.TreeNode;
import com.qslion.framework.service.IGenericService;
import java.util.List;
import java.util.Map;

public interface PartyRelationService extends IGenericService<AuPartyRelation, Long> {

      public abstract boolean deletePartyRelation(String paramString);

	  public abstract int getCountByParentCode(String paramString);

	  public  List<AuPartyRelation> queryPartyRelation(AuPartyRelation auPartyRelationVo);

	  public abstract AuPartyRelation find(String paramString);

	  public abstract int getRecordCount(String paramString);

	  //根据团体关系类型ID查找团体关系
	  public List<AuPartyRelation> getPartyRelationsByTypeId(String typeId);
	  //局部团体关系树
      public List<TreeNode> getPartyRelationTree(String relationTypeId, boolean hasHref);
      //局部团体关系树带参数
      public List<TreeNode> getPartyRelationTree(String relationTypeId, boolean hasHref, Map<String, Object> map);
      
      //明细关系树
      public List<TreeNode> getPartyDetailRelationTree(String partyId, String relationTypeId);
      //全局团体关系树
      public List<TreeNode> getGlobalRelationTree();
      //根据团体类型获得关系树
      public List<TreeNode> getRelationTreeByPartyTypes(List<AuPartyType> partyTypes);
      //根节点初始化
      public boolean initRoot(String partyId, String relTypeId);
      //添加团体关系
	  public boolean addPartyRelation(String partyId, String parentRelId, String relTypeId);
	  //
	  public AuPartyRelation getRelationByPartyId(String partyId, String relTypeId);
      
	  public abstract void addPartyRelation(AuPartyRelation paramAuPartyRelationVo);
	  
	  //检查简化版机构根节点
	  public boolean hasCustomRoot(String orgCode);
	  //获取树的最大左右值
	  public Integer getMaxValue(String dir);
	  
}
