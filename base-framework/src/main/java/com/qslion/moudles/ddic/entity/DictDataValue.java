package com.qslion.moudles.ddic.entity;

import com.qslion.framework.entity.BaseEntity;
import com.qslion.framework.enums.EnableStatus;

import javax.persistence.*;

/**
 * 实体类 - 数据字典基础表
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "dict_data_value")
public class DictDataValue extends BaseEntity<Long> {

    /**
     * 编码
     */
    private String code;

    private String name;

    private String value;

    protected EnableStatus enableStatus;

    private String description;
    /**
     * 显示序号
     */
    private Integer orderNo;

    private DictDataType dictDataType;

    @ManyToOne
    @JoinColumn(name = "TYPE_ID")
    public DictDataType getDictDataType() {
        return dictDataType;
    }

    public DictDataValue setDictDataType(DictDataType dictDataType) {
        this.dictDataType = dictDataType;
        return this;
    }

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

    @Column(name = "VALUE", nullable = false, length = 64)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    @Enumerated
    @Column(name = "ENABLE_STATUS", nullable = false, length = 10)
    public EnableStatus getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(EnableStatus enableStatus) {
        this.enableStatus = enableStatus;
    }

    @Column(name = "DESCRIPTION", length = 256)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "ORDER_NO", nullable = false)
    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
}