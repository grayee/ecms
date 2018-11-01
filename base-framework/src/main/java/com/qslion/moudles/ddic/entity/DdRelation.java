package com.qslion.moudles.ddic.entity;

import com.qslion.framework.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 实体类 - 数据字典基础表
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "dd_relation", catalog = "qslion")
public class DdRelation extends BaseEntity<Long> {

    private String relationname;
    private String displayname;
    private String pktable;
    private String pkcolumn;
    private String fktable;
    private String fkcolumn;
    private String iscreated;
    private String status;
    private String remark;

    @Column(name = "RELATIONNAME", nullable = false, length = 128)
    public String getRelationname() {
        return this.relationname;
    }

    public void setRelationname(String relationname) {
        this.relationname = relationname;
    }

    @Column(name = "DISPLAYNAME", length = 128)
    public String getDisplayname() {
        return this.displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    @Column(name = "PKTABLE", nullable = false, length = 128)
    public String getPktable() {
        return this.pktable;
    }

    public void setPktable(String pktable) {
        this.pktable = pktable;
    }

    @Column(name = "PKCOLUMN", nullable = false, length = 128)
    public String getPkcolumn() {
        return this.pkcolumn;
    }

    public void setPkcolumn(String pkcolumn) {
        this.pkcolumn = pkcolumn;
    }

    @Column(name = "FKTABLE", nullable = false, length = 128)
    public String getFktable() {
        return this.fktable;
    }

    public void setFktable(String fktable) {
        this.fktable = fktable;
    }

    @Column(name = "FKCOLUMN", nullable = false, length = 128)
    public String getFkcolumn() {
        return this.fkcolumn;
    }

    public void setFkcolumn(String fkcolumn) {
        this.fkcolumn = fkcolumn;
    }

    @Column(name = "ISCREATED", length = 1)
    public String getIscreated() {
        return this.iscreated;
    }

    public void setIscreated(String iscreated) {
        this.iscreated = iscreated;
    }

    @Column(name = "STATUS", nullable = false, length = 1)
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Column(name = "REMARK", length = 1024)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}