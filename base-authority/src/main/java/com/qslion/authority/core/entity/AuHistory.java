package com.qslion.authority.core.entity;

import com.qslion.framework.entity.BaseEntity;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * AuHistory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AU_HISTORY")
public class AuHistory extends BaseEntity<Long> {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date operateDate;
	private String operateId;
	private String operateName;
	private String operateType;
	private String sourceId;
	private String sourceCode;
	private String sourceName;
	private String sourceOrgtree;
	private String sourceDetail;
	private String sourceTypeid;
	private String tagId;
	private String tagUserid;
	private Date tagDate;
	private String cloumn1;
	private String cloumn2;
	private String cloumn3;
	private String sourcePartyid;
	private String destId;
	private String destCode;
	private String destName;
	private String destOrgtree;
	private Set<AuProxyHistory> auProxyhistoriesForProxyProxyerHistoryId = new HashSet<AuProxyHistory>(
			0);
	private Set<AuProxyHistory> auProxyhistoriesForProxyHistoryId = new HashSet<AuProxyHistory>(
			0);

	// Constructors

	/** default constructor */
	public AuHistory() {
	}

	/** full constructor */
	public AuHistory(Date operateDate, String operateId,
			String operateName, String operateType, String sourceId,
			String sourceCode, String sourceName, String sourceOrgtree,
			String sourceDetail, String sourceTypeid, String tagId,
			String tagUserid, Date tagDate, String cloumn1,
			String cloumn2, String cloumn3, String sourcePartyid,
			String destId, String destCode, String destName,
			String destOrgtree,
			Set<AuProxyHistory> auProxyhistoriesForProxyProxyerHistoryId,
			Set<AuProxyHistory> auProxyhistoriesForProxyHistoryId) {
		this.operateDate = operateDate;
		this.operateId = operateId;
		this.operateName = operateName;
		this.operateType = operateType;
		this.sourceId = sourceId;
		this.sourceCode = sourceCode;
		this.sourceName = sourceName;
		this.sourceOrgtree = sourceOrgtree;
		this.sourceDetail = sourceDetail;
		this.sourceTypeid = sourceTypeid;
		this.tagId = tagId;
		this.tagUserid = tagUserid;
		this.tagDate = tagDate;
		this.cloumn1 = cloumn1;
		this.cloumn2 = cloumn2;
		this.cloumn3 = cloumn3;
		this.sourcePartyid = sourcePartyid;
		this.destId = destId;
		this.destCode = destCode;
		this.destName = destName;
		this.destOrgtree = destOrgtree;
		this.auProxyhistoriesForProxyProxyerHistoryId = auProxyhistoriesForProxyProxyerHistoryId;
		this.auProxyhistoriesForProxyHistoryId = auProxyhistoriesForProxyHistoryId;
	}


	@Column(name = "OPERATE_DATE", length = 19)
	public Date getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	@Column(name = "OPERATE_ID", length = 19)
	public String getOperateId() {
		return this.operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}

	@Column(name = "OPERATE_NAME", length = 300)
	public String getOperateName() {
		return this.operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	@Column(name = "OPERATE_TYPE", length = 19)
	public String getOperateType() {
		return this.operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	@Column(name = "SOURCE_ID", length = 19)
	public String getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name = "SOURCE_CODE", length = 300)
	public String getSourceCode() {
		return this.sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	@Column(name = "SOURCE_NAME", length = 300)
	public String getSourceName() {
		return this.sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	@Column(name = "SOURCE_ORGTREE", length = 300)
	public String getSourceOrgtree() {
		return this.sourceOrgtree;
	}

	public void setSourceOrgtree(String sourceOrgtree) {
		this.sourceOrgtree = sourceOrgtree;
	}

	@Column(name = "SOURCE_DETAIL", length = 500)
	public String getSourceDetail() {
		return this.sourceDetail;
	}

	public void setSourceDetail(String sourceDetail) {
		this.sourceDetail = sourceDetail;
	}

	@Column(name = "SOURCE_TYPEID", length = 19)
	public String getSourceTypeid() {
		return this.sourceTypeid;
	}

	public void setSourceTypeid(String sourceTypeid) {
		this.sourceTypeid = sourceTypeid;
	}

	@Column(name = "TAG_ID", length = 19)
	public String getTagId() {
		return this.tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	@Column(name = "TAG_USERID", length = 19)
	public String getTagUserid() {
		return this.tagUserid;
	}

	public void setTagUserid(String tagUserid) {
		this.tagUserid = tagUserid;
	}

	@Column(name = "TAG_DATE", length = 19)
	public Date getTagDate() {
		return this.tagDate;
	}

	public void setTagDate(Date tagDate) {
		this.tagDate = tagDate;
	}

	@Column(name = "CLOUMN1", length = 300)
	public String getCloumn1() {
		return this.cloumn1;
	}

	public void setCloumn1(String cloumn1) {
		this.cloumn1 = cloumn1;
	}

	@Column(name = "CLOUMN2", length = 300)
	public String getCloumn2() {
		return this.cloumn2;
	}

	public void setCloumn2(String cloumn2) {
		this.cloumn2 = cloumn2;
	}

	@Column(name = "CLOUMN3", length = 300)
	public String getCloumn3() {
		return this.cloumn3;
	}

	public void setCloumn3(String cloumn3) {
		this.cloumn3 = cloumn3;
	}

	@Column(name = "SOURCE_PARTYID", length = 19)
	public String getSourcePartyid() {
		return this.sourcePartyid;
	}

	public void setSourcePartyid(String sourcePartyid) {
		this.sourcePartyid = sourcePartyid;
	}

	@Column(name = "DEST_ID", length = 19)
	public String getDestId() {
		return this.destId;
	}

	public void setDestId(String destId) {
		this.destId = destId;
	}

	@Column(name = "DEST_CODE", length = 300)
	public String getDestCode() {
		return this.destCode;
	}

	public void setDestCode(String destCode) {
		this.destCode = destCode;
	}

	@Column(name = "DEST_NAME", length = 300)
	public String getDestName() {
		return this.destName;
	}

	public void setDestName(String destName) {
		this.destName = destName;
	}

	@Column(name = "DEST_ORGTREE", length = 300)
	public String getDestOrgtree() {
		return this.destOrgtree;
	}

	public void setDestOrgtree(String destOrgtree) {
		this.destOrgtree = destOrgtree;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "auHistoryByProxyProxyerHistoryId")
	public Set<AuProxyHistory> getAuProxyhistoriesForProxyProxyerHistoryId() {
		return this.auProxyhistoriesForProxyProxyerHistoryId;
	}

	public void setAuProxyhistoriesForProxyProxyerHistoryId(
			Set<AuProxyHistory> auProxyhistoriesForProxyProxyerHistoryId) {
		this.auProxyhistoriesForProxyProxyerHistoryId = auProxyhistoriesForProxyProxyerHistoryId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "auHistoryByProxyHistoryId")
	public Set<AuProxyHistory> getAuProxyhistoriesForProxyHistoryId() {
		return this.auProxyhistoriesForProxyHistoryId;
	}

	public void setAuProxyhistoriesForProxyHistoryId(
			Set<AuProxyHistory> auProxyhistoriesForProxyHistoryId) {
		this.auProxyhistoriesForProxyHistoryId = auProxyhistoriesForProxyHistoryId;
	}

}