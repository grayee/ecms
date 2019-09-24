package com.qslion.moudles.tenant.entity;

import com.qslion.framework.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 租户信息
 *
 * @author Gray.Z
 * @date 2018/5/5 15:30.
 */
@Entity
@Table(name = "tenant_info")
public class TenantInfo extends BaseEntity<Long> {
    /**
     * Tenant identifier
     */
    private String tenant;
    private String username;
    private String password;
    private String tenantType;
    /**
     * Tenant jdbc url
     */
    private String url;

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getTenantType() {
        return tenantType;
    }

    public void setTenantType(String tenantType) {
        this.tenantType = tenantType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
