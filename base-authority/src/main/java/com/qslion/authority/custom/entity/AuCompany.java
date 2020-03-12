package com.qslion.authority.custom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qslion.authority.core.entity.AbstractOrgEntity;
import com.qslion.authority.core.enums.AuOrgType;
import com.qslion.framework.bean.DisplayField;
import com.qslion.framework.util.ValidatorUtils.AddGroup;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 实体类 - 公司
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@ApiModel(description = "公司对象Company")
@Entity
@Table(name = "au_company")
public class AuCompany extends AbstractOrgEntity {

    @DisplayField(id = 1, title = "{display.column.title.company.no}")
    private String companyNo;
    @DisplayField(id = 2, title = "公司名称")
    @NotBlank(message = "{company.name.notBlank}")
    private String companyName;
    @DisplayField(id = 5, title = "公司标识")
    private String companyFlag;
    private String companyType;
    private Short companyLevel;
    @DisplayField(id = 3, title = "公司简称")
    private String shortName;
    private String area;
    private String linkMan;
    @DisplayField(id = 9, title = "联系电话")
    @Pattern(regexp = "^1([345789])\\d{9}$", message = "座机号码格式错误")
    @NotBlank(message = "手机号码不能为空", groups = {AddGroup.class})
    private String mobilePhone;

    @DisplayField(id = 10, title = "座机电话")
    @NotBlank(message = "座机号码不能为空", groups = {AddGroup.class})
    private String telPhone;

    private String fax;
    @DisplayField(id = 11, title = "公司地址")
    private String address;
    @DisplayField(id = 6, title = "公司邮编")
    private String postalCode;
    @DisplayField(id = 8, title = "邮箱")
    @Email(message = "邮箱格式不正确")
    private String email;
    @DisplayField(id = 7, title = "公司网址")
    private String webSite;


    @Basic
    @Column(name = "company_no", length = 64)
    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    @Basic
    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "company_flag", length = 64)
    public String getCompanyFlag() {
        return companyFlag;
    }

    public void setCompanyFlag(String companyFlag) {
        this.companyFlag = companyFlag;
    }

    @Basic
    @Column(name = "company_type", length = 64)
    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    @Basic
    @Column(name = "company_level", length = 3)
    public Short getCompanyLevel() {
        return companyLevel;
    }

    public void setCompanyLevel(Short companyLevel) {
        this.companyLevel = companyLevel;
    }

    @Basic
    @Column(name = "short_name", length = 64)
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Basic
    @Column(name = "area", length = 64)
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Basic
    @Column(name = "link_man", length = 64)
    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    @Basic
    @Column(name = "tel_phone", length = 32)
    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    @Basic
    @Column(name = "mobile_phone", length = 11)
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Basic
    @Column(name = "fax", length = 32)
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "postal_code", length = 32)
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Basic
    @Column(name = "email", length = 64)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "web_site", length = 64)
    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        AuCompany auCompany = (AuCompany) o;

        if (companyNo != null ? !companyNo.equals(auCompany.companyNo) : auCompany.companyNo != null) {
            return false;
        }
        if (companyName != null ? !companyName.equals(auCompany.companyName) : auCompany.companyName != null) {
            return false;
        }
        if (companyFlag != null ? !companyFlag.equals(auCompany.companyFlag) : auCompany.companyFlag != null) {
            return false;
        }
        if (companyType != null ? !companyType.equals(auCompany.companyType) : auCompany.companyType != null) {
            return false;
        }
        if (companyLevel != null ? !companyLevel.equals(auCompany.companyLevel) : auCompany.companyLevel != null) {
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
        if (telPhone != null ? !telPhone.equals(auCompany.telPhone) : auCompany.telPhone != null) {
            return false;
        }
        if (fax != null ? !fax.equals(auCompany.fax) : auCompany.fax != null) {
            return false;
        }
        if (address != null ? !address.equals(auCompany.address) : auCompany.address != null) {
            return false;
        }
        if (postalCode != null ? !postalCode.equals(auCompany.postalCode) : auCompany.postalCode != null) {
            return false;
        }
        if (email != null ? !email.equals(auCompany.email) : auCompany.email != null) {
            return false;
        }
        if (webSite != null ? !webSite.equals(auCompany.webSite) : auCompany.webSite != null) {
            return false;
        }
        return remark != null ? remark.equals(auCompany.remark) : auCompany.remark == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (companyNo != null ? companyNo.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (companyFlag != null ? companyFlag.hashCode() : 0);
        result = 31 * result + (companyType != null ? companyType.hashCode() : 0);
        result = 31 * result + (companyLevel != null ? companyLevel.hashCode() : 0);
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (linkMan != null ? linkMan.hashCode() : 0);
        result = 31 * result + (telPhone != null ? telPhone.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (webSite != null ? webSite.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
    @JsonIgnore
    @Transient
    @Override
    public AuOrgType getOrgType() {
        return AuOrgType.COMPANY;
    }
    @JsonIgnore
    @Transient
    @Override
    public String getOrgName() {
        return companyName;
    }
}
