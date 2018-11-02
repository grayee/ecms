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
@Table(name = "dd_module_master")
public class DdModuleMaster extends BaseEntity<Long> {

    private String moduleId;
    private String tableName;
    private String tabName;
    private String param;
    private String tranFlag;
    private String url;

    @Column(name = "MODULE_ID", unique = true, nullable = false, length = 64)
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

    @Column(name = "TAB_NAME", nullable = false, length = 64)
    public String getTabName() {
        return this.tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    @Column(name = "PARAM", length = 512)
    public String getParam() {
        return this.param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Column(name = "TRAN_FLAG", length = 1)
    public String getTranFlag() {
        return this.tranFlag;
    }

    public void setTranFlag(String tranFlag) {
        this.tranFlag = tranFlag;
    }

    @Column(name = "URL", nullable = false, length = 512)
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}