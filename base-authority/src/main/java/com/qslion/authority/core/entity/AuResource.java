package com.qslion.authority.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;
import com.qslion.framework.bean.TreeNode;
import com.qslion.framework.entity.BaseTree;
import com.qslion.framework.enums.EnableStatus;

import java.util.Set;
import javax.persistence.*;

/**
 * 实体类 - 资源
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "au_resource")
public class AuResource extends BaseTree<Long> {
    private String value;
    private EnableStatus enableStatus;
    private Set<AuPermission> permissions = Sets.newHashSet();
    private AuMenu menu;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "RESOURCE_ID")
    public Set<AuPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<AuPermission> permissions) {
        this.permissions = permissions;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "resource")
    public AuMenu getMenu() {
        return menu;
    }

    public void setMenu(AuMenu menu) {
        this.menu = menu;
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
    @Enumerated
    @Column(name = "enable_status", nullable = true, length = 1)
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

        AuResource that = (AuResource) o;

        if (id != that.id) {
            return false;
        }

        if (value != null ? !value.equals(that.value) : that.value != null) {
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
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (enableStatus != null ? enableStatus.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        return result;
    }

    @JsonIgnore
    @Transient
    public TreeNode getTreeNode() {
        return new TreeNode(getId(), this.getName());
    }
}
