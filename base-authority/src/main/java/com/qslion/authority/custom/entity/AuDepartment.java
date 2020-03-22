package com.qslion.authority.custom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qslion.authority.core.entity.AbstractOrgEntity;
import com.qslion.authority.core.enums.AuOrgType;
import com.qslion.framework.bean.DisplayField;

import javax.persistence.*;
import java.util.Date;

/**
 * 实体类 - 部门
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "au_department")
public class AuDepartment extends AbstractOrgEntity {

    @DisplayField(order = 1, title = "部门编码")
    private String deptNo;
    @DisplayField(order = 2, title = "部门名称")
    private String deptName;
    @DisplayField(order = 3, title = "部门标识")
    private String deptFlag;
    @DisplayField(order = 4, title = "部门类型")
    private String deptType;
    @DisplayField(order = 5, title = "部门级别")
    private String deptLevel;
    @DisplayField(order = 6, title = "部门领导")
    private String deptLeader;
    @DisplayField(order = 7, title = "成立时间")
    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",timezone = "GMT+8")
    private Date foundDate;


    @Basic
    @Column(name = "dept_no", nullable = true, length = 64)
    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    @Basic
    @Column(name = "dept_name", nullable = true, length = 255)
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Basic
    @Column(name = "dept_flag", nullable = true, length = 64)
    public String getDeptFlag() {
        return deptFlag;
    }

    public void setDeptFlag(String deptFlag) {
        this.deptFlag = deptFlag;
    }

    @Basic
    @Column(name = "dept_type", nullable = true, length = 64)
    public String getDeptType() {
        return deptType;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }

    @Basic
    @Column(name = "dept_level", nullable = true, length = 3)
    public String getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(String deptLevel) {
        this.deptLevel = deptLevel;
    }

    @Basic
    @Column(name = "dept_leader", nullable = true, length = 64)
    public String getDeptLeader() {
        return deptLeader;
    }

    public void setDeptLeader(String deptLeader) {
        this.deptLeader = deptLeader;
    }

    @Basic
    @Column(name = "found_date")
    public Date getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(Date foundDate) {
        this.foundDate = foundDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuDepartment that = (AuDepartment) o;

        if (id != that.id) {
            return false;
        }
        if (deptNo != null ? !deptNo.equals(that.deptNo) : that.deptNo != null) {
            return false;
        }
        if (deptName != null ? !deptName.equals(that.deptName) : that.deptName != null) {
            return false;
        }
        if (deptFlag != null ? !deptFlag.equals(that.deptFlag) : that.deptFlag != null) {
            return false;
        }
        if (deptType != null ? !deptType.equals(that.deptType) : that.deptType != null) {
            return false;
        }
        if (deptLevel != null ? !deptLevel.equals(that.deptLevel) : that.deptLevel != null) {
            return false;
        }
        if (deptLeader != null ? !deptLeader.equals(that.deptLeader) : that.deptLeader != null) {
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
        result = 31 * result + (deptNo != null ? deptNo.hashCode() : 0);
        result = 31 * result + (deptName != null ? deptName.hashCode() : 0);
        result = 31 * result + (deptFlag != null ? deptFlag.hashCode() : 0);
        result = 31 * result + (deptType != null ? deptType.hashCode() : 0);
        result = 31 * result + (deptLevel != null ? deptLevel.hashCode() : 0);
        result = 31 * result + (deptLeader != null ? deptLeader.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (enableStatus != null ? enableStatus.hashCode() : 0);
        result = 31 * result + (enableDate != null ? enableDate.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        return result;
    }
    @JsonIgnore
    @Transient
    @Override
    public AuOrgType getOrgType() {
        return AuOrgType.DEPARTMENT;
    }
    @JsonIgnore
    @Transient
    @Override
    public String getOrgName() {
        return deptName;
    }
}
