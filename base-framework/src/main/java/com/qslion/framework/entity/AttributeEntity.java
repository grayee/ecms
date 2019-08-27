package com.qslion.framework.entity;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * 实体类-基类
 *
 * @author Gray.Z
 * @date 2018/4/3 20:25.
 */
@MappedSuperclass
public abstract class AttributeEntity extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    private Long referenceId;

    private String key;

    private String value;

    private String valueType;

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    @Transient
    public abstract String getTableName();
}
