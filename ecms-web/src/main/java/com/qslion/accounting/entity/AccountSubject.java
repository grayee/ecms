package com.qslion.accounting.entity;

import com.qslion.accounting.enums.BalanceDirection;
import com.qslion.accounting.enums.SubjectType;
import com.qslion.framework.entity.BaseEntity;
import com.qslion.framework.enums.EnableStatus;

import javax.persistence.*;

/**
 * 会计科目表
 *
 * @author Gray.Z
 * @date 2019/8/22 20:50.
 */
@Entity
@Table(name = "acc_subject")
public class AccountSubject extends BaseEntity<Long> {

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
    private String bookType;


    private EnableStatus enableStatus;

    @Basic
    @Column(name = "subject_code", nullable = false, length = 32)
    public String getSubjectCode() {
        return subjectCode;
    }

    public AccountSubject setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
        return this;
    }

    @Basic
    @Column(name = "subject_name", nullable = false, length = 128)
    public String getSubjectName() {
        return subjectName;
    }

    public AccountSubject setSubjectName(String subjectName) {
        this.subjectName = subjectName;
        return this;
    }

    @Basic
    @Column(name = "short_name", nullable = false, length = 128)
    public String getShortName() {
        return shortName;
    }

    public AccountSubject setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    @Basic
    @Column(name = "english_name", nullable = false, length = 128)
    public String getEnglishName() {
        return englishName;
    }

    public AccountSubject setEnglishName(String englishName) {
        this.englishName = englishName;
        return this;
    }

    @Basic
    @Column(name = "balance_dir", nullable = false)
    public BalanceDirection getBalanceDir() {
        return balanceDir;
    }

    public AccountSubject setBalanceDir(BalanceDirection balanceDir) {
        this.balanceDir = balanceDir;
        return this;
    }

    @Basic
    @Column(name = "level", nullable = false)
    public Integer getLevel() {
        return level;
    }

    public AccountSubject setLevel(Integer level) {
        this.level = level;
        return this;
    }

    @Enumerated
    @Basic
    @Column(name = "subject_type", nullable = false)
    public SubjectType getSubjectType() {
        return subjectType;
    }

    public AccountSubject setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
        return this;
    }

    @Basic
    @Column(name = "book_type", nullable = false)
    public String getBookType() {
        return bookType;
    }

    public AccountSubject setBookType(String bookType) {
        this.bookType = bookType;
        return this;
    }

    @Basic
    @Column(name = "parent_id", nullable = false)
    public Long getParentId() {
        return parentId;
    }

    public AccountSubject setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    @Enumerated
    @Basic
    @Column(name = "enable_status", nullable = false)
    public EnableStatus getEnableStatus() {
        return enableStatus;
    }

    public AccountSubject setEnableStatus(EnableStatus enableStatus) {
        this.enableStatus = enableStatus;
        return this;
    }
}
