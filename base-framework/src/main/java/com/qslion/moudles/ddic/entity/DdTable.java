package com.qslion.moudles.ddic.entity;

import com.qslion.framework.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 实体类 - 数据字典基础表
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "dd_table")
public class DdTable extends BaseEntity<Long> {
 
	private String tablename;
	private String displayname;
	private String category;
	private String subcategory;
	private String isvirtual;
	private String checkexpression;
	private String iscreated;
	private String status;
	private String remark;

	@Column(name = "TABLENAME", nullable = false, length = 128)
	public String getTablename() {
		return this.tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	@Column(name = "DISPLAYNAME", nullable = false, length = 128)
	public String getDisplayname() {
		return this.displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	@Column(name = "CATEGORY", nullable = false, length = 1)
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "SUBCATEGORY", length = 128)
	public String getSubcategory() {
		return this.subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	@Column(name = "ISVIRTUAL", length = 1)
	public String getIsvirtual() {
		return this.isvirtual;
	}

	public void setIsvirtual(String isvirtual) {
		this.isvirtual = isvirtual;
	}

	@Column(name = "CHECKEXPRESSION", length = 128)
	public String getCheckexpression() {
		return this.checkexpression;
	}

	public void setCheckexpression(String checkexpression) {
		this.checkexpression = checkexpression;
	}

	@Column(name = "ISCREATED", length = 1)
	public String getIscreated() {
		return this.iscreated;
	}

	public void setIscreated(String iscreated) {
		this.iscreated = iscreated;
	}

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	@Column(name = "REMARK", length = 1024)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}