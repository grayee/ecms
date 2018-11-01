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
@Table(name = "dd_template")
public class DdTemplate extends BaseEntity<Long> {

	 
	private String name;
	private String mainurl;


	@Column(name = "NAME", nullable = false, length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "MAINURL", nullable = false, length = 128)
	public String getMainurl() {
		return this.mainurl;
	}

	public void setMainurl(String mainurl) {
		this.mainurl = mainurl;
	}

}