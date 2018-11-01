package com.qslion.moudles.district.entity;

import com.qslion.framework.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 实体类 - 区域
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "pre_common_district")
public class CommonDistrict extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;
    private String name;
    private Short level;
    private Short usetype;
    private Long upid;

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "level", nullable = false)
    public Short getLevel() {
        return this.level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    @Column(name = "usetype")
    public Short getUsetype() {
        return this.usetype;
    }

    public void setUsetype(Short usetype) {
        this.usetype = usetype;
    }

    @Column(name = "upid", nullable = false)
    public Long getUpid() {
        return this.upid;
    }

    public void setUpid(Long upid) {
        this.upid = upid;
    }
}