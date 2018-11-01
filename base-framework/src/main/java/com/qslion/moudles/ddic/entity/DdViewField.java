package com.qslion.moudles.ddic.entity;

import com.qslion.framework.entity.BaseEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 实体类 - 数据字典基础表
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "dd_view_field")
public class DdViewField extends BaseEntity<Long> {

 
	private String name;
	private String displayname;
	private String viewid;
	private String viewcode;
	private String status;
	private String description;
	private Byte displaytype;
	private String refmodel;
	private String refsource;
	private String refsourceclass;
	private String conditionContent;
	private String callback;
	private String tablename;
	private String columnname;
	private String columnsql;
	private String readonly;
	private String displayoriginvalue;
	private String keysource;
	private String display;
	private Long seqno;
	private Long sortno;
	private String createdby;
	private String updatedby;
	private Date createdDate;
	private Date updated;
	private String sort;
	private String width;
	private String align;
	private String category;
	private String buskey;


	@Column(name = "NAME", nullable = false, length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DISPLAYNAME", length = 64)
	public String getDisplayname() {
		return this.displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

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

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "DESCRIPTION", length = 512)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "DISPLAYTYPE", nullable = false, precision = 2, scale = 0)
	public Byte getDisplaytype() {
		return this.displaytype;
	}

	public void setDisplaytype(Byte displaytype) {
		this.displaytype = displaytype;
	}

	@Column(name = "REFMODEL", length = 512)
	public String getRefmodel() {
		return this.refmodel;
	}

	public void setRefmodel(String refmodel) {
		this.refmodel = refmodel;
	}

	@Column(name = "REFSOURCE", length = 64)
	public String getRefsource() {
		return this.refsource;
	}

	public void setRefsource(String refsource) {
		this.refsource = refsource;
	}

	@Column(name = "REFSOURCECLASS", length = 64)
	public String getRefsourceclass() {
		return this.refsourceclass;
	}

	public void setRefsourceclass(String refsourceclass) {
		this.refsourceclass = refsourceclass;
	}

	@Column(name = "CONDITION_CONTENT", length = 512)
	public String getConditionContent() {
		return this.conditionContent;
	}

	public void setConditionContent(String conditionContent) {
		this.conditionContent = conditionContent;
	}

	@Column(name = "CALLBACK", length = 512)
	public String getCallback() {
		return this.callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	@Column(name = "TABLENAME", length = 64)
	public String getTablename() {
		return this.tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	@Column(name = "COLUMNNAME", length = 64)
	public String getColumnname() {
		return this.columnname;
	}

	public void setColumnname(String columnname) {
		this.columnname = columnname;
	}

	@Column(name = "COLUMNSQL", length = 512)
	public String getColumnsql() {
		return this.columnsql;
	}

	public void setColumnsql(String columnsql) {
		this.columnsql = columnsql;
	}

	@Column(name = "READONLY", nullable = false, length = 1)
	public String getReadonly() {
		return this.readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	@Column(name = "DISPLAYORIGINVALUE", nullable = false, length = 1)
	public String getDisplayoriginvalue() {
		return this.displayoriginvalue;
	}

	public void setDisplayoriginvalue(String displayoriginvalue) {
		this.displayoriginvalue = displayoriginvalue;
	}

	@Column(name = "KEYSOURCE", nullable = false, length = 1)
	public String getKeysource() {
		return this.keysource;
	}

	public void setKeysource(String keysource) {
		this.keysource = keysource;
	}

	@Column(name = "DISPLAY", nullable = false, length = 1)
	public String getDisplay() {
		return this.display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	@Column(name = "SEQNO", precision = 10, scale = 0)
	public Long getSeqno() {
		return this.seqno;
	}

	public void setSeqno(Long seqno) {
		this.seqno = seqno;
	}

	@Column(name = "SORTNO", precision = 10, scale = 0)
	public Long getSortno() {
		return this.sortno;
	}

	public void setSortno(Long sortno) {
		this.sortno = sortno;
	}

	@Column(name = "CREATEDBY", length = 64)
	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	@Column(name = "UPDATEDBY", length = 64)
	public String getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATE", length = 10)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATED", length = 10)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Column(name = "SORT", nullable = false, length = 1)
	public String getSort() {
		return this.sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Column(name = "WIDTH", length = 10)
	public String getWidth() {
		return this.width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	@Column(name = "ALIGN", length = 10)
	public String getAlign() {
		return this.align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	@Column(name = "CATEGORY", length = 64)
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "BUSKEY", length = 64)
	public String getBuskey() {
		return this.buskey;
	}

	public void setBuskey(String buskey) {
		this.buskey = buskey;
	}

}