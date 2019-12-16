/**
 *
 */
package com.qslion.authority.core.service.impl;

import com.qslion.authority.core.dao.AuUserRepository;
import com.qslion.authority.core.entity.AuUser;
import com.qslion.authority.core.service.AuUserService;
import com.qslion.framework.bean.SystemConfig;
import com.qslion.framework.service.impl.GenericServiceImpl;
import com.qslion.framework.util.JSONUtils;

import java.security.Principal;
import java.util.Date;
import java.util.stream.Collectors;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * server
 *
 * @author Gray.Z
 * @date 2018/4/30 19:19.
 */
@Service
@Transactional(value = "transactionManager", rollbackFor = Exception.class)
public class AuUserServiceImpl extends GenericServiceImpl<AuUser, Long> implements UserDetailsService,
        AuUserService {

    @Autowired
    public AuUserRepository userRepository;

    @Override
    public AuUser findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public AuUser loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        logger.info("系统登录通过用户名载入用户信息开始,用户名：{}", username);
        AuUser loginUser = userRepository.findUserByUsername(username);
        if (loginUser == null) {
            loginUser = userRepository.findUserByLoginId(username);
            if (loginUser == null && username.contains("@")) {
                loginUser = userRepository.findUserByEmail(username);
            }
            if (loginUser == null) {
                throw new UsernameNotFoundException("管理员[" + username + "]不存在!");
            }
        }
        // 解除管理员账户锁定
        SystemConfig systemConfig = new SystemConfig();
        if (loginUser.isAccountNonLocked()) {
            if (systemConfig.getIsLoginFailureLock()) {
                int loginFailureLockTime = systemConfig.getLoginFailureLockTime();
                if (loginFailureLockTime != 0) {
                    Date lockedDate = loginUser.getLockedDate();
                    Date nonLockedTime = DateUtils.addMinutes(lockedDate, loginFailureLockTime);
                    Date now = new Date();
                    if (now.after(nonLockedTime)) {
                        loginUser.setLoginFailureCount(0);
                        loginUser.setAccountNonLocked(false);
                        loginUser.setLockedDate(null);
                        this.update(loginUser);
                    }
                }
            } else {
                loginUser.setLoginFailureCount(0);
                loginUser.setAccountNonLocked(true);
                this.update(loginUser);
            }
        }
        logger.info("系统登录通过用户名载入用户信息结束,权限信息:{}", JSONUtils.writeValueAsString(loginUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList())));
        return loginUser;
    }

    @Override
    public String getCurrentUsername() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        if (principal instanceof Principal) {
            return ((Principal) principal).getName();
        }

        return String.valueOf(principal);
    }
}
