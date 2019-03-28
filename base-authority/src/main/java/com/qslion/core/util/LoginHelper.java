/**
 *
 */
package com.qslion.core.util;

import com.qslion.core.entity.AuUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 登陆帮助类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
public class LoginHelper {

    public static AuUser getLoginUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        return (AuUser) principal;
    }
}
