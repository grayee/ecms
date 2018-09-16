/*
 * Copyright (c) 2018. Tianyi AIDOC Company.Inc. All Rights Reserved.
 */

package com.qslion.security;

import com.google.common.collect.Maps;
import com.qslion.core.entity.AuPermission;
import com.qslion.core.entity.AuResource;
import com.qslion.core.entity.AuRole;
import com.qslion.core.service.AuResourceService;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AuSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static Logger logger = LogManager.getLogger(AuSecurityMetadataSource.class);
    @Autowired
    private AuResourceService auResourceService;

    private static Map<String, Collection<ConfigAttribute>> resourceMap = Maps.newLinkedHashMap();


    /**
     * 程序启动的时执行,加载资源和角色的对应关系-resourceMap
     *
     * @return 所有的角色
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<>();
        List<AuResource> resources = auResourceService.findAll();
        for (AuResource resource : resources) {
            Set<ConfigAttribute> itemAttributes = new HashSet<>();
            Set<AuPermission> permissions = resource.getPermissions();
            for (AuPermission permission : permissions) {
                Set<AuRole> roles = permission.getRoles();
                for (AuRole role : roles) {
                    ConfigAttribute ca = new SecurityConfig(role.getAuthority());
                    // 每一个请求资源对应的Role
                    itemAttributes.add(ca);
                    // 所有的Role对象
                    allAttributes.add(ca);
                }
            }
            String resourceValue = resource.getValue();
            resourceMap.put(resourceValue, itemAttributes);
        }
        return allAttributes;
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

        logger.info("user request url is :" + request.getRequestURI());

        Iterator<String> iterator = resourceMap.keySet().iterator();

        while (iterator.hasNext()) {
            String resourceValue = iterator.next();
            RequestMatcher matcher = new AntPathRequestMatcher(resourceValue);
            if (matcher.matches(request)) {
                return resourceMap.get(resourceValue);
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}
