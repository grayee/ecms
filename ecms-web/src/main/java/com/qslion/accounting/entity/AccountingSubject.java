package com.qslion.accounting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qslion.accounting.enums.AcctBookType;
import com.qslion.accounting.enums.BalanceDirection;
import com.qslion.accounting.enums.SubjectType;
import com.qslion.framework.entity.BaseEntity;
import com.qslion.framework.enums.EnableStatus;

import javax.persistence.*;
import java.util.List;

/**
 * 会计科目表
 *
 * @author Gray.Z
 * @date 2019/8/22 20:50.
 */
@Entity
@Table(name = "accounting_subject")
public class AccountingSubject extends BaseEntity<Long> {

    private Long parentId;
    /**
     * 科目编码
     */
    private String subjectCode;
    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 科目简称
     */
    private String shortName;

    /**
     * 科目英文名称
     */
    private String englishName;
    /**
     * 余额方向
     */
    private BalanceDirection balanceDir;

    /**
     * 4222科目层级：一级科目(4位)，二级科目，三级科目，四级科目
     */
    private Integer level;

    /**
     * 科目类型
     */
    private SubjectType subjectType;

    /**
     * 逗号分隔存储多个
     * https://baijiahao.baidu.com/s?id=1620159106860707000&wfr=spider&for=pc
     * 帐簿类型：普通多栏、增值税多栏、三栏
     */
    private AcctBookType acctBookType;

    /**
     * 数量核算
     */
    private Boolean isAmountCheck;

    /**
     * 计量单位
     */
    private String amountUnit;

    /**
     * 外币核算
     */
    private Boolean isForeignCurrencyCheck;

    /**
     * 币种
     */
    private String currency;

    /**
     * 辅助核算
     */
    private Boolean isAssistCheck;

    /**
     * 辅助核算项
     */
    @JsonIgnore
    private List<AccountingAssistType> assistTypes;

    private EnableStatus enableStatus;

    @OneToMany
    @JoinColumn(name = "REFERENCE_ID")
    public List<AccountingAssistType> getAssistTypes() {
        return assistTypes;
    }

    public void setAssistTypes(List<AccountingAssistType> assistTypes) {
        this.assistTypes = assistTypes;
    }

    @Basic
    @Column(name = "subject_code", nullable = false, length = 32)
    public String getSubjectCode() {
        return subjectCode;
    }

    public AccountingSubject setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
        return this;
    }

    @Basic
    @Column(name = "subject_name", nullable = false, length = 128)
    public String getSubjectName() {
        return subjectName;
    }

    public AccountingSubject setSubjectName(String subjectName) {
        this.subjectName = subjectName;
        return this;
    }

    @Basic
    @Column(name = "short_name", nullable = false, length = 128)
    public String getShortName() {
        return shortName;
    }

    public AccountingSubject setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    @Basic
    @Column(name = "english_name", nullable = false, length = 128)
    public String getEnglishName() {
        return englishName;
    }

    public AccountingSubject setEnglishName(String englishName) {
        this.englishName = englishName;
        return this;
    }

    @Basic
    @Column(name = "balance_dir", nullable = false)
    public BalanceDirection getBalanceDir() {
        return balanceDir;
    }

    public AccountingSubject setBalanceDir(BalanceDirection balanceDir) {
        this.balanceDir = balanceDir;
        return this;
    }

    @Basic
    @Column(name = "level", nullable = false)
    public Integer getLevel() {
        return level;
    }

    public AccountingSubject setLevel(Integer level) {
        this.level = level;
        return this;
    }

    @Enumerated
    @Basic
    @Column(name = "subject_type", nullable = false)
    public SubjectType getSubjectType() {
        return subjectType;
    }

    public AccountingSubject setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
        return this;
    }

    @Enumerated
    @Basic
    @Column(name = "book_type", nullable = false)
    public AcctBookType getAcctBookType() {
        return acctBookType;
    }

    public AccountingSubject setAcctBookType(AcctBookType acctBookType) {
        this.acctBookType = acctBookType;
        return this;
    }


    @Basic
    @Column(name = "parent_id", nullable = false)
    public Long getParentId() {
        return parentId;
    }

    public AccountingSubject setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    @Enumerated
    @Basic
    @Column(name = "enable_status", nullable = false)
    public EnableStatus getEnableStatus() {
        return enableStatus;
    }

    public AccountingSubject setEnableStatus(EnableStatus enableStatus) {
        this.enableStatus = enableStatus;
        return this;
    }

    public Boolean getAmountCheck() {
        return isAmountCheck;
    }

    public void setAmountCheck(Boolean amountCheck) {
        isAmountCheck = amountCheck;
    }

    public String getAmountUnit() {
        return amountUnit;
    }

    public void setAmountUnit(String amountUnit) {
        this.amountUnit = amountUnit;
    }

    public Boolean getForeignCurrencyCheck() {
        return isForeignCurrencyCheck;
    }

    public void setForeignCurrencyCheck(Boolean foreignCurrencyCheck) {
        isForeignCurrencyCheck = foreignCurrencyCheck;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getAssistCheck() {
        return isAssistCheck;
    }

    public void setAssistCheck(Boolean assistCheck) {
        isAssistCheck = assistCheck;
    }
}
