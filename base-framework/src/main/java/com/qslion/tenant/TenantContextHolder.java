package com.qslion.tenant;

/**
 * 租户上下文
 *
 * @author Gray.Z
 * @date 2018/5/5 15:30.
 */
public class TenantContextHolder {
    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public static void setTenant(String tenant) {
        CONTEXT.set(tenant);
    }

    public static String getTenant() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
