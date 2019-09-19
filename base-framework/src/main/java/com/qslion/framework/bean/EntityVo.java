package com.qslion.framework.bean;

import com.qslion.framework.entity.BaseEntity;
import com.qslion.framework.util.ReflectUtils;
import com.qslion.moudles.ddic.entity.DictDataType;
import org.springframework.util.ReflectionUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
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

    public static EntityVo getResult(Object obj) {
        EntityVo entityVo = new EntityVo();
        for (Field field : ReflectUtils.getFields(obj.getClass())) {
            entityVo.put(field.getName(), ReflectUtils.getValueByField(field, obj));
        }
        return entityVo;
    }

    public static void main(String[] args) {
        DictDataType test = new DictDataType();
        test.setCode("1");
        test.setSystem(true);

        EntityVo vo = EntityVo.getResult(test);
        System.out.println(vo);
    }
}
