package com.qslion.authority.core.entity;


import com.qslion.framework.entity.BaseEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * AuSyserror entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AU_SYSERROR")
public class AuSysError extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private String operateId;
	private String operateName;
	private Date operateDate;
	private String errorType;
	private String sourceId;
	private String sourcePartyid;
	private String sourceCode;
	private String sourceName;
	private String sourceOrgtree;
	private String sourceTypeid;
	private String sourceDetail;
	private String remark;
	private String cloumn1;

	// Constructors

	/** default constructor */
	public AuSysError() {
	}

	/** full constructor */
	public AuSysError(String operateId, String operateName,
			Date operateDate, String errorType, String sourceId,
			String sourcePartyid, String sourceCode, String sourceName,
			String sourceOrgtree, String sourceTypeid, String sourceDetail,
			String remark, String cloumn1) {
		this.operateId = operateId;
		this.operateName = operateName;
		this.operateDate = operateDate;
		this.errorType = errorType;
		this.sourceId = sourceId;
		this.sourcePartyid = sourcePartyid;
		this.sourceCode = sourceCode;
		this.sourceName = sourceName;
		this.sourceOrgtree = sourceOrgtree;
		this.sourceTypeid = sourceTypeid;
		this.sourceDetail = sourceDetail;
		this.remark = remark;
		this.cloumn1 = cloumn1;
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

	@Column(name = "OPERATE_DATE", length = 19)
	public Date getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	@Column(name = "ERROR_TYPE", length = 1)
	public String getErrorType() {
		return this.errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	@Column(name = "SOURCE_ID", length = 19)
	public String getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name = "SOURCE_PARTYID", length = 19)
	public String getSourcePartyid() {
		return this.sourcePartyid;
	}

	public void setSourcePartyid(String sourcePartyid) {
		this.sourcePartyid = sourcePartyid;
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

	@Column(name = "SOURCE_TYPEID", length = 19)
	public String getSourceTypeid() {
		return this.sourceTypeid;
	}

	public void setSourceTypeid(String sourceTypeid) {
		this.sourceTypeid = sourceTypeid;
	}

	@Column(name = "SOURCE_DETAIL", length = 500)
	public String getSourceDetail() {
		return this.sourceDetail;
	}

	public void setSourceDetail(String sourceDetail) {
		this.sourceDetail = sourceDetail;
	}

	@Column(name = "REMARK", length = 500)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "CLOUMN1", length = 50)
	public String getCloumn1() {
		return this.cloumn1;
	}

	public void setCloumn1(String cloumn1) {
		this.cloumn1 = cloumn1;
	}

}