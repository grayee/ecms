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
@Table(name = "dd_view")
public class DdView extends BaseEntity<Long> {

    private String code;
    private String name;
    private Byte viewType;
    private String conditionContent;
    private String businessClass;
    private String fieldLayout;
    private Short pageSize;
    private boolean isSystem;

    @Column(name = "CODE", length = 64)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "NAME", nullable = false, length = 64)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "VIEW_TYPE", nullable = false, precision = 2, scale = 0)
    public Byte getViewType() {
        return viewType;
    }

    public void setViewType(Byte viewType) {
        this.viewType = viewType;
    }

    @Column(name = "CONDITION_CONTENT", length = 512)
    public String getConditionContent() {
        return this.conditionContent;
    }

    public void setConditionContent(String conditionContent) {
        this.conditionContent = conditionContent;
    }

    @Column(name = "BUSINESS_CLASS", length = 512)
    public String getBusinessClass() {
        return businessClass;
    }

    public void setBusinessClass(String businessClass) {
        this.businessClass = businessClass;
    }

    @Column(name = "FIELD_LAYOUT", length = 2000)
    public String getFieldLayout() {
        return fieldLayout;
    }

    public void setFieldLayout(String fieldLayout) {
        this.fieldLayout = fieldLayout;
    }

    @Column(name = "PAGE_SIZE", precision = 3, scale = 0)
    public Short getPageSize() {
        return pageSize;
    }

    public void setPageSize(Short pageSize) {
        this.pageSize = pageSize;
    }

    @Column(name = "IS_SYSTEM", nullable = false, length = 1)
    public boolean isSystem() {
        return isSystem;
    }

    public void setSystem(boolean system) {
        isSystem = system;
    }
}