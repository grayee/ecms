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
@Table(name = "dd_view_field")
public class DdViewField extends BaseEntity<Long> {

    private String name;
    private String displayName;
    private String viewId;
    private String viewCode;
    private String status;
    private String description;
    private Byte displayType;
    private String refModel;
    private String refSource;
    private String refSourceClass;
    private String conditionContent;
    private String callback;
    private String tableName;
    private String columnName;
    private String columnSql;
    private String readonly;
    private String displayOriginValue;
    private String keySource;
    private String display;
    private Long seqno;
    private Long sortno;
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

    @Column(name = "DISPLAY_NAME", length = 64)
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Column(name = "VIEW_ID", nullable = false, length = 32)
    public String getViewId() {
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    @Column(name = "VIEW_CODE", nullable = false, length = 64)
    public String getViewCode() {
        return viewCode;
    }

    public void setViewCode(String viewCode) {
        this.viewCode = viewCode;
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

    @Column(name = "DISPLAY_TYPE", nullable = false, precision = 2, scale = 0)
    public Byte getDisplayType() {
        return displayType;
    }

    public void setDisplayType(Byte displayType) {
        this.displayType = displayType;
    }

    @Column(name = "REF_MODEL", length = 512)
    public String getRefModel() {
        return refModel;
    }

    public void setRefModel(String refModel) {
        this.refModel = refModel;
    }

    @Column(name = "REF_SOURCE", length = 64)
    public String getRefSource() {
        return refSource;
    }

    public void setRefSource(String refSource) {
        this.refSource = refSource;
    }

    @Column(name = "REF_SOURCE_CLASS", length = 64)
    public String getRefSourceClass() {
        return refSourceClass;
    }

    public void setRefSourceClass(String refSourceClass) {
        this.refSourceClass = refSourceClass;
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

    @Column(name = "TABLE_NAME", length = 64)
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Column(name = "COLUMN_NAME", length = 64)
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Column(name = "COLUMN_SQL", length = 512)
    public String getColumnSql() {
        return columnSql;
    }

    public void setColumnSql(String columnSql) {
        this.columnSql = columnSql;
    }

    @Column(name = "READONLY", nullable = false, length = 1)
    public String getReadonly() {
        return this.readonly;
    }

    public void setReadonly(String readonly) {
        this.readonly = readonly;
    }

    @Column(name = "DISPLAY_ORIGIN_VALUE", nullable = false, length = 1)
    public String getDisplayOriginValue() {
        return displayOriginValue;
    }

    public void setDisplayOriginValue(String displayOriginValue) {
        this.displayOriginValue = displayOriginValue;
    }

    @Column(name = "KEY_SOURCE", nullable = false, length = 1)
    public String getKeySource() {
        return keySource;
    }

    public void setKeySource(String keySource) {
        this.keySource = keySource;
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