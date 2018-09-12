/**
 *
 */
package com.qslion.authority.core.service.impl;

import com.qslion.authority.core.dao.AuUserRepository;
import com.qslion.authority.core.entity.AuUser;
import com.qslion.authority.core.service.AuUserService;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.bean.SystemConfig;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * 修改备注：
 */
@Service("auUserService")
public class AuUserServiceImpl extends GenericServiceImpl<AuUser, String> implements UserDetailsService,
        AuUserService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    public AuUserRepository userRepository;



    @Override
    public AuUser findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public int getRecordCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getRecordCount(String paramString) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public boolean update(AuUser entity) {
        return false;
    }

    @Override
    public boolean delete(String[] ids) {
        return false;
    }

    @Override
    public Pager<AuUser> findByPager(Pager<AuUser> pager) {
        return null;
    }


    public AuUser loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        logger.info("---------------------系统登录通过用户名载入用户信息开始！--------------------------");
        AuUser loginUser = userRepository.findUserByUsername(username);
        if (loginUser == null) {
            throw new UsernameNotFoundException("管理员[" + username + "]不存在!");
        }
        // 解除管理员账户锁定
        SystemConfig systemConfig = new SystemConfig();
        if (loginUser.getIsAccountLocked() == true) {
            if (systemConfig.getIsLoginFailureLock() == true) {
                int loginFailureLockTime = systemConfig.getLoginFailureLockTime();
                if (loginFailureLockTime != 0) {
                    Date lockedDate = loginUser.getLockedDate();
                    Date nonLockedTime = DateUtils.addMinutes(lockedDate, loginFailureLockTime);
                    Date now = new Date();
                    if (now.after(nonLockedTime)) {
                        loginUser.setLoginFailureCount(0);
                        loginUser.setIsAccountLocked(false);
                        loginUser.setLockedDate(null);
                        //adminRepository.update(admin);
                    }
                }
            } else {
                loginUser.setLoginFailureCount(0);
                loginUser.setIsAccountLocked(false);
                loginUser.setLockedDate(null);
                //adminRepository.update(admin);
            }
        }
        loginUser.setAuthorities(getGrantedAuthorities(loginUser));
        logger.info("---------------------系统登录通过用户名载入用户信息结束！用户名：" + loginUser.getUsername() + ",权限信息:" + loginUser.getAuthorities().toString() + "--------------------------");
        return loginUser;
    }

    // 获得管理角色数组
    public Collection<GrantedAuthority> getGrantedAuthorities(AuUser admin) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        /*for (AuRole role : admin.getAuRoleSet()) {
            grantedAuthorities.add(new GrantedAuthorityImpl(role.getValue()));
		}*/

        //AuthorityUtils.createAuthorityList(admin.getAuRoleSet());
        //SecurityContextHolder.getContext().getAuthentication().getAuthorities();  ---获取当前用户权限
        return grantedAuthorities;
    }

    public String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
