package com.qslion.authority.core.entity;

import com.qslion.framework.entity.BaseEntity;

import javax.persistence.*;


/**
 * AuAuthorize entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "au_authorize")
public class AuAuthorize extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;
    private Long partyId;
    private String partyCode;
    private String partyType;
    private String resourceCode;
    private String resourceType;
    private String authorizeStatus;
    private Integer accessType;
    private String isAppend;
    private AuResource auResource;


    @Column(name = "party_id", nullable = false)
    public Long getPartyId() {
        return this.partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }


    @Column(name = "party_code", length = 300)
    public String getPartyCode() {
        return this.partyCode;
    }

    public void setPartyCode(String partyCode) {
        this.partyCode = partyCode;
    }

    @Column(name = "party_type", length = 1)
    public String getPartyType() {
        return this.partyType;
    }

    public void setPartyType(String partyType) {
        this.partyType = partyType;
    }

    @Column(name = "resource_code", length = 300)
    public String getResourceCode() {
        return this.resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    @Column(name = "resource_type", length = 1)
    public String getResourceType() {
        return this.resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Column(name = "authorize_status", nullable = false, length = 1)
    public String getAuthorizeStatus() {
        return this.authorizeStatus;
    }

    public void setAuthorizeStatus(String authorizeStatus) {
        this.authorizeStatus = authorizeStatus;
    }

    @Column(name = "access_type", precision = 8, scale = 0)
    public Integer getAccessType() {
        return this.accessType;
    }

    public void setAccessType(Integer accessType) {
        this.accessType = accessType;
    }

    @Column(name = "is_append", nullable = false, length = 1)
    public String getIsAppend() {
        return this.isAppend;
    }

    public void setIsAppend(String isAppend) {
        this.isAppend = isAppend;
    }

    @OneToOne(fetch = FetchType.LAZY)
    public AuResource getAuResource() {
        return auResource;
    }

    public void setAuResource(AuResource auResource) {
        this.auResource = auResource;
    }

}