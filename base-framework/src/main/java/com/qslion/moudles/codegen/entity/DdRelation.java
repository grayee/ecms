package com.qslion.moudles.codegen.entity;

import com.qslion.framework.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * 实体类 - 数据库表关系
 * 该表存储了系统所涉及的各表之间的关系，并对表间关系进行描述
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "dd_relation")
public class DdRelation extends BaseEntity<Long> {

    private String relationName;
    private RelationType relationType;
    private String displayName;
    private String pkTable;
    private String pkColumn;
    private String fkTable;
    private String fkColumn;
    private boolean isCreated;
    private String status;
    private String remark;

    private enum RelationType {
        ONE_TO_ONE, ONE_TO_MANY, MANY_TO_MANY
    }

    @Column(name = "RELATION_NAME", nullable = false, length = 128)
    public String getRelationName() {
        return this.relationName;
    }

    public void setRelationName(String relationname) {
        this.relationName = relationName;
    }

    @Enumerated
    @Column(name = "RELATION_TYPE", nullable = false, length = 1)
    public RelationType getRelationType() {
        return relationType;
    }

    public DdRelation setRelationType(RelationType relationType) {
        this.relationType = relationType;
        return this;
    }

    @Column(name = "DISPLAY_NAME", length = 128)
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Column(name = "PK_TABLE", nullable = false, length = 128)
    public String getPkTable() {
        return pkTable;
    }

    public void setPkTable(String pkTable) {
        this.pkTable = pkTable;
    }

    @Column(name = "PK_COLUMN", nullable = false, length = 128)
    public String getPkColumn() {
        return pkColumn;
    }

    public void setPkColumn(String pkColumn) {
        this.pkColumn = pkColumn;
    }

    @Column(name = "FK_TABLE", nullable = false, length = 128)
    public String getFkTable() {
        return fkTable;
    }

    public void setFkTable(String fkTable) {
        this.fkTable = fkTable;
    }

    @Column(name = "FK_COLUMN", nullable = false, length = 128)
    public String getFkColumn() {
        return fkColumn;
    }

    public void setFkColumn(String fkColumn) {
        this.fkColumn = fkColumn;
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

}