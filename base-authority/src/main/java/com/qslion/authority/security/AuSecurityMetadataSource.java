package com.qslion.authority.security;

import com.google.common.collect.Maps;
import com.qslion.authority.core.entity.AuPermission;
import com.qslion.authority.core.entity.AuResource;

import java.util.*;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

/**
 * 资源拦截器拦截-资源元数据定义，将所有的资源和权限对应关系建立起来，
 * 即定义某一资源可以被哪些角色访问
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Component
public class AuSecurityMetadataSource implements FilterInvocationSecurityMetadataSource, Observer {

    private static Logger logger = LogManager.getLogger();

    private static Map<String, Collection<ConfigAttribute>> resourceMap = Maps.newLinkedHashMap();


    /**
     * 程序启动的时执行(AbstractSecurityInterceptor.afterPropertiesSet),加载资源和角色的对应关系-resourceMap
     *
     * @return 所有的角色
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
   /*      Set<ConfigAttribute> allAttributes = new HashSet<>();
       List<AuResource> resources = auResourceService.findAll();
        for (AuResource resource : resources) {
            Set<ConfigAttribute> itemAttributes = new HashSet<>();
            for (AuPermission permission : resource.getPermissions()) {
                ConfigAttribute ca = new SecurityConfig(permission.getAuthority());
                // 每一个请求资源对应的Role
                itemAttributes.add(ca);
                // 所有的Role对象
                allAttributes.add(ca);
            }
            String resourceValue = resource.getValue();
            if (StringUtils.isNotEmpty(resourceValue)) {
                resourceMap.put(resourceValue, itemAttributes);
            }
        }*/
        return new HashSet<>();
    }

    /**
     * According to a URL, Find out permission configuration of this URL.
     * 根据访问的资源URL来查找对应所需角色权限，如果在权限表中，则返回给 decide 方法，
     * 用来判定用户是否有此权限。如果不在权限表中则放行
     *
     * @param object 当前用户访问的受保护的资源
     * @return 资源元数据集合
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // object contains user request  URL.
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();

        logger.info("当前请求URL为 :{}", String.format("%s %s", request.getMethod(), request.getRequestURI()));

        for (String resourceValue : resourceMap.keySet()) {
            RequestMatcher matcher = new AntPathRequestMatcher(resourceValue);
            if (matcher.matches(request)) {
                return resourceMap.get(resourceValue);
            }
        }
        logger.info("当前UTL :{} 不在权限表中", String.format("%s %s", request.getMethod(), request.getRequestURI()));
        return null;
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

    @Override
    public void update(Observable o, Object arg) {
        List<AuResource> resources = (List<AuResource>) arg;
        for (AuResource resource : resources) {
            Set<ConfigAttribute> itemAttributes = new HashSet<>();
            for (AuPermission permission : resource.getPermissions()) {
                ConfigAttribute ca = new SecurityConfig(permission.getAuthority());
                // 每一个请求资源对应的Role
                itemAttributes.add(ca);
            }
            String resourceValue = resource.getValue();
            if (StringUtils.isNotEmpty(resourceValue)) {
                resourceMap.put(resourceValue, itemAttributes);
            }
        }
    }
}
