package com.qslion.core.entity;

import com.qslion.framework.entity.BaseEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * 用户配置项，负责用户配置文件。比如用户是否修改过密码，用户的msn账户，用户的个性化信息等。
 * AuUserprofile entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AU_USERPROFILE")
public class AuUserProfile extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;
	private AuParty auParty;
	private String propertykey;
	private String value;
	private Date inittime;
	private Date updatetime;
	private String partyname;
	private String description;
	private String cloumn1;
	private String enable;
	private String propertytype;

	// Constructors

	/** default constructor */
	public AuUserProfile() {
	}

	/** full constructor */
	public AuUserProfile(AuParty auParty, String propertykey, String value,
			Date inittime, Date updatetime, String partyname,
			String description, String cloumn1, String enable,
			String propertytype) {
		this.auParty = auParty;
		this.propertykey = propertykey;
		this.value = value;
		this.inittime = inittime;
		this.updatetime = updatetime;
		this.partyname = partyname;
		this.description = description;
		this.cloumn1 = cloumn1;
		this.enable = enable;
		this.propertytype = propertytype;
	}

	// Property accessors

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARTYID")
	public AuParty getAuParty() {
		return this.auParty;
	}

	public void setAuParty(AuParty auParty) {
		this.auParty = auParty;
	}

	@Column(name = "PROPERTYKEY", length = 50)
	public String getPropertykey() {
		return this.propertykey;
	}

	public void setPropertykey(String propertykey) {
		this.propertykey = propertykey;
	}

	@Column(name = "VALUE", length = 100)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "INITTIME", length = 19)
	public Date getInittime() {
		return this.inittime;
	}

	public void setInittime(Date inittime) {
		this.inittime = inittime;
	}

	@Column(name = "UPDATETIME", length = 19)
	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	@Column(name = "PARTYNAME", length = 50)
	public String getPartyname() {
		return this.partyname;
	}

	public void setPartyname(String partyname) {
		this.partyname = partyname;
	}

	@Column(name = "DESCRIPTION", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "CLOUMN1", length = 50)
	public String getCloumn1() {
		return this.cloumn1;
	}

	public void setCloumn1(String cloumn1) {
		this.cloumn1 = cloumn1;
	}

	@Column(name = "ENABLE", length = 1)
	public String getEnable() {
		return this.enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	@Column(name = "PROPERTYTYPE", length = 1)
	public String getPropertytype() {
		return this.propertytype;
	}

	public void setPropertytype(String propertytype) {
		this.propertytype = propertytype;
	}

}