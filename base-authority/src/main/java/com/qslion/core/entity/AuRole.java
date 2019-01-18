package com.qslion.core.entity;

import com.google.common.collect.Sets;
import com.qslion.core.enums.AuPartyType;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.security.core.GrantedAuthority;

/**
 * 实体类 - 角色
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "au_role")
public class AuRole extends PartyEntity implements GrantedAuthority {

    private String name;
    private String value;
    private String description;

    private Set<AuUser> users = Sets.newHashSet();
    private Set<AuPermission> permissions = Sets.newHashSet();
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

    @ManyToMany(targetEntity = AuUser.class, mappedBy = "roles")
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

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Transient
    @Override
    public String getAuthority() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuRole auRole = (AuRole) o;

        if (id != auRole.id) {
            return false;
        }
        if (name != null ? !name.equals(auRole.name) : auRole.name != null) {
            return false;
        }
        if (value != null ? !value.equals(auRole.value) : auRole.value != null) {
            return false;
        }
        if (description != null ? !description.equals(auRole.description)
            : auRole.description != null) {
            return false;
        }
        if (enableStatus != null ? !enableStatus.equals(auRole.enableStatus)
            : auRole.enableStatus != null) {
            return false;
        }
        if (createDate != null ? !createDate.equals(auRole.createDate) : auRole.createDate != null) {
            return false;
        }
        if (modifyDate != null ? !modifyDate.equals(auRole.modifyDate) : auRole.modifyDate != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (enableStatus != null ? enableStatus.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        return result;
    }

    @Override
    public AuParty buildAuParty() {
        auParty = new AuParty();
        auParty.setAuPartyType(AuPartyType.ROLE);
        auParty.setName(getName());
        auParty.setRemark(getDescription());
        auParty.setEnableStatus(getEnableStatus());
        auParty.setInherit(true);
        auParty.setReal(true);
        return auParty;
    }
}
