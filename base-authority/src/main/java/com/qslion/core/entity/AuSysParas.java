package com.qslion.core.entity;


import com.qslion.framework.entity.BaseEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * AuSysparas entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AU_SYSPARAS")
public class AuSysParas extends BaseEntity<Long> {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String propertykey;
	private String value;
	private Date inittime;
	private Date updatetime;
	private String creatorid;
	private String creatorname;
	private String description;
	private String cloumn1;
	private String enable;
	private String propertytype;

	// Constructors

	/** default constructor */
	public AuSysParas() {
	}

	/** full constructor */
	public AuSysParas(String propertykey, String value, Date inittime,
			Date updatetime, String creatorid, String creatorname,
			String description, String cloumn1, String enable,
			String propertytype) {
		this.propertykey = propertykey;
		this.value = value;
		this.inittime = inittime;
		this.updatetime = updatetime;
		this.creatorid = creatorid;
		this.creatorname = creatorname;
		this.description = description;
		this.cloumn1 = cloumn1;
		this.enable = enable;
		this.propertytype = propertytype;
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

	@Column(name = "CREATORID", length = 19)
	public String getCreatorid() {
		return this.creatorid;
	}

	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}

	@Column(name = "CREATORNAME", length = 50)
	public String getCreatorname() {
		return this.creatorname;
	}

	public void setCreatorname(String creatorname) {
		this.creatorname = creatorname;
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