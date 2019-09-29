package com.qslion.authority.security;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * 审计-获取用户ID
 *
 * @author Gray.Z
 * @date 2018/9/23 14:15.
 */
@Component
public class AuUserIdAuditAware implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.of(0L);
        }
        Object principal = authentication.getPrincipal();

        System.out.println("principal:" + String.valueOf(principal));
        return Optional.of(999L);
    }
}
