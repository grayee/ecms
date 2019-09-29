package com.qslion.authority.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qslion.framework.entity.BaseEntity;
import com.qslion.framework.enums.EnableStatus;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 实体类 - 角色类型
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "au_role_type")
public class AuRoleType extends BaseEntity<Long>  {

    private String name;
    private String description;

    private EnableStatus enableStatus;

    @JsonIgnore
    private List<AuRole> roleList;

    @OneToMany
    @JoinColumn(name = "TYPE_ID")
    public List<AuRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<AuRole> roleList) {
        this.roleList = roleList;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated
    @Basic
    @Column(name = "enable_status", nullable = true, length = 1)
    public EnableStatus getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(EnableStatus enableStatus) {
        this.enableStatus = enableStatus;
    }
}
