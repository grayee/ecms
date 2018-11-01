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
@Table(name = "dd_module_query")
public class DdModuleQuery extends BaseEntity<Long> {


    private String moduleid;
    private String conditionContent;
    private String url;
    private String queryfieldsconf;


    @Column(name = "MODULEID", unique = true, nullable = false, length = 64)
    public String getModuleid() {
        return this.moduleid;
    }

    public void setModuleid(String moduleid) {
        this.moduleid = moduleid;
    }

    @Column(name = "CONDITION_CONTENT", length = 512)
    public String getConditionContent() {
        return this.conditionContent;
    }

    public void setConditionContent(String conditionContent) {
        this.conditionContent = conditionContent;
    }

    @Column(name = "URL", nullable = false, length = 512)
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "QUERYFIELDSCONF", length = 512)
    public String getQueryfieldsconf() {
        return this.queryfieldsconf;
    }

    public void setQueryfieldsconf(String queryfieldsconf) {
        this.queryfieldsconf = queryfieldsconf;
    }

}