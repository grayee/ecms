package com.qslion.moudles.ddic.entity;

import com.qslion.framework.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * DdViewTable entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "dd_view_table")
public class DdViewTable extends BaseEntity<Long> {
 
	private String viewid;
	private String viewcode;
	private String tableid;
	private String keyname;

	@Column(name = "VIEWID", nullable = false, length = 32)
	public String getViewid() {
		return this.viewid;
	}

	public void setViewid(String viewid) {
		this.viewid = viewid;
	}

	@Column(name = "VIEWCODE", nullable = false, length = 64)
	public String getViewcode() {
		return this.viewcode;
	}

	public void setViewcode(String viewcode) {
		this.viewcode = viewcode;
	}

	@Column(name = "TABLEID", nullable = false, length = 32)
	public String getTableid() {
		return this.tableid;
	}

	public void setTableid(String tableid) {
		this.tableid = tableid;
	}

	@Column(name = "KEYNAME", length = 64)
	public String getKeyname() {
		return this.keyname;
	}

	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}

}