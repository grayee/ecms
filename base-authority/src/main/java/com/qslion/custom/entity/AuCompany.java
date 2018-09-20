package com.qslion.custom.entity;

import com.qslion.framework.entity.BaseEntity;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 实体类 - 公司
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "au_company")
public class AuCompany extends BaseEntity<Long> {

    private String companyNo;
    @NotBlank(message = "{company.name.notBlank}")
    private String companyName;
    private String companyFlag;
    private String companyType;
    private String companyLevel;
    private String shortName;
    private String area;
    private String linkMan;
    private String tel;
    private String fax;
    private String address;
    private String postalCode;
    @Email(message = "邮箱格式不正确")
    private String email;
    private String web;
    private String remark;
    private String enableStatus;
    private Date enableDate;

    @Basic
    @Column(name = "company_no", nullable = true, length = 64)
    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    @Basic
    @Column(name = "company_name", nullable = true, length = 255)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "company_flag", nullable = true, length = 64)
    public String getCompanyFlag() {
        return companyFlag;
    }

    public void setCompanyFlag(String companyFlag) {
        this.companyFlag = companyFlag;
    }

    @Basic
    @Column(name = "company_type", nullable = true, length = 64)
    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    @Basic
    @Column(name = "company_level", nullable = true, length = 3)
    public String getCompanyLevel() {
        return companyLevel;
    }

    public void setCompanyLevel(String companyLevel) {
        this.companyLevel = companyLevel;
    }

    @Basic
    @Column(name = "short_name", nullable = true, length = 64)
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Basic
    @Column(name = "area", nullable = true, length = 64)
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Basic
    @Column(name = "link_man", nullable = true, length = 64)
    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    @Basic
    @Column(name = "tel", nullable = true, length = 32)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "fax", nullable = true, length = 32)
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "postal_code", nullable = true, length = 32)
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "web", nullable = true, length = 255)
    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "enable_status", nullable = false, length = 1)
    public String getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(String enableStatus) {
        this.enableStatus = enableStatus;
    }

    @Basic
    @Column(name = "enable_date", nullable = false)
    public Date getEnableDate() {
        return enableDate;
    }

    public void setEnableDate(Date enableDate) {
        this.enableDate = enableDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuCompany auCompany = (AuCompany) o;

        if (id != auCompany.id) {
            return false;
        }
        if (companyNo != null ? !companyNo.equals(auCompany.companyNo) : auCompany.companyNo != null) {
            return false;
        }
        if (companyName != null ? !companyName.equals(auCompany.companyName)
            : auCompany.companyName != null) {
            return false;
        }
        if (companyFlag != null ? !companyFlag.equals(auCompany.companyFlag)
            : auCompany.companyFlag != null) {
            return false;
        }
        if (companyType != null ? !companyType.equals(auCompany.companyType)
            : auCompany.companyType != null) {
            return false;
        }
        if (companyLevel != null ? !companyLevel.equals(auCompany.companyLevel)
            : auCompany.companyLevel != null) {
            return false;
        }
        if (shortName != null ? !shortName.equals(auCompany.shortName) : auCompany.shortName != null) {
            return false;
        }
        if (area != null ? !area.equals(auCompany.area) : auCompany.area != null) {
            return false;
        }
        if (linkMan != null ? !linkMan.equals(auCompany.linkMan) : auCompany.linkMan != null) {
            return false;
        }
        if (tel != null ? !tel.equals(auCompany.tel) : auCompany.tel != null) {
            return false;
        }
        if (fax != null ? !fax.equals(auCompany.fax) : auCompany.fax != null) {
            return false;
        }
        if (address != null ? !address.equals(auCompany.address) : auCompany.address != null) {
            return false;
        }
        if (postalCode != null ? !postalCode.equals(auCompany.postalCode)
            : auCompany.postalCode != null) {
            return false;
        }
        if (email != null ? !email.equals(auCompany.email) : auCompany.email != null) {
            return false;
        }
        if (web != null ? !web.equals(auCompany.web) : auCompany.web != null) {
            return false;
        }
        if (remark != null ? !remark.equals(auCompany.remark) : auCompany.remark != null) {
            return false;
        }
        if (enableStatus != null ? !enableStatus.equals(auCompany.enableStatus)
            : auCompany.enableStatus != null) {
            return false;
        }
        if (enableDate != null ? !enableDate.equals(auCompany.enableDate)
            : auCompany.enableDate != null) {
            return false;
        }
        if (createDate != null ? !createDate.equals(auCompany.createDate)
            : auCompany.createDate != null) {
            return false;
        }
        if (modifyDate != null ? !modifyDate.equals(auCompany.modifyDate)
            : auCompany.modifyDate != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (companyNo != null ? companyNo.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (companyFlag != null ? companyFlag.hashCode() : 0);
        result = 31 * result + (companyType != null ? companyType.hashCode() : 0);
        result = 31 * result + (companyLevel != null ? companyLevel.hashCode() : 0);
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (linkMan != null ? linkMan.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (web != null ? web.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (enableStatus != null ? enableStatus.hashCode() : 0);
        result = 31 * result + (enableDate != null ? enableDate.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        return result;
    }
}
