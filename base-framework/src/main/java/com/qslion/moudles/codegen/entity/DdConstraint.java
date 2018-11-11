package com.qslion.moudles.codegen.entity;

import com.qslion.framework.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    private DdTable table;
    private String constraintName;
    private ConstraintType constraintType;
    private String tableName;
    private String columnName;
    private String refTableName;
    private String refColumnName;
    private String refConstraintName;
    private String conditionContent;
    private Short keySeq;
    private String status;

    public DdConstraint(ConstraintType constraintType) {
        this.constraintType = constraintType;
    }

    public enum ConstraintType {
        PRIMARY_KEY, FOREIGN_KEY_IMPORTED, FOREIGN_KEY_EXPORTED, UNIQUE_KEY
    }

    @ManyToOne
    @JoinColumn(name = "TABLE_ID")
    public DdTable getTable() {
        return table;
    }

    public DdConstraint setTable(DdTable table) {
        this.table = table;
        return this;
    }

    @Column(name = "CONSTRAINT_NAME", length = 64)
    public String getConstraintName() {
        return this.constraintName;
    }

    public void setConstraintName(String constraintName) {
        this.constraintName = constraintName;
    }

    @Enumerated
    @Column(name = "CONSTRAINT_TYPE", length = 1)
    public ConstraintType getConstraintType() {
        return this.constraintType;
    }

    public void setConstraintType(ConstraintType constraintType) {
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

    @Column(name = "REF_CONSTRAINT_NAME")
    public String getRefConstraintName() {
        return refConstraintName;
    }

    public DdConstraint setRefConstraintName(String refConstraintName) {
        this.refConstraintName = refConstraintName;
        return this;
    }

    @Column(name = "KEY_SEQ")
    public Short getKeySeq() {
        return keySeq;
    }

    public DdConstraint setKeySeq(Short keySeq) {
        this.keySeq = keySeq;
        return this;
    }
}