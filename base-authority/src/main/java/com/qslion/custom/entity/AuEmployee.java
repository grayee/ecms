package com.qslion.custom.entity;

import com.qslion.core.entity.AuParty;
import com.qslion.core.entity.PartyEntity;
import com.qslion.core.enums.AuPartyType;
import com.qslion.framework.bean.DisplayField;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 实体类 - 雇员
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "au_employee")
public class AuEmployee extends PartyEntity {

    @DisplayField(id = 1, title = "员工编号")
    private String personNo;
    @DisplayField(id = 2, title = "员工姓名")
    private String personName;
    @DisplayField(id = 3, title = "英文名称")
    private String englishName;
    private String personType;
    @DisplayField(id = 4, title = "性别")
    private String sex;
    @DisplayField(id = 5, title = "手机号码")
    private String mobile;
    @DisplayField(id = 6, title = "电话")
    private String tel;
    @DisplayField(id = 7, title = "邮箱")
    private String email;
    @DisplayField(id = 8, title = "联系地址")
    private String address;
    private String postalCode;
    private String remark;


    @Basic
    @Column(name = "person_no", nullable = true, length = 64)
    public String getPersonNo() {
        return personNo;
    }

    public void setPersonNo(String personNo) {
        this.personNo = personNo;
    }

    @Basic
    @Column(name = "person_name", nullable = true, length = 64)
    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    @Basic
    @Column(name = "english_name", nullable = true, length = 64)
    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    @Basic
    @Column(name = "person_type", nullable = true, length = 64)
    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    @Basic
    @Column(name = "sex", nullable = true, length = 1)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "mobile", nullable = true, length = 32)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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
    @Column(name = "email", nullable = true, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    @Column(name = "remark", nullable = true, length = 255)
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

        AuEmployee that = (AuEmployee) o;

        if (id != that.id) {
            return false;
        }
        if (personNo != null ? !personNo.equals(that.personNo) : that.personNo != null) {
            return false;
        }
        if (personName != null ? !personName.equals(that.personName) : that.personName != null) {
            return false;
        }
        if (englishName != null ? !englishName.equals(that.englishName) : that.englishName != null) {
            return false;
        }
        if (personType != null ? !personType.equals(that.personType) : that.personType != null) {
            return false;
        }
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) {
            return false;
        }
        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) {
            return false;
        }
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) {
            return false;
        }
        if (email != null ? !email.equals(that.email) : that.email != null) {
            return false;
        }
        if (address != null ? !address.equals(that.address) : that.address != null) {
            return false;
        }
        if (postalCode != null ? !postalCode.equals(that.postalCode) : that.postalCode != null) {
            return false;
        }
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) {
            return false;
        }
        if (enableStatus != null ? !enableStatus.equals(that.enableStatus)
            : that.enableStatus != null) {
            return false;
        }
        if (enableDate != null ? !enableDate.equals(that.enableDate) : that.enableDate != null) {
            return false;
        }
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) {
            return false;
        }
        if (modifyDate != null ? !modifyDate.equals(that.modifyDate) : that.modifyDate != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (personNo != null ? personNo.hashCode() : 0);
        result = 31 * result + (personName != null ? personName.hashCode() : 0);
        result = 31 * result + (englishName != null ? englishName.hashCode() : 0);
        result = 31 * result + (personType != null ? personType.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (enableStatus != null ? enableStatus.hashCode() : 0);
        result = 31 * result + (enableDate != null ? enableDate.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        return result;
    }

    @Override
    public AuParty buildAuParty() {
        auParty = new AuParty();
        auParty.setAuPartyType(AuPartyType.EMPLOYEE);
        auParty.setName(getPersonName());
        auParty.setRemark(getRemark());
        auParty.setEnableStatus(getEnableStatus());
        auParty.setInherit(true);
        auParty.setReal(true);
        setAuParty(auParty);
        return auParty;
    }
}
