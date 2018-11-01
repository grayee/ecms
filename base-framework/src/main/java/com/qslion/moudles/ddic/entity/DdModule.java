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
@Table(name = "dd_module")
public class DdModule extends BaseEntity<Long> {


    private String name;
    private String templateid;
    private String issystem;

    @Column(name = "NAME", nullable = false, length = 64)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "TEMPLATEID", nullable = false, length = 64)
    public String getTemplateid() {
        return this.templateid;
    }

    public void setTemplateid(String templateid) {
        this.templateid = templateid;
    }

    @Column(name = "ISSYSTEM", nullable = false, length = 1)
    public String getIssystem() {
        return this.issystem;
    }

    public void setIssystem(String issystem) {
        this.issystem = issystem;
    }

}