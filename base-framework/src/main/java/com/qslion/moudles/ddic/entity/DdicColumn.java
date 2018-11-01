package com.qslion.moudles.ddic.entity;

import com.qslion.framework.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 实体类 - 数据字典列表
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "ddic_column")
public class DdicColumn extends BaseEntity<Long> {

    private String tableid;
    private String tablename;
    private String columnname;
    private String displayname;
    private String columntype;
    private Long columnlen;
    private Integer columnprec;
    private String defaultval;
    private String isnullable;
    private String isvirtual;
    private Integer valuefrom;
    private Integer valueto;
    private String formula;
    private String iscreated;
    private String status;
    private String category;
    private String remark;


    @Column(name = "TABLEID", length = 32)
    public String getTableid() {
        return this.tableid;
    }

    public void setTableid(String tableid) {
        this.tableid = tableid;
    }

    @Column(name = "TABLENAME", nullable = false, length = 128)
    public String getTablename() {
        return this.tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    @Column(name = "COLUMNNAME", nullable = false, length = 128)
    public String getColumnname() {
        return this.columnname;
    }

    public void setColumnname(String columnname) {
        this.columnname = columnname;
    }

    @Column(name = "DISPLAYNAME", length = 128)
    public String getDisplayname() {
        return this.displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    @Column(name = "COLUMNTYPE", length = 10)
    public String getColumntype() {
        return this.columntype;
    }

    public void setColumntype(String columntype) {
        this.columntype = columntype;
    }

    @Column(name = "COLUMNLEN", precision = 10, scale = 0)
    public Long getColumnlen() {
        return this.columnlen;
    }

    public void setColumnlen(Long columnlen) {
        this.columnlen = columnlen;
    }

    @Column(name = "COLUMNPREC", precision = 8, scale = 0)
    public Integer getColumnprec() {
        return this.columnprec;
    }

    public void setColumnprec(Integer columnprec) {
        this.columnprec = columnprec;
    }

    @Column(name = "DEFAULTVAL", length = 128)
    public String getDefaultval() {
        return this.defaultval;
    }

    public void setDefaultval(String defaultval) {
        this.defaultval = defaultval;
    }

    @Column(name = "ISNULLABLE", length = 1)
    public String getIsnullable() {
        return this.isnullable;
    }

    public void setIsnullable(String isnullable) {
        this.isnullable = isnullable;
    }

    @Column(name = "ISVIRTUAL", length = 1)
    public String getIsvirtual() {
        return this.isvirtual;
    }

    public void setIsvirtual(String isvirtual) {
        this.isvirtual = isvirtual;
    }

    @Column(name = "VALUEFROM", precision = 8, scale = 0)
    public Integer getValuefrom() {
        return this.valuefrom;
    }

    public void setValuefrom(Integer valuefrom) {
        this.valuefrom = valuefrom;
    }

    @Column(name = "VALUETO", precision = 8, scale = 0)
    public Integer getValueto() {
        return this.valueto;
    }

    public void setValueto(Integer valueto) {
        this.valueto = valueto;
    }

    @Column(name = "FORMULA", length = 128)
    public String getFormula() {
        return this.formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
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

    @Column(name = "CATEGORY", length = 64)
    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(name = "REMARK", length = 1024)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}