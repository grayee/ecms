package com.qslion.web.sob.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qslion.framework.bean.DisplayField;
import com.qslion.framework.entity.BaseEntity;
import com.qslion.web.accounting.enums.GaapType;
import com.qslion.web.accounting.enums.VatType;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 账套（set of books SOB）
 * https://blog.csdn.net/qq_32423845/article/details/88384263
 *
 * @author Gray.Z
 * @date 2019/8/22 20:50.
 */
@Entity
@Table(name = "account_book_set")
public class AccountSet extends BaseEntity<Long> {

    /**
     * 单位名称
     */
    @DisplayField(id = 1, title = "单位名称")
    private String companyName;
    /**
     * 账套名称
     */
    @DisplayField(id = 2, title = "账套名称")
    private String sobName;
    /**
     * 账套期间
     */
    @DisplayField(id = 3, title = "账套期间")
    @JsonFormat(pattern = "yyyy-MM")
    private Date acctPeriod;

    /**
     * 增值税种类
     */
    @DisplayField(id = 4, title = "增值税种类")
    private VatType vatType;

    /**
     * 会计原则类型
     */
    @DisplayField(id = 5, title = "会计准则类型")
    private GaapType gaapType;

    /**
     * 是否系统内置
     */
    @DisplayField(id = 6, title = "是否内置")
    private Boolean isSystem;

    /**
     * 租户
     */
    private String tenant;

    private String dbUrl;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSobName() {
        return sobName;
    }

    public void setSobName(String sobName) {
        this.sobName = sobName;
    }

    public Date getAcctPeriod() {
        return acctPeriod;
    }

    public void setAcctPeriod(Date acctPeriod) {
        this.acctPeriod = acctPeriod;
    }

    public VatType getVatType() {
        return vatType;
    }

    public void setVatType(VatType vatType) {
        this.vatType = vatType;
    }

    public GaapType getGaapType() {
        return gaapType;
    }

    public void setGaapType(GaapType gaapType) {
        this.gaapType = gaapType;
    }

    public Boolean getSystem() {
        return isSystem;
    }

    public void setSystem(Boolean system) {
        isSystem = system;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }
}
