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
@Table(name = "dd_module_query_detail")
public class DdModuleQueryDetail extends BaseEntity<Long> {

    private String moduleId;
    private String tableName;
    private String viewCode;
    private String fieldId;
    private String type;
    private Integer seqNo;

    @Column(name = "MODULE_ID", nullable = false, length = 64)
    public String getModuleId() {
        return this.moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    @Column(name = "TABLE_NAME", nullable = false, length = 64)
    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Column(name = "VIEW-CODE", length = 64)
    public String getViewCode() {
        return this.viewCode;
    }

    public void setViewCode(String viewCode) {
        this.viewCode = viewCode;
    }

    @Column(name = "FIELD_ID", length = 64)
    public String getFieldId() {
        return this.fieldId;
    }

    public void setFieldId(String fieldid) {
        this.fieldId = fieldId;
    }

    @Column(name = "TYPE", length = 64)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "SEQ_NO", precision = 8, scale = 0)
    public Integer getSeqNo() {
        return this.seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

}