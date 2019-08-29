
package com.qslion.custom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qslion.core.entity.PartyEntity;
import com.qslion.core.enums.AuPartyType;
import com.qslion.framework.bean.DisplayField;

import javax.persistence.*;

/**
 * 实体类 - 职位
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "au_position")
public class AuPosition extends PartyEntity {

    @DisplayField(id = 1, title = "岗位编号")
    private String positionNo;
    @DisplayField(id = 2, title = "岗位名称")
    private String positionName;
    @DisplayField(id = 3, title = "岗位标识")
    private String positionFlag;
    @DisplayField(id = 4, title = "岗位类型")
    private String positionType;
    private String positionLevel;
    @DisplayField(id = 5, title = "领导标志")
    private String leaderFlag;
    private String leaderLevel;


    @Basic
    @Column(name = "position_no", nullable = true, length = 64)
    public String getPositionNo() {
        return positionNo;
    }

    public void setPositionNo(String positionNo) {
        this.positionNo = positionNo;
    }

    @Basic
    @Column(name = "position_name", nullable = true, length = 64)
    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    @Basic
    @Column(name = "position_flag", nullable = true, length = 64)
    public String getPositionFlag() {
        return positionFlag;
    }

    public void setPositionFlag(String positionFlag) {
        this.positionFlag = positionFlag;
    }

    @Basic
    @Column(name = "position_type", nullable = true, length = 64)
    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    @Basic
    @Column(name = "position_level", nullable = true, length = 3)
    public String getPositionLevel() {
        return positionLevel;
    }

    public void setPositionLevel(String positionLevel) {
        this.positionLevel = positionLevel;
    }

    @Basic
    @Column(name = "leader_flag", nullable = false, length = 1)
    public String getLeaderFlag() {
        return leaderFlag;
    }

    public void setLeaderFlag(String leaderFlag) {
        this.leaderFlag = leaderFlag;
    }

    @Basic
    @Column(name = "leader_level", nullable = true, length = 64)
    public String getLeaderLevel() {
        return leaderLevel;
    }

    public void setLeaderLevel(String leaderLevel) {
        this.leaderLevel = leaderLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuPosition that = (AuPosition) o;

        if (id != that.id) {
            return false;
        }
        if (positionNo != null ? !positionNo.equals(that.positionNo) : that.positionNo != null) {
            return false;
        }
        if (positionName != null ? !positionName.equals(that.positionName)
            : that.positionName != null) {
            return false;
        }
        if (positionFlag != null ? !positionFlag.equals(that.positionFlag)
            : that.positionFlag != null) {
            return false;
        }
        if (positionType != null ? !positionType.equals(that.positionType)
            : that.positionType != null) {
            return false;
        }
        if (positionLevel != null ? !positionLevel.equals(that.positionLevel)
            : that.positionLevel != null) {
            return false;
        }
        if (leaderFlag != null ? !leaderFlag.equals(that.leaderFlag) : that.leaderFlag != null) {
            return false;
        }
        if (leaderLevel != null ? !leaderLevel.equals(that.leaderLevel) : that.leaderLevel != null) {
            return false;
        }
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) {
            return false;
        }
        if (enableStatus != null ? !enableStatus.equals(that.enableStatus)
            : that.enableStatus != null) {
            return false;
        }
        if (enableDate != null ? !enableDate.equals(that.enableDate) : that.enableDate != null) {
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
        result = 31 * result + (positionNo != null ? positionNo.hashCode() : 0);
        result = 31 * result + (positionName != null ? positionName.hashCode() : 0);
        result = 31 * result + (positionFlag != null ? positionFlag.hashCode() : 0);
        result = 31 * result + (positionType != null ? positionType.hashCode() : 0);
        result = 31 * result + (positionLevel != null ? positionLevel.hashCode() : 0);
        result = 31 * result + (leaderFlag != null ? leaderFlag.hashCode() : 0);
        result = 31 * result + (leaderLevel != null ? leaderLevel.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (enableStatus != null ? enableStatus.hashCode() : 0);
        result = 31 * result + (enableDate != null ? enableDate.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        return result;
    }
    @JsonIgnore
    @Transient
    @Override
    public AuPartyType getPartyType() {
        return AuPartyType.POSITION;
    }
    @JsonIgnore
    @Transient
    @Override
    public String getPartyName() {
        return positionName;
    }

}
