package com.qslion.web.accounting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qslion.web.accounting.enums.AcctBookType;
import com.qslion.web.accounting.enums.BalanceDirection;
import com.qslion.web.accounting.enums.SubjectType;
import com.qslion.framework.bean.DisplayField;
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
    @DisplayField(id = 1, title = "科目编码", align = "left", width = "5")
    private String subjectCode;
    /**
     * 科目名称
     */
    @DisplayField(id = 2, title = "科目名称", align = "left")
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
    @DisplayField(id = 4, title = "余额方向")
    private BalanceDirection balanceDir;

    /**
     * 4222科目层级：一级科目(4位)，二级科目，三级科目，四级科目
     */
    private Integer level;

    /**
     * 科目类型
     */
    @DisplayField(id = 3, title = "科目类型")
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

    /**
     * 是否系统内置
     */
    @DisplayField(id = 5, title = "是否内置")
    private Boolean isSystem;

    @ManyToMany(targetEntity = AccountingAssistType.class)
    @JoinTable(name = "accounting_subject_assist", joinColumns = {
            @JoinColumn(name = "subject_id")}, inverseJoinColumns = {@JoinColumn(name = "assist_id")})
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

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    @Basic
    @Column(name = "subject_name", nullable = false, length = 128)
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Basic
    @Column(name = "short_name", length = 128)
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Basic
    @Column(name = "english_name", length = 128)
    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    @Basic
    @Column(name = "balance_dir", nullable = false)
    public BalanceDirection getBalanceDir() {
        return balanceDir;
    }

    public void setBalanceDir(BalanceDirection balanceDir) {
        this.balanceDir = balanceDir;
    }

    @Basic
    @Column(name = "level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Enumerated
    @Basic
    @Column(name = "subject_type", nullable = false)
    public SubjectType getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
    }

    @Enumerated
    @Basic
    @Column(name = "book_type")
    public AcctBookType getAcctBookType() {
        return acctBookType;
    }

    public void setAcctBookType(AcctBookType acctBookType) {
        this.acctBookType = acctBookType;
    }


    @Basic
    @Column(name = "parent_id")
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Enumerated
    @Basic
    @Column(name = "enable_status")
    public EnableStatus getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(EnableStatus enableStatus) {
        this.enableStatus = enableStatus;
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

    public Boolean getSystem() {
        return isSystem;
    }

    public void setSystem(Boolean system) {
        isSystem = system;
    }
}
