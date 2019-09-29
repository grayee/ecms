package com.qslion.web.accounting.entity;

import com.qslion.framework.entity.AttributeEntity;

import javax.persistence.*;
import java.util.List;

/**
 * 辅助核算
 *
 * @author Gray.Z
 * @date 2019/8/22 20:50.
 */
@Entity
@Table(name = "accounting_assist_type")
public class AccountingAssistType extends AttributeEntity {

    /**
     * 是否系统内置
     */
    private Boolean isSystem;
    
    /**
     * 会计科目
     */
    private List<AccountingSubject> subjects;

    @ManyToMany(targetEntity = AccountingSubject.class, mappedBy = "assistTypes", fetch = FetchType.LAZY)
    public List<AccountingSubject> getSubjects() {
        return subjects;
    }

    public AccountingAssistType setSubjects(List<AccountingSubject> subjects) {
        this.subjects = subjects;
        return this;
    }

    public Boolean getSystem() {
        return isSystem;
    }

    public AccountingAssistType setSystem(Boolean system) {
        isSystem = system;
        return this;
    }

    @Transient
    @Override
    public String getTableName() {
        return "accounting_assist_type";
    }
}
