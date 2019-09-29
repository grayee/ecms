package com.qslion.authority.core.entity;

import com.qslion.framework.entity.BaseEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * AuAuthorizeLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AU_AUTHORIZE_LOG")
public class AuAuthorizeLog extends BaseEntity<Long> {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date operateDate;
	private String operateId;
	private String operateName;
	private String operateType;
	private String visitorId;
	private String visitorName;
	private String visitorCode;
	private String visitorType;
	private String resourceId;
	private String resourceName;
	private String resourceCode;
	private String resourceType;
	private String authorizeStatus;
	private String authorizeTag;
	private String accreditType;
	private Integer accessType;
	private String isAppend;
	private Date deleteDate;

	// Constructors

	/** default constructor */
	public AuAuthorizeLog() {
	}

	/** minimal constructor */
	public AuAuthorizeLog(String visitorId, String resourceId,
			String authorizeStatus, String accreditType, String isAppend) {
		this.visitorId = visitorId;
		this.resourceId = resourceId;
		this.authorizeStatus = authorizeStatus;
		this.accreditType = accreditType;
		this.isAppend = isAppend;
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

	@Column(name = "VISITOR_ID", nullable = false, length = 19)
	public String getVisitorId() {
		return this.visitorId;
	}

	public void setVisitorId(String visitorId) {
		this.visitorId = visitorId;
	}

	@Column(name = "VISITOR_NAME", length = 300)
	public String getVisitorName() {
		return this.visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	@Column(name = "VISITOR_CODE", length = 300)
	public String getVisitorCode() {
		return this.visitorCode;
	}

	public void setVisitorCode(String visitorCode) {
		this.visitorCode = visitorCode;
	}

	@Column(name = "VISITOR_TYPE", length = 1)
	public String getVisitorType() {
		return this.visitorType;
	}

	public void setVisitorType(String visitorType) {
		this.visitorType = visitorType;
	}

	@Column(name = "RESOURCE_ID", nullable = false, length = 19)
	public String getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	@Column(name = "RESOURCE_NAME", length = 300)
	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	@Column(name = "RESOURCE_CODE", length = 300)
	public String getResourceCode() {
		return this.resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	@Column(name = "RESOURCE_TYPE", length = 1)
	public String getResourceType() {
		return this.resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	@Column(name = "AUTHORIZE_STATUS", nullable = false, length = 1)
	public String getAuthorizeStatus() {
		return this.authorizeStatus;
	}

	public void setAuthorizeStatus(String authorizeStatus) {
		this.authorizeStatus = authorizeStatus;
	}

	@Column(name = "AUTHORIZE_TAG", length = 19)
	public String getAuthorizeTag() {
		return this.authorizeTag;
	}

	public void setAuthorizeTag(String authorizeTag) {
		this.authorizeTag = authorizeTag;
	}

	@Column(name = "ACCREDIT_TYPE", nullable = false, length = 1)
	public String getAccreditType() {
		return this.accreditType;
	}

	public void setAccreditType(String accreditType) {
		this.accreditType = accreditType;
	}

	@Column(name = "ACCESS_TYPE", precision = 8, scale = 0)
	public Integer getAccessType() {
		return this.accessType;
	}

	public void setAccessType(Integer accessType) {
		this.accessType = accessType;
	}

	@Column(name = "IS_APPEND", nullable = false, length = 1)
	public String getIsAppend() {
		return this.isAppend;
	}

	public void setIsAppend(String isAppend) {
		this.isAppend = isAppend;
	}


	@Column(name = "DELETE_DATE", length = 19)
	public Date getDeleteDate() {
		return this.deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

}