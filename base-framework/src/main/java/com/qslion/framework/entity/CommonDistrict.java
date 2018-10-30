package com.qslion.framework.entity;

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
@Table(name = "common_district")
public class CommonDistrict extends BaseEntity<Long> {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private Short level;
    private Boolean usetype;
    private Long upid;
    private Short displayorder;

    // Constructors

    /**
     * default constructor
     */
    public CommonDistrict() {
    }

    /**
     * full constructor
     */
    public CommonDistrict(String name, Short level, Boolean usetype,
        Long upid, Short displayorder) {
        this.name = name;
        this.level = level;
        this.usetype = usetype;
        this.upid = upid;
        this.displayorder = displayorder;
    }


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

    @Column(name = "usetype", nullable = false)
    public Boolean getUsetype() {
        return this.usetype;
    }

    public void setUsetype(Boolean usetype) {
        this.usetype = usetype;
    }

    @Column(name = "upid", nullable = false)
    public Long getUpid() {
        return this.upid;
    }

    public void setUpid(Long upid) {
        this.upid = upid;
    }

    @Column(name = "displayorder", nullable = false)
    public Short getDisplayorder() {
        return this.displayorder;
    }

    public void setDisplayorder(Short displayorder) {
        this.displayorder = displayorder;
    }

}