package com.qslion.authority.core.entity;

import com.google.common.collect.Sets;
import com.qslion.framework.entity.BaseEntity;
import java.sql.Date;
import java.util.Collection;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 实体类 - 用户
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Entity
@Table(name = "au_user")
public class AuUser extends BaseEntity<Long> implements UserDetails {

    private String username;
    private String password;
    private String email;
    private String mobile;
    private String nickname;
    private Integer age;
    private Date birthday;
    private String loginId;
    private String loginIp;
    private String enableStatus;

    private Set<AuRole> roles = Sets.newHashSet();

    private Set<AuUserGroup> userGroups = Sets.newHashSet();

    /**
     * @see org.springframework.security.core.userdetails.User
     */
    private Set<AuRole> authorities = Sets.newHashSet();
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;


    @ManyToMany(targetEntity = AuUserGroup.class, mappedBy = "users")
    public Set<AuUserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(Set<AuUserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    @ManyToMany(targetEntity = AuRole.class, fetch = FetchType.EAGER )
    @JoinTable(name = "au_user_role", joinColumns = {
        @JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    public Set<AuRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AuRole> roles) {
        this.roles = roles;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 30)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 30)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "mobile", nullable = true, length = 11)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "nickname", nullable = true, length = 30)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "age", nullable = true)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "birthday", nullable = true)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "login_id", nullable = true, length = 30)
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    @Basic
    @Column(name = "login_ip", nullable = true, length = 255)
    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    @Basic
    @Column(name = "enable_status", nullable = true, length = 1)
    public String getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(String enableStatus) {
        this.enableStatus = enableStatus;
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Transient
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        authorities.addAll(roles);
        //AuthorityUtils.createAuthorityList()
        return authorities;
    }

    public void setAuthorities(Set<AuRole> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuUser auUser = (AuUser) o;

        if (id != auUser.id) {
            return false;
        }
        if (username != null ? !username.equals(auUser.username) : auUser.username != null) {
            return false;
        }
        if (password != null ? !password.equals(auUser.password) : auUser.password != null) {
            return false;
        }
        if (email != null ? !email.equals(auUser.email) : auUser.email != null) {
            return false;
        }
        if (mobile != null ? !mobile.equals(auUser.mobile) : auUser.mobile != null) {
            return false;
        }
        if (nickname != null ? !nickname.equals(auUser.nickname) : auUser.nickname != null) {
            return false;
        }
        if (age != null ? !age.equals(auUser.age) : auUser.age != null) {
            return false;
        }
        if (birthday != null ? !birthday.equals(auUser.birthday) : auUser.birthday != null) {
            return false;
        }
        if (loginId != null ? !loginId.equals(auUser.loginId) : auUser.loginId != null) {
            return false;
        }
        if (loginIp != null ? !loginIp.equals(auUser.loginIp) : auUser.loginIp != null) {
            return false;
        }
        if (enableStatus != null ? !enableStatus.equals(auUser.enableStatus)
            : auUser.enableStatus != null) {
            return false;
        }
        if (createDate != null ? !createDate.equals(auUser.createDate) : auUser.createDate != null) {
            return false;
        }
        if (modifyDate != null ? !modifyDate.equals(auUser.modifyDate) : auUser.modifyDate != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (loginId != null ? loginId.hashCode() : 0);
        result = 31 * result + (loginIp != null ? loginIp.hashCode() : 0);
        result = 31 * result + (enableStatus != null ? enableStatus.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        return result;
    }
}
