package com.qslion.authority.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Maps;
import com.qslion.authority.core.enums.MenuType;
import com.qslion.framework.bean.DisplayField;
import com.qslion.framework.bean.DisplayTitle;
import com.qslion.framework.bean.TreeNode;
import com.qslion.framework.entity.BaseTree;
import com.qslion.framework.enums.EnableStatus;

import javax.persistence.*;
import java.util.Map;

/**
 * 实体类 - 菜单
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "au_menu")
@DisplayTitle(name = "菜单")
public class AuMenu extends BaseTree<Long> {

    @DisplayField(order = 2,title = "菜单类型")
    private MenuType type;
    @DisplayField(order = 3,title = "菜单路径",align = "left")
    private String url;
    @DisplayField(order = 1.5,title = "图标")
    private String icon;
    @DisplayField(order = 4,title = "组件",align = "left")
    private String component;
    private Short status;
    private EnableStatus enableStatus;
    /**
     * 权限编码，多个以逗号分隔
     */
    @DisplayField(order = 5,title = "权限编码")
    private String authCode;

    private AuResource resource;

    @JsonIgnore
    @OneToOne(targetEntity = AuResource.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "resource_id", referencedColumnName = "id")
    public AuResource getResource() {
        return resource;
    }

    public AuMenu setResource(AuResource resource) {
        this.resource = resource;
        return this;
    }

    @Enumerated
    @Column(name = "type", nullable = false, length = 1)
    public MenuType getType() {
        return this.type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    @Basic
    @Column(name = "url", nullable = true, length = 255)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "icon", nullable = true, length = 30)
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Basic
    @Column(name = "component", nullable = true)
    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
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

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuMenu auMenu = (AuMenu) o;

        if (id != auMenu.id) {
            return false;
        }

        if (url != null ? !url.equals(auMenu.url) : auMenu.url != null) {
            return false;
        }

        if (icon != null ? !icon.equals(auMenu.icon) : auMenu.icon != null) {
            return false;
        }

        if (status != null ? !status.equals(auMenu.status) : auMenu.status != null) {
            return false;
        }
        if (enableStatus != null ? !enableStatus.equals(auMenu.enableStatus)
                : auMenu.enableStatus != null) {
            return false;
        }
        if (createDate != null ? !createDate.equals(auMenu.createDate) : auMenu.createDate != null) {
            return false;
        }
        if (modifyDate != null ? !modifyDate.equals(auMenu.modifyDate) : auMenu.modifyDate != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (enableStatus != null ? enableStatus.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        return result;
    }

    public AuResource buildResource() {
        AuResource resource = new AuResource();
        resource.setName(getName());
        resource.setValue(getUrl());
        resource.setMenu(this);
        resource.setRemark(this.getRemark());
        resource.setParentId(this.getParentId());
        resource.setEnableStatus(getEnableStatus());
        return resource;
    }

    @Override
    @JsonIgnore
    @Transient
    public TreeNode getTreeNode() {
        TreeNode treeNode = new TreeNode(String.valueOf(getId()), this.getName());
        treeNode.setIconCls(getIcon());
        treeNode.setPath(getUrl());
        treeNode.setState(getLeaf() ? TreeNode.NodeState.OPEN : TreeNode.NodeState.CLOSED);

        Map<String, Object> attributeMap = Maps.newHashMap();
        attributeMap.put("modifyDate", getModifyDate());
        attributeMap.put("menuType", getType().ordinal());
        attributeMap.put("orderNo", getOrderCode());
        attributeMap.put("component", getComponent());
        treeNode.setAttributes(attributeMap);
        return treeNode;
    }
}
