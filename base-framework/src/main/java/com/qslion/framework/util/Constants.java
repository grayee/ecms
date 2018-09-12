/*
 * Copyright (c) 2018. Tianyi AIDOC Company.Inc. All Rights Reserved.
 */

package com.qslion.framework.util;

/**
 * server
 *
 * @author Gray.Z
 * @date 2018/4/21 17:01.
 */
public class Constants {

    public static final int DEFAULT_DECIMAL_SCALE = 3;
    public static final String DECIMAL_ZERO_STRING = "0.000";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String KEY="WOLQPD7KKER1RE4UIBVJZ2AW9ERE5PIPER3CCWE";

    /** 超级管理员ID */
    public static final int SUPER_ADMIN = 1;
    /** 数据权限过滤 */
    public static final String SQL_FILTER = "sql_filter";


    /**
     * 菜单类型
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
