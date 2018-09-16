package com.qslion.core.entity;

import com.google.common.collect.Sets;
import com.qslion.framework.entity.BaseEntity;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 实体类 - 用户组
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "au_usergroup")
public class AuUserGroup extends BaseEntity<Long> {

    private String name;
    private String code;
    private String description;
    private String enableStatus;

    private Set<AuUser> users = Sets.newHashSet();

    private Set<AuRole> roles = Sets.newHashSet();

    @ManyToMany(targetEntity = AuRole.class, fetch = FetchType.EAGER)
    @JoinTable(name = "au_usergroup_role", joinColumns = {
        @JoinColumn(name = "usergroup_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    public Set<AuRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AuRole> roles) {
        this.roles = roles;
    }

    @ManyToMany(targetEntity = AuUser.class, fetch = FetchType.EAGER)
    @JoinTable(name = "au_usergroup_user", joinColumns = {
        @JoinColumn(name = "usergroup_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    public Set<AuUser> getUsers() {
        return users;
    }

    public void setUsers(Set<AuUser> users) {
        this.users = users;
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
    @Column(name = "code", nullable = true, length = 30)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "enable_status", nullable = true, length = 1)
    public String getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(String enableStatus) {
        this.enableStatus = enableStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuUserGroup that = (AuUserGroup) o;

        if (id != that.id) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (code != null ? !code.equals(that.code) : that.code != null) {
            return false;
        }
        if (description != null ? !description.equals(that.description) : that.description != null) {
            return false;
        }
        if (enableStatus != null ? !enableStatus.equals(that.enableStatus)
            : that.enableStatus != null) {
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
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (enableStatus != null ? enableStatus.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        return result;
    }
}
