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
@Table(name = "account_assist")
public class AccountAssist extends AttributeEntity {

    /**
     * 是否系统内置
     */
    private Boolean isSystem;
    
    /**
     * 会计科目
     */
    private List<AccountSubject> subjects;

    @ManyToMany(targetEntity = AccountSubject.class, mappedBy = "acctAssists", fetch = FetchType.LAZY)
    public List<AccountSubject> getSubjects() {
        return subjects;
    }

    public AccountAssist setSubjects(List<AccountSubject> subjects) {
        this.subjects = subjects;
        return this;
    }

    public Boolean getSystem() {
        return isSystem;
    }

    public AccountAssist setSystem(Boolean system) {
        isSystem = system;
        return this;
    }

    @Transient
    @Override
    public String getTableName() {
        return "accounting_assist_type";
    }
}
