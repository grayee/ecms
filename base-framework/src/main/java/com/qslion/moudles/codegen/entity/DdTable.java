package com.qslion.moudles.codegen.entity;

import com.google.common.collect.Lists;
import com.qslion.framework.entity.BaseEntity;
import com.qslion.moudles.codegen.entity.DdConstraint.ConstraintType;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 实体类 - 数据库表
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "dd_table")
public class DdTable extends BaseEntity<Long> {

    private String catalog;
    private String schema;
    private String tableName;
    private String tableType;
    private String displayName;
    private String category;
    private String subCategory;
    private boolean isVirtual;
    private String checkExpression;
    private boolean isCreated;
    private String status;
    private String remark;

    private List<DdColumn> columns = Lists.newArrayList();

    private List<DdConstraint> constraints = Lists.newArrayList();

    @OneToMany
    @JoinColumn(name = "TABLE_ID")
    public List<DdColumn> getColumns() {
        return columns;
    }

    public DdTable setColumns(List<DdColumn> columns) {
        this.columns = columns;
        return this;
    }

    @OneToMany
    @JoinColumn(name = "TABLE_ID")
    public List<DdConstraint> getConstraints() {
        return constraints;
    }

    public DdTable setConstraints(List<DdConstraint> constraints) {
        this.constraints = constraints;
        return this;
    }

    public String getCatalog() {
        return catalog;
    }

    public DdTable setCatalog(String catalog) {
        this.catalog = catalog;
        return this;
    }

    public String getSchema() {
        return schema;
    }

    public DdTable setSchema(String schema) {
        this.schema = schema;
        return this;
    }

    @Column(name = "TABLE_NAME", nullable = false)
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Column(name = "TABLE_TYPE", nullable = false, length = 8)
    public String getTableType() {
        return tableType;
    }

    public DdTable setTableType(String tableType) {
        this.tableType = tableType;
        return this;
    }

    @Column(name = "DISPLAY_NAME", nullable = false, length = 128)
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Column(name = "CATEGORY", nullable = false, length = 1)
    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(name = "SUB_CATEGORY", length = 128)
    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    @Column(name = "IS_VIRTUAL", length = 1)
    public boolean isVirtual() {
        return isVirtual;
    }

    public void setVirtual(boolean virtual) {
        isVirtual = virtual;
    }

    @Column(name = "CHECK_EXPRESSION", length = 128)
    public String getCheckExpression() {
        return checkExpression;
    }

    public void setCheckExpression(String checkExpression) {
        this.checkExpression = checkExpression;
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


    @Column(name = "REMARK", length = 1024)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 判断指定列名是否为主键
     *
     * @param columnName 要判断的列名称
     * @return 如果指定列为主键则返回true，否则返回false
     */
    public boolean isKey(String columnName, ConstraintType constraintType) {
        boolean result = false;
        for (DdConstraint constraint : constraints) {
            if (constraint.getColumnName().equalsIgnoreCase(columnName) && constraint.getConstraintType() == constraintType) {
                result = true;
                break;
            }
        }
        return result;
    }


}