package com.qslion.framework.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 实体类-基类
 *
 * @author Gray.Z
 * @date 2018/4/3 20:25.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    protected T id;
    /**
     * 创建日期
     */
    @CreatedDate
    protected Date createDate;
    /**
     * 修改日期
     */
    @LastModifiedDate
    protected Date modifyDate;
    /**
     * 版本信息
     */
    private Long version;

    @Id
    @Column(length = 32, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GenericGenerator(name = "uuid", strategy = "uuid")
    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", length = 21, updatable = false, columnDefinition = "datetime(3)")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date", length = 21, columnDefinition = "datetime(3)")
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Version
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        return id == null ? System.identityHashCode(this) : id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass().getPackage() != obj.getClass().getPackage()) {
            return false;
        }
        final BaseEntity other = (BaseEntity) obj;
        if (id == null) {
            if (other.getId() != null) {
                return false;
            }
        } else if (!id.equals(other.getId())) {
            return false;
        }
        return true;
    }

}