package com.qslion.moudles.codegen.entity;

import com.qslion.framework.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 实体类 - 表列
 * 该表存储了系统所涉及的数据对象的所有字段，并对字段属性进行描述
 * 参考 <code>ResultSetMetaDataBase.ColumnMetaData</code>
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "dd_column")
public class DdColumn extends BaseEntity<Long> {

    private DdTable table;
    private String tableName;
    private String columnName;
    private String displayName;
    private String columnType;
    private Long length;
    private Integer precision;
    private Integer scale;
    private String defaultValue;
    private boolean isNullable;

    /**
     * 标识该列是否为主键
     */
    private boolean isPrimaryKey = false;
    /**
     * 标识该列是否外键列（参照其他表的键）
     */
    private boolean isImportedKey = false;
    /**
     * 标识该列是否外键列（被其他表参照的键）
     */
    private boolean isExportedKey = false;

    /**
     * 标识该列是否为自增列
     */
    private boolean isAutoIncrement = false;

    private boolean isUniqueKey;
    private boolean isVirtual;
    private Integer valueFrom;
    private Integer valueTo;
    private String formula;
    private boolean isCreated;
    private String status;
    private String category;
    private String remark;

    @ManyToOne
    @JoinColumn(name = "TABLE_ID")
    public DdTable getTable() {
        return table;
    }

    public DdColumn setTable(DdTable table) {
        this.table = table;
        return this;
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

    @Column(name = "LENGTH", precision = 10, scale = 0)
    public Long getLength() {
        return this.length;
    }

    public void setLength(Long length) {
        this.length = length;
    }


    @Column(name = "PRECISION", precision = 8, scale = 0)
    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    @Column(name = "SCALE", precision = 19, scale = 0)
    public Integer getScale() {
        return scale;
    }

    public DdColumn setScale(Integer scale) {
        this.scale = scale;
        return this;
    }

    @Column(name = "IS_UNIQUE_KEY", length = 1)
    public boolean isUniqueKey() {
        return isUniqueKey;
    }

    public DdColumn setUniqueKey(boolean isUniqueKey) {
        isUniqueKey = isUniqueKey;
        return this;
    }

    @Column(name = "DEFAULT_VALUE", length = 128)
    public String getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Column(name = "IS_NULLABLE", length = 1)
    public boolean isNullable() {
        return isNullable;
    }

    public void setNullable(boolean nullable) {
        isNullable = nullable;
    }

    @Column(name = "IS_VIRTUAL", length = 1)
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

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public DdColumn setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
        return this;
    }

    public boolean isImportedKey() {
        return isImportedKey;
    }

    public DdColumn setImportedKey(boolean importedKey) {
        isImportedKey = importedKey;
        return this;
    }

    public boolean isExportedKey() {
        return isExportedKey;
    }

    public DdColumn setExportedKey(boolean exportedKey) {
        isExportedKey = exportedKey;
        return this;
    }

    public boolean isAutoIncrement() {
        return isAutoIncrement;
    }

    public DdColumn setAutoIncrement(boolean autoIncrement) {
        isAutoIncrement = autoIncrement;
        return this;
    }
}