package com.qslion.web.sob;

/**
 * 账套上下文
 *
 * @author Gray.Z
 * @date 2018/5/5 15:30.
 */
public class SOBContextHolder {
    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public static void setSob(String tenant) {
        CONTEXT.set(tenant);
    }

    public static String getSob() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
