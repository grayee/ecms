package com.qslion.moudles.ddic.entity;

import com.qslion.framework.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 实体类 - 列表
 * 该表存储了系统所涉及的数据对象的所有字段，并对字段属性进行描述
 * 参考 <code>ResultSetMetaDataBase.ColumnMetaData</code>
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "dd_column")
public class DdColumn extends BaseEntity<Long> {

    private String tableId;
    private String tableName;
    private String columnName;
    private String displayName;
    private String columnType;
    private Long columnLength;
    private Integer columnPrecision;
    private String defaultValue;
    private boolean isNullable;
    private boolean isVirtual;
    private Integer valueFrom;
    private Integer valueTo;
    private String formula;
    private boolean isCreated;
    private String status;
    private String category;
    private String remark;


    @Column(name = "TABLE_ID", length = 32)
    public String getTableId() {
        return this.tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    @Column(name = "TABLE_NAME", nullable = false, length = 128)
    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Column(name = "COLUMN_NAME", nullable = false, length = 128)
    public String getColumnName() {
        return this.columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Column(name = "DISPLAY_NAME", length = 128)
    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Column(name = "COLUMN_TYPE", length = 10)
    public String getColumnType() {
        return this.columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    @Column(name = "COLUMN_LENGTH", precision = 10, scale = 0)
    public Long getColumnLength() {
        return this.columnLength;
    }

    public void setColumnLength(Long columnLength) {
        this.columnLength = columnLength;
    }


    @Column(name = "COLUMN_PRECISION", precision = 8, scale = 0)
    public Integer getColumnPrecision() {
        return columnPrecision;
    }

    public void setColumnPrecision(Integer columnPrecision) {
        this.columnPrecision = columnPrecision;
    }

    @Column(name = "DEFAULT_VALUE", length = 128)
    public String getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Column(name = "ISNULLABLE", length = 1)
    public boolean isNullable() {
        return isNullable;
    }

    public void setNullable(boolean nullable) {
        isNullable = nullable;
    }

    @Column(name = "ISVIRTUAL", length = 1)
    public boolean isVirtual() {
        return isVirtual;
    }

    public void setVirtual(boolean virtual) {
        isVirtual = virtual;
    }

    @Column(name = "VALUE_FROM", precision = 8, scale = 0)
    public Integer getValueFrom() {
        return this.valueFrom;
    }

    public void setValueFrom(Integer valueFrom) {
        this.valueFrom = valueFrom;
    }

    @Column(name = "VALUE_TO", precision = 8, scale = 0)
    public Integer getValueTo() {
        return this.valueTo;
    }

    public void setValueTo(Integer valueTo) {
        this.valueTo = valueTo;
    }

    @Column(name = "FORMULA", length = 128)
    public String getFormula() {
        return this.formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    @Column(name = "IS_CREATED", length = 1)
    public boolean isCreated() {
        return isCreated;
    }

    public void setCreated(boolean created) {
        isCreated = created;
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