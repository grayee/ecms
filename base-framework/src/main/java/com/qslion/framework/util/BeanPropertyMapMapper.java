/*
 * Copyright (c) 2018. Tianyi AIDOC Company.Inc. All Rights Reserved.
 */

package com.qslion.framework.util;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 将数据库查询返回的Map<String,Object> 结果转换为Class
 *
 * @author Gray.Z
 * @date 2018/5/7 22:37.
 */
public class BeanPropertyMapMapper<T> {

    public T map(Class<T> mappedClass, Map<String, Object> map) {
        Assert.state(mappedClass != null, "Mapped class was not specified");
        if (map.isEmpty()) {
            return null;
        }
        Map<String, PropertyDescriptor> mappedFields = new HashMap<>(100);
        PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(mappedClass);
        for (PropertyDescriptor pd : pds) {
            if (pd.getWriteMethod() != null) {
                mappedFields.put(pd.getName().toLowerCase(), pd);
                String underscoredName = underscoreName(pd.getName());
                if (!pd.getName().toLowerCase().equals(underscoredName)) {
                    mappedFields.put(underscoredName, pd);
                }
            }
        }

        T mappedObject = BeanUtils.instantiateClass(mappedClass);
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(mappedObject);
        bw.setConversionService(DefaultConversionService.getSharedInstance());
        for (String key : map.keySet()) {
            String field = key.toLowerCase();
            PropertyDescriptor pd = mappedFields != null ? mappedFields.get(field) : null;
            if (pd != null) {
                bw.setPropertyValue(pd.getName(), map.get(key));
            }
        }

        return mappedObject;
    }

    protected String underscoreName(String name) {
        if (!StringUtils.hasLength(name)) {
            return "";
        } else {
            StringBuilder result = new StringBuilder();
            result.append(name.substring(0, 1).toLowerCase());

            for (int i = 1; i < name.length(); ++i) {
                String s = name.substring(i, i + 1);
                String slc = s.toLowerCase();
                if (!s.equals(slc)) {
                    result.append("_").append(slc);
                } else {
                    result.append(s);
                }
            }
            return result.toString();
        }
    }
}
