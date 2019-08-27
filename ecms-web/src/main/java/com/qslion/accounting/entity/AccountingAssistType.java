package com.qslion.accounting.entity;

import com.qslion.framework.entity.AttributeEntity;

import javax.persistence.*;

/**
 * 会计科目表
 *
 * @author Gray.Z
 * @date 2019/8/22 20:50.
 */
@Entity
@Table(name = "accounting_assist_type")
public class AccountingAssistType extends AttributeEntity {

    /**
     * 会计科目
     */
    private AccountingSubject accountingSubject;

    @ManyToOne
    @JoinColumn(name = "REFERENCE_ID")
    public AccountingSubject getAccountingSubject() {
        return accountingSubject;
    }

    public void setAccountingSubject(AccountingSubject accountingSubject) {
        this.accountingSubject = accountingSubject;
    }

    @Transient
    @Override
    public String getTableName() {
        return "accounting_assist_type";
    }
}
