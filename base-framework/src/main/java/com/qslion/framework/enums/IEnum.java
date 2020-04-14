package com.qslion.framework.enums;

/**
 * IEnum
 *
 * @author Gray.Z
 * @date 2018/9/22.
 */
public interface IEnum<T> {
    T getId();

    /**
     *
     * @return
     */
    String getIdKey();

    /**
     * 显示名称
     *
     * @return DisplayName
     */
    String getDisplayName();
}
