package com.qslion.custom.entity;

import com.qslion.core.entity.PartyEntity;
import com.qslion.core.enums.AuPartyType;
import com.qslion.framework.bean.DisplayField;
import com.qslion.framework.enums.SexEnum;

import javax.persistence.*;

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
    private String employeeNo;
    @DisplayField(id = 2, title = "员工姓名")
    private String employeeName;
    @DisplayField(id = 3, title = "英文名称")
    private String englishName;
    private String employeeType;
    @DisplayField(id = 4, title = "性别")
    private SexEnum sex;
    @DisplayField(id = 5, title = "手机号码")
    private String mobilePhone;
    @DisplayField(id = 6, title = "电话")
    private String telPhone;
    @DisplayField(id = 7, title = "邮箱")
    private String email;
    @DisplayField(id = 8, title = "联系地址")
    private String address;
    private String postalCode;

    @Basic
    @Column(name = "employee_no", nullable = true, length = 64)
    public String getEmployeeNo() {
        return employeeNo;
    }
    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    @Basic
    @Column(name = "employee_name", nullable = true, length = 64)
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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
    @Column(name = "employee_type", nullable = true, length = 64)
    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    @Basic
    @Column(name = "sex", nullable = true, length = 1)
    @Enumerated
    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "mobile_phone", nullable = true, length = 32)
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Basic
    @Column(name = "tel_phone", nullable = true, length = 32)
    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
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
        if (employeeNo != null ? !employeeNo.equals(that.employeeNo) : that.employeeNo != null) {
            return false;
        }
        if (employeeName != null ? !employeeName.equals(that.employeeName) : that.employeeName != null) {
            return false;
        }
        if (englishName != null ? !englishName.equals(that.englishName) : that.englishName != null) {
            return false;
        }
        if (employeeType != null ? !employeeType.equals(that.employeeType) : that.employeeType != null) {
            return false;
        }
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) {
            return false;
        }
        if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) {
            return false;
        }
        if (telPhone != null ? !telPhone.equals(that.telPhone) : that.telPhone != null) {
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
        result = 31 * result + (employeeNo != null ? employeeNo.hashCode() : 0);
        result = 31 * result + (employeeName != null ? employeeName.hashCode() : 0);
        result = 31 * result + (englishName != null ? englishName.hashCode() : 0);
        result = 31 * result + (employeeType != null ? employeeType.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (telPhone != null ? telPhone.hashCode() : 0);
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
    public AuPartyType getPartyType() {
        return AuPartyType.EMPLOYEE;
    }

    @Override
    public String getPartyName() {
        return employeeName;
    }
}
