package com.qslion.authority.core.entity;


import com.qslion.framework.entity.BaseEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * AuProxyhistory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AU_PROXYHISTORY")
public class AuProxyHistory extends BaseEntity<Long> {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AuHistory auHistoryByProxyProxyerHistoryId;
	private AuHistory auHistoryByProxyHistoryId;
	private String proxyAuthorizeHistoryId;
	private String operaterId;
	private String operaterName;
	private Date operaterDate;
	private String loginName;
	private String operaterType;
	private String noticeNote;
	private String column1;
	private String sponsor;
	private String sponsorId;
	private String proxy;
	private String proxyId;
	private String recipient;
	private String recipientId;
	private String canelId;
	private String canelName;
	private Date canelDate;

	// Constructors

	/** default constructor */
	public AuProxyHistory() {
	}

	/** full constructor */
	public AuProxyHistory(AuHistory auHistoryByProxyProxyerHistoryId,
			AuHistory auHistoryByProxyHistoryId,
			String proxyAuthorizeHistoryId, String operaterId,
			String operaterName, Date operaterDate, String loginName,
			String operaterType, String noticeNote, String column1,
			String sponsor, String sponsorId, String proxy, String proxyId,
			String recipient, String recipientId, String canelId,
			String canelName, Date canelDate) {
		this.auHistoryByProxyProxyerHistoryId = auHistoryByProxyProxyerHistoryId;
		this.auHistoryByProxyHistoryId = auHistoryByProxyHistoryId;
		this.proxyAuthorizeHistoryId = proxyAuthorizeHistoryId;
		this.operaterId = operaterId;
		this.operaterName = operaterName;
		this.operaterDate = operaterDate;
		this.loginName = loginName;
		this.operaterType = operaterType;
		this.noticeNote = noticeNote;
		this.column1 = column1;
		this.sponsor = sponsor;
		this.sponsorId = sponsorId;
		this.proxy = proxy;
		this.proxyId = proxyId;
		this.recipient = recipient;
		this.recipientId = recipientId;
		this.canelId = canelId;
		this.canelName = canelName;
		this.canelDate = canelDate;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROXY_PROXYER_HISTORY_ID")
	public AuHistory getAuHistoryByProxyProxyerHistoryId() {
		return this.auHistoryByProxyProxyerHistoryId;
	}

	public void setAuHistoryByProxyProxyerHistoryId(
			AuHistory auHistoryByProxyProxyerHistoryId) {
		this.auHistoryByProxyProxyerHistoryId = auHistoryByProxyProxyerHistoryId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROXY_HISTORY_ID")
	public AuHistory getAuHistoryByProxyHistoryId() {
		return this.auHistoryByProxyHistoryId;
	}

	public void setAuHistoryByProxyHistoryId(AuHistory auHistoryByProxyHistoryId) {
		this.auHistoryByProxyHistoryId = auHistoryByProxyHistoryId;
	}

	@Column(name = "PROXY_AUTHORIZE_HISTORY_ID", length = 19)
	public String getProxyAuthorizeHistoryId() {
		return this.proxyAuthorizeHistoryId;
	}

	public void setProxyAuthorizeHistoryId(String proxyAuthorizeHistoryId) {
		this.proxyAuthorizeHistoryId = proxyAuthorizeHistoryId;
	}

	@Column(name = "OPERATER_ID", length = 19)
	public String getOperaterId() {
		return this.operaterId;
	}

	public void setOperaterId(String operaterId) {
		this.operaterId = operaterId;
	}

	@Column(name = "OPERATER_NAME", length = 300)
	public String getOperaterName() {
		return this.operaterName;
	}

	public void setOperaterName(String operaterName) {
		this.operaterName = operaterName;
	}

	@Column(name = "OPERATER_DATE", length = 19)
	public Date getOperaterDate() {
		return this.operaterDate;
	}

	public void setOperaterDate(Date operaterDate) {
		this.operaterDate = operaterDate;
	}

	@Column(name = "LOGIN_NAME", length = 300)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "OPERATER_TYPE", length = 300)
	public String getOperaterType() {
		return this.operaterType;
	}

	public void setOperaterType(String operaterType) {
		this.operaterType = operaterType;
	}

	@Column(name = "NOTICE_NOTE", length = 1)
	public String getNoticeNote() {
		return this.noticeNote;
	}

	public void setNoticeNote(String noticeNote) {
		this.noticeNote = noticeNote;
	}

	@Column(name = "COLUMN1", length = 300)
	public String getColumn1() {
		return this.column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}

	@Column(name = "SPONSOR", length = 50)
	public String getSponsor() {
		return this.sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	@Column(name = "SPONSOR_ID", length = 19)
	public String getSponsorId() {
		return this.sponsorId;
	}

	public void setSponsorId(String sponsorId) {
		this.sponsorId = sponsorId;
	}

	@Column(name = "PROXY", length = 50)
	public String getProxy() {
		return this.proxy;
	}

	public void setProxy(String proxy) {
		this.proxy = proxy;
	}

	@Column(name = "PROXY_ID", length = 19)
	public String getProxyId() {
		return this.proxyId;
	}

	public void setProxyId(String proxyId) {
		this.proxyId = proxyId;
	}

	@Column(name = "RECIPIENT", length = 50)
	public String getRecipient() {
		return this.recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	@Column(name = "RECIPIENT_ID", length = 19)
	public String getRecipientId() {
		return this.recipientId;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}

	@Column(name = "CANEL_ID", length = 19)
	public String getCanelId() {
		return this.canelId;
	}

	public void setCanelId(String canelId) {
		this.canelId = canelId;
	}

	@Column(name = "CANEL_NAME", length = 50)
	public String getCanelName() {
		return this.canelName;
	}

	public void setCanelName(String canelName) {
		this.canelName = canelName;
	}

	@Column(name = "CANEL_DATE", length = 19)
	public Date getCanelDate() {
		return this.canelDate;
	}

	public void setCanelDate(Date canelDate) {
		this.canelDate = canelDate;
	}

}