package com.qslion.moudles.ddic.entity;

import com.qslion.framework.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 实体类 - 约束
 * 该表用来描述系统中的约束
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "dd_constraint")
public class DdConstraint extends BaseEntity<Long> {

    private String constraintName;
    private String constraintType;
    private String tableName;
    private String columnName;
    private String conditionContent;
    private String refTableName;
    private String status;
    private String refColumnName;

    @Column(name = "CONSTRAINT_NAME", length = 64)
    public String getConstraintName() {
        return this.constraintName;
    }

    public void setConstraintName(String constraintName) {
        this.constraintName = constraintName;
    }

    @Column(name = "CONSTRAINT_TYPE", length = 1)
    public String getConstraintType() {
        return this.constraintType;
    }

    public void setConstraintType(String constraintType) {
        this.constraintType = constraintType;
    }

    @Column(name = "TABLE_NAME", length = 64)
    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Column(name = "COLUMN_NAME", length = 64)
    public String getColumnName() {
        return this.columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Column(name = "CONDITION_CONTENT", length = 255)
    public String getConditionContent() {
        return this.conditionContent;
    }

    public void setConditionContent(String conditionContent) {
        this.conditionContent = conditionContent;
    }

    @Column(name = "REF_TABLE_NAME", length = 64)
    public String getRefTableName() {
        return this.refTableName;
    }

    public void setRefTableName(String refTableName) {
        this.refTableName = refTableName;
    }

    @Column(name = "STATUS", length = 1)
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "REF_COLUMN_NAME", length = 64)
    public String getRefColumnName() {
        return this.refColumnName;
    }

    public void setRefColumnName(String refcolumnname) {
        this.refColumnName = refcolumnname;
    }
}