package com.qslion.authority.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import com.qslion.authority.core.enums.AuOrgType;
import com.qslion.framework.bean.DisplayField;

import javax.persistence.*;
import java.util.Set;

/**
 * 实体类 - 角色
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "au_role")
public class AuRole extends BaseOrg {

    private static final long serialVersionUID = 5739472491120418264L;
    @DisplayField(order = 1, title = "角色名称")
    private String name;
    @DisplayField(order = 2, title = "角色编码")
    private String value;

    @JsonIgnore
    private Set<AuUser> users = Sets.newHashSet();
    @JsonIgnore
    private Set<AuPermission> permissions = Sets.newHashSet();
    @JsonIgnore
    private Set<AuUserGroup> userGroups = Sets.newHashSet();

    @ManyToMany(targetEntity = AuUserGroup.class, mappedBy = "roles")
    public Set<AuUserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(Set<AuUserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    @ManyToMany(targetEntity = AuPermission.class, fetch = FetchType.LAZY)
    @JoinTable(name = "au_role_permission", joinColumns = {
            @JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    public Set<AuPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<AuPermission> permissions) {
        this.permissions = permissions;
    }

    @ManyToMany(targetEntity = AuUser.class, mappedBy = "roles", fetch = FetchType.EAGER)
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
    @Column(name = "value", nullable = true, length = 30)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @JsonIgnore
    @Transient
    @Override
    public AuOrgType getOrgType() {
        return AuOrgType.ROLE;
    }
    @JsonIgnore
    @Transient
    @Override
    public String getOrgName() {
        return name;
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
        AuRole auRole = (AuRole) o;
        return Objects.equal(name, auRole.name) &&
                Objects.equal(value, auRole.value) &&
                Objects.equal(remark, auRole.remark) &&
                Objects.equal(users, auRole.users) &&
                Objects.equal(permissions, auRole.permissions) &&
                Objects.equal(userGroups, auRole.userGroups);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), name, value, remark, users, permissions, userGroups);
    }
}
