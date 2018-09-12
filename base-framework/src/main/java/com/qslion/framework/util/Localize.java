/*
 * Copyright (c) 2018. Tianyi AIDOC Company.Inc. All Rights Reserved.
 */

package com.qslion.framework.util;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;


/**
 * server
 *
 * @author Gray.Z
 * @date 2018/4/14 10:13.
 */
@Component
public class Localize implements MessageSourceAware {

    private static MessageSource messageSource;

    public static String getMessage(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

    public static String getMessage(String key, Object... args) {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        Localize.messageSource = messageSource;
    }

    public static Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }
}
