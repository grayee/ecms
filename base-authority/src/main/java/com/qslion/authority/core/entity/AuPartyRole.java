/**
 *
 */
package com.qslion.authority.core.entity;


import com.qslion.framework.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;


/**
 * 项目名称：authority
 * 类名称：PartyRole
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2011-8-17 下午02:13:30
 * 修改人：Administrator
 * 修改时间：2011-8-17 下午02:13:30
 * 修改备注：
 */
@Entity(name = "AU_PARTYROLE")
public class AuPartyRole extends BaseEntity<Long> {

    /**
     *
     */
    private static final long serialVersionUID = -2058228975460800897L;
    private String roleId;
    private String partyId;
    private String systemId;

    @Column(name = "ROLEID", unique = true, nullable = false, length = 19)
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Column(name = "PARTYID", unique = true, nullable = false, length = 19)
    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    @Column(name = "SYSTEMID", unique = true, nullable = false, length = 19)
    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }
}
