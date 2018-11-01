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
@Table(name = "dd_module_query_detail")
public class DdModuleQueryDetail extends BaseEntity<Long> {

 
	private String moduleid;
	private String tablename;
	private String viewcode;
	private String fieldid;
	private String type;
	private Integer seqno;

	@Column(name = "MODULEID", nullable = false, length = 64)
	public String getModuleid() {
		return this.moduleid;
	}

	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}

	@Column(name = "TABLENAME", nullable = false, length = 64)
	public String getTablename() {
		return this.tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	@Column(name = "VIEWCODE", length = 64)
	public String getViewcode() {
		return this.viewcode;
	}

	public void setViewcode(String viewcode) {
		this.viewcode = viewcode;
	}

	@Column(name = "FIELDID", length = 64)
	public String getFieldid() {
		return this.fieldid;
	}

	public void setFieldid(String fieldid) {
		this.fieldid = fieldid;
	}

	@Column(name = "TYPE", length = 64)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "SEQNO", precision = 8, scale = 0)
	public Integer getSeqno() {
		return this.seqno;
	}

	public void setSeqno(Integer seqno) {
		this.seqno = seqno;
	}

}