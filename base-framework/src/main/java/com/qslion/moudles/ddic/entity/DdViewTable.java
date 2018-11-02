package com.qslion.moudles.ddic.entity;

import com.qslion.framework.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * DdViewTable entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "dd_view_table")
public class DdViewTable extends BaseEntity<Long> {

    private String viewId;
    private String viewCode;
    private String tableId;
    private String keyName;


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

    @Column(name = "TABLE_ID", nullable = false, length = 32)
    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    @Column(name = "KEY_NAME", length = 64)
    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }
}