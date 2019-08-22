package com.qslion.framework.bean;

import com.google.common.collect.Lists;
import com.qslion.moudles.ddic.entity.DictDataType;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * EntityVo
 *
 * @author Gray.Z
 * @date 2018/9/20.
 */
public class EntityVo extends LinkedHashMap<String, Object> {

    public static EntityVo get(Object obj, List<String> fields) {
        EntityVo entityVo = new EntityVo();
        if (!fields.contains("id")) {
            fields.add("id");
        }
        for (String field : fields) {
            try {
                PropertyDescriptor pd = new PropertyDescriptor(field, obj.getClass());
                entityVo.put(field, pd.getReadMethod().invoke(obj));
            } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return entityVo;
    }

    public static void main(String[] args) {
        DictDataType test = new DictDataType();
        test.setCode("1");
        test.setSystem(true);

        EntityVo vo = EntityVo.get(test, Lists.newArrayList("code", "system"));
        System.out.println(vo);
    }
}
