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
@Table(name = "dd_view")
public class DdView extends BaseEntity<Long> {

	 
	private String code;
	private String name;
	private Byte viewtype;
	private String conditionContent;
	private String businessclass;
	private String fieldlayout;
	private Short pagesize;
	private String issystem;

	@Column(name = "CODE", length = 64)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "NAME", nullable = false, length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "VIEWTYPE", nullable = false, precision = 2, scale = 0)
	public Byte getViewtype() {
		return this.viewtype;
	}

	public void setViewtype(Byte viewtype) {
		this.viewtype = viewtype;
	}

	@Column(name = "CONDITION_CONTENT", length = 512)
	public String getConditionContent() {
		return this.conditionContent;
	}

	public void setConditionContent(String conditionContent) {
		this.conditionContent = conditionContent;
	}

	@Column(name = "BUSINESSCLASS", length = 512)
	public String getBusinessclass() {
		return this.businessclass;
	}

	public void setBusinessclass(String businessclass) {
		this.businessclass = businessclass;
	}

	@Column(name = "FIELDLAYOUT", length = 2000)
	public String getFieldlayout() {
		return this.fieldlayout;
	}

	public void setFieldlayout(String fieldlayout) {
		this.fieldlayout = fieldlayout;
	}

	@Column(name = "PAGESIZE", precision = 3, scale = 0)
	public Short getPagesize() {
		return this.pagesize;
	}

	public void setPagesize(Short pagesize) {
		this.pagesize = pagesize;
	}

	@Column(name = "ISSYSTEM", nullable = false, length = 1)
	public String getIssystem() {
		return this.issystem;
	}

	public void setIssystem(String issystem) {
		this.issystem = issystem;
	}

}