package com.qslion.framework.bean;

import com.google.common.collect.Maps;
import com.qslion.framework.enums.IEnum;
import com.qslion.framework.util.ReflectUtils;
import com.qslion.moudles.ddic.entity.DictDataType;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * EntityVo
 *
 * @author Gray.Z
 * @date 2018/9/20.
 */
public class EntityVo extends LinkedHashMap<String, Object> {

    private static final String FIELD_ID = "id";
    private static final String EXTRAS = "extras";

    public static EntityVo getEntityVo(Object obj) {
        EntityVo entityVo = new EntityVo();
        for (Field field : ReflectUtils.getFields(obj.getClass())) {
            if (field.isAnnotationPresent(DisplayField.class) || FIELD_ID.equals(field.getName())) {
                Object fieldValueObj = ReflectUtils.getValueByField(field, obj);
                if (fieldValueObj instanceof IEnum) {
                    entityVo.put(((IEnum) fieldValueObj).getIdKey(), fieldValueObj);
                    entityVo.put(field.getName(), ((IEnum) fieldValueObj).getDisplayName());
                } else {
                    entityVo.put(field.getName(), fieldValueObj);
                }
            }
        }
        return entityVo;
    }
    
    public void addExtras(String key, Object value) {
        Map<String, Object> extraMap = (Map<String, Object>) get(EXTRAS);
        if (extraMap == null) {
            extraMap = Maps.newHashMap();
            put(EXTRAS, extraMap);
        }
        extraMap.put(key, value);
    }

    public static void main(String[] args) {
        DictDataType test = new DictDataType();
        test.setCode("1");
        test.setSystem(true);

        EntityVo vo = EntityVo.getEntityVo(test);
        System.out.println(vo);
    }
}
