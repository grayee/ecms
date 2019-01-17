package com.qslion.custom.entity;

import com.qslion.core.entity.AuParty;
import com.qslion.core.entity.PartyEntity;
import com.qslion.core.enums.AuPartyType;
import com.qslion.framework.util.ValidatorUtils.AddGroup;
import io.swagger.annotations.ApiModel;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 实体类 - 公司
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@ApiModel(description="公司对象Company")
@Entity
@Table(name = "au_company")
public class AuCompany extends PartyEntity {

    private String companyNo;
    @NotBlank(message = "{company.name.notBlank}")
    private String companyName;
    private String companyFlag;
    private String companyType;
    private Short companyLevel;
    private String shortName;
    private String area;
    private String linkMan;
    @Pattern(regexp = "^1([345789])\\d{9}$", message = "座机号码格式错误")
    @NotBlank(message = "座机号码不能为空", groups = {AddGroup.class})
    private String tel;
    private String fax;
    private String address;
    private String postalCode;
    @Email(message = "邮箱格式不正确")
    private String email;
    private String webSite;
    private String remark;

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
    @Column(name = "tel", length = 32)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        if (tel != null ? !tel.equals(auCompany.tel) : auCompany.tel != null) {
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
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (webSite != null ? webSite.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

    @Override
    public AuParty buildAuParty() {
        auParty = new AuParty();
        auParty.setAuPartyType(AuPartyType.COMPANY);
        auParty.setName(getCompanyName());
        auParty.setRemark(getRemark());
        auParty.setEnableStatus(getEnableStatus());
        auParty.setInherit(true);
        auParty.setReal(true);
        return auParty;
    }
}
