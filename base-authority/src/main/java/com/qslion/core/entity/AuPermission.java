package com.qslion.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;
import com.qslion.framework.entity.BaseEntity;
import com.qslion.framework.enums.EnableStatus;

import javax.persistence.*;
import java.util.Set;

/**
 * 实体类 - 权限
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "au_permission")
public class AuPermission extends BaseEntity<Long> {

    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限标识
     */
    private String value;
    /**
     * 权限描述
     */
    private String description;
    /**
     * 权限类型
     */
    private PermitType type = PermitType.FUNCTION;

    public enum PermitType {
        /**
         * 功能权限、数据权限
         */
        FUNCTION, DATA
    }

    private EnableStatus enableStatus;

    @JsonIgnore
    private Set<AuRole> roles = Sets.newHashSet();
    /**
     * 一个资源拥有多个权限
     */
    private AuResource resource;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "RESOURCE_ID")
    public AuResource getResource() {
        return resource;
    }

    public void setResource(AuResource resource) {
        this.resource = resource;
    }

    @JsonIgnore
    @ManyToMany(targetEntity = AuRole.class, mappedBy = "permissions", fetch = FetchType.EAGER)
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
    @Enumerated
    public PermitType getType() {
        return type;
    }

    public void setType(PermitType type) {
        this.type = type;
    }

    @Basic
    @Column(name = "enable_status", nullable = true, length = 1)
    @Enumerated
    public EnableStatus getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(EnableStatus enableStatus) {
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
