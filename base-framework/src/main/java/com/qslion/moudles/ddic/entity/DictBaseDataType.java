package com.qslion.moudles.ddic.entity;

import com.qslion.framework.entity.BaseEntity;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 实体类 - 数据字典类型
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "dict_base_data_type")
public class DictBaseDataType extends BaseEntity<Long> {

    private String name;
    private String description;
    private boolean isSystem;
    private List<DictBaseData> dictBaseDataList;

    @OneToMany
    @JoinColumn(name = "TYPE_ID")
    public List<DictBaseData> getDictBaseDataList() {
        return dictBaseDataList;
    }

    public DictBaseDataType setDictBaseDataList(
        List<DictBaseData> dictBaseDataList) {
        this.dictBaseDataList = dictBaseDataList;
        return this;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "NAME", nullable = false, length = 64)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "IS_SYSTEM", nullable = false, length = 1)
    public boolean isSystem() {
        return isSystem;
    }

    public void setSystem(boolean system) {
        isSystem = system;
    }
}