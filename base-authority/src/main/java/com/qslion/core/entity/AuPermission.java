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
 * 实体类 - 权限
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "au_permission")
public class AuPermission extends BaseEntity<Long> {

    private String name;
    private String value;
    private String description;
    private Short type;
    private String enableStatus;

    private Set<AuRole> roles = Sets.newHashSet();
    private Set<AuMenu> menus = Sets.newHashSet();
    private Set<AuResource> resources = Sets.newHashSet();

    @ManyToMany(targetEntity = AuPermission.class, fetch = FetchType.LAZY)
    @JoinTable(name = "au_permission_menu", joinColumns = {
        @JoinColumn(name = "permission_id")}, inverseJoinColumns = {@JoinColumn(name = "menu_id")})
    public Set<AuMenu> getMenus() {
        return menus;
    }

    public void setMenus(Set<AuMenu> menus) {
        this.menus = menus;
    }

    @ManyToMany(targetEntity = AuPermission.class, fetch = FetchType.LAZY)
    @JoinTable(name = "au_permission_resource", joinColumns = {
        @JoinColumn(name = "permission_id")}, inverseJoinColumns = {
        @JoinColumn(name = "resource_id")})
    public Set<AuResource> getResources() {
        return resources;
    }

    public void setResources(Set<AuResource> resources) {
        this.resources = resources;
    }

    @ManyToMany(targetEntity = AuRole.class, mappedBy = "permissions",fetch= FetchType.EAGER)
    public Set<AuRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AuRole> roles) {
        this.roles = roles;
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

    @Basic
    @Column(name = "type", nullable = true)
    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
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

        AuPermission that = (AuPermission) o;

        if (id != that.id) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (value != null ? !value.equals(that.value) : that.value != null) {
            return false;
        }
        if (description != null ? !description.equals(that.description) : that.description != null) {
            return false;
        }
        if (type != null ? !type.equals(that.type) : that.type != null) {
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
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (enableStatus != null ? enableStatus.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        return result;
    }
}
