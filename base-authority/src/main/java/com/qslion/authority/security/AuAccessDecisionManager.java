package com.qslion.authority.security;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

/**
 * 授权管理器,主要实现权限鉴定和访问决策功能，实现了AccessDecisionManager接口，
 * 决定某个用户具有的角色，是否有足够的权限去访问某个资源
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Component
public class AuAccessDecisionManager implements AccessDecisionManager {

    private static final Logger logger = LogManager.getLogger();

    private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    /**
     * 权限验证
     *
     * @param authentication   认证通过的用户认证权限信息
     * @param object           当前正在请求的受保护的对象-URL资源,包括MethodInvocation（使用AOP）、JoinPoint（使用Aspectj）和FilterInvocation（Web请求）三种类型
     * @param configAttributes 当前受保护资源所需的权限
     * @throws AccessDeniedException               访问拒绝异常
     * @throws InsufficientAuthenticationException 认证错误
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        logger.info("2.进入授权管理器-AccessDecisionManager,开始检查访问者的访问控制权限..." + object.toString());
        if (configAttributes == null) {
            logger.info("[决策管理器]:请求 {} 无需权限", ((FilterInvocation) object).getRequestUrl());
            return;
        }
        logger.info("[决策管理器]:请求 {} 需要的权限 - {}", ((FilterInvocation) object).getRequestUrl(), configAttributes);
        logger.info("[决策管理器]:用户 {} 拥有的权限 - {}", authentication.getName(), authentication.getAuthorities());
        //object is a URL.
        for (ConfigAttribute configAttribute : configAttributes) {
            String needPerm = configAttribute.getAttribute();
            if (StringUtils.isNotEmpty(needPerm)) {
                for (GrantedAuthority ga : authentication.getAuthorities()) {
                    //ga is user's role.
                    if ("ALL".equals(ga.getAuthority()) || needPerm.equals(ga.getAuthority())) {
                        return;
                    }
                }
            }
        }
        logger.info("[决策管理器]:用户 {} 没有访问资源 {} 的权限!", authentication.getName(), ((FilterInvocation) object).getRequestUrl());
        throw new AccessDeniedException(messages.getMessage("AbstractAccessDecisionManager.accessDenied",
                "权限不足"));
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }

    @Override
    public boolean supports(ConfigAttribute arg0) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean supports(Class<?> arg0) {
        // TODO Auto-generated method stub
        return true;
    }

}
