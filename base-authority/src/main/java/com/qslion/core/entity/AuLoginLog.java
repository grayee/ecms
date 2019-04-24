package com.qslion.core.entity;

import com.qslion.framework.entity.BaseEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;


/**
 * 登陆日志
 *
 * @author Gray.Z
 * @date 2018/9/18.
 */
@Entity
@Table(name = "AU_LOGIN_LOG")
public class AuLoginLog extends BaseEntity<Long> {


    public enum LoginType {
        /**
         * 退出方式，LOGIN 登陆，LOGOUT 退出
         */
        LOGIN, LOGOUT
    }

    private enum LoginState {
        /**
         * 登陆状态，ONLINE在线，OFFLINE 下线
         */
        ONLINE, OFFLINE
    }

    private String loginId;
    private String username;
    private String loginIp;
    private String client;
    private String os;
    private String host;
    private LoginType loginType;
    private Date loginTime;
    private LoginState loginState;
    private String loginMac;

    @Column(name = "LOGIN_ID", length = 50)
    public String getLoginId() {
        return this.loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    @Column(name = "USER_NAME", length = 50)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "LOGIN_IP", length = 50)
    public String getLoginIp() {
        return this.loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    @Column(name = "CLIENT", length = 255)
    public String getClient() {
        return this.client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    @Column(name = "OS", length = 50)
    public String getOs() {
        return this.os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    @Column(name = "HOST", length = 50)
    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Enumerated
    @Column(name = "LOGOUT_TYPE", length = 1)
    public LoginType getLoginType() {
        return this.loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    @Column(name = "LOGIN_TIME", length = 19)
    public Date getLoginTime() {
        return this.loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Enumerated
    @Column(name = "LOGIN_STATE", length = 1)
    public LoginState getLoginState() {
        return this.loginState;
    }

    public void setLoginState(LoginState loginState) {
        this.loginState = loginState;
    }

    @Column(name = "LOGIN_MAC", length = 50)
    public String getLoginMac() {
        return this.loginMac;
    }

    public void setLoginMac(String loginMac) {
        this.loginMac = loginMac;
    }

}