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
@Table(name = "dd_module_common")
public class DdModuleCommon extends BaseEntity<Long> {

    private String moduleid;
    private String tablename;
    private String param;
    private String url;

    @Column(name = "MODULEID", unique = true, nullable = false, length = 64)
    public String getModuleid() {
        return this.moduleid;
    }

    public void setModuleid(String moduleid) {
        this.moduleid = moduleid;
    }

    @Column(name = "TABLENAME", nullable = false, length = 64)
    public String getTablename() {
        return this.tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    @Column(name = "PARAM", length = 512)
    public String getParam() {
        return this.param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Column(name = "URL", nullable = false, length = 512)
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}