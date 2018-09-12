package com.qslion.authority.core.entity;

import com.qslion.framework.entity.BaseEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * AuLoginLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AU_LOGIN_LOG")
public class AuLoginLog extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private String loginId;
	private String name;
	private String loginIp;
	private String ie;
	private String os;
	private String host;
	private String logoutType;
	private Date loginTime;
	private Date logoutTime;
	private String loginState;
	private Date lockTime;
	private String loginMac;

	// Constructors

	/** default constructor */
	public AuLoginLog() {
	}

	/** full constructor */
	public AuLoginLog(String loginId, String name, String loginIp, String ie,
			String os, String host, String logoutType, Date loginTime,
			Date logoutTime, String loginState, Date lockTime,
			String loginMac) {
		this.loginId = loginId;
		this.name = name;
		this.loginIp = loginIp;
		this.ie = ie;
		this.os = os;
		this.host = host;
		this.logoutType = logoutType;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.loginState = loginState;
		this.lockTime = lockTime;
		this.loginMac = loginMac;
	}



	@Column(name = "LOGIN_ID", length = 50)
	public String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@Column(name = "NAME", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "LOGIN_IP", length = 50)
	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@Column(name = "IE", length = 50)
	public String getIe() {
		return this.ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	@Column(name = "OS", length = 50)
	public String getOs() {
		return this.os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	@Column(name = "HOST", length = 50)
	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Column(name = "LOGOUT_TYPE", length = 1)
	public String getLogoutType() {
		return this.logoutType;
	}

	public void setLogoutType(String logoutType) {
		this.logoutType = logoutType;
	}

	@Column(name = "LOGIN_TIME", length = 19)
	public Date getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	@Column(name = "LOGOUT_TIME", length = 19)
	public Date getLogoutTime() {
		return this.logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	@Column(name = "LOGIN_STATE", length = 50)
	public String getLoginState() {
		return this.loginState;
	}

	public void setLoginState(String loginState) {
		this.loginState = loginState;
	}

	@Column(name = "LOCK_TIME", length = 19)
	public Date getLockTime() {
		return this.lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}

	@Column(name = "LOGIN_MAC", length = 50)
	public String getLoginMac() {
		return this.loginMac;
	}

	public void setLoginMac(String loginMac) {
		this.loginMac = loginMac;
	}

}