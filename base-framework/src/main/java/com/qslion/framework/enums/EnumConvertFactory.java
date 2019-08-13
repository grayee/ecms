package com.qslion.framework.enums;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 *  EnumConvertFactory
 *
 * @author Gray.Z
 * @date 2018/9/22.
 */
public class EnumConvertFactory implements ConverterFactory<String, IEnum> {

    @Override
    public <T extends IEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToIEum<>(targetType);
    }

    static <T extends IEnum> Object getIEnum(Class<T> targetType, String source) {
        for (T enumObj : targetType.getEnumConstants()) {
            if (source.equals(String.valueOf(enumObj.getId()))) {
                return enumObj;
            }
        }
        return null;
    }

    private static class StringToIEum<T extends IEnum> implements Converter<String, T> {
        private Class<T> targetType;
        StringToIEum(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(String source) {
            if (StringUtils.isEmpty(source)) {
                return null;
            }
            return (T) EnumConvertFactory.getIEnum(this.targetType, source);
        }
    }
}
