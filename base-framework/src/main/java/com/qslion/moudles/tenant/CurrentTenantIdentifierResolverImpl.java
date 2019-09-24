package com.qslion.moudles.tenant;

import org.apache.commons.lang.StringUtils;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

/**
 * Hibernate提供的用于识别tenantId的类，当每次执行sql语句被拦截就会调用这个类中的方法来获取tenantId
 * 负责解析租户标识
 *
 * @author Gray.Z
 * @date 2018/5/5 15:30.
 */
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

    /**
     * 默认的租户ID
     */
    private static final String DEFAULT_TENANT = "default";

    // 获取tenantId的逻辑在这个方法里面写
    @Override
    public String resolveCurrentTenantIdentifier() {
        //通过租户上下文获取租户ID，此ID是用户登录时在header中进行设置的
        String tenant = TenantContextHolder.getTenant();
        //如果上下文中没有找到该租户ID，则使用默认的租户ID，或者直接报异常信息
        return StringUtils.isNotBlank(tenant) ? tenant : DEFAULT_TENANT;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
