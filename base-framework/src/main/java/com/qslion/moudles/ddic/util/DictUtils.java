package com.qslion.moudles.ddic.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qslion.framework.util.SpringUtil;
import com.qslion.moudles.ddic.entity.DictDataType;
import com.qslion.moudles.ddic.entity.DictDataValue;
import com.qslion.moudles.ddic.service.DictionaryService;
import com.qslion.moudles.ddic.service.impl.DictionaryServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据字典控制类
 *
 * @author Gray.Z
 * @date 2018/12/6.
 */
public class DictUtils {
    /**
     * 存储字典,可换redis存储
     */
    private static Map<String, List<Map<String, Object>>> dictMaps = new ConcurrentHashMap<>();

    static {
        DictionaryService dictService = (DictionaryService) SpringUtil.getBean("dictionaryServiceImpl");
        //获取所有已启用的数据字典
        List<DictDataType> dicts = dictService.findAll();

        for (DictDataType dictType : dicts) {
            String code = dictType.getCode();
            for (DictDataValue dictDataValue : dictType.getDictDataValueList()) {
                //查看字典集合是否已经有该数据,如果没有就创建
                if (dictMaps.get(code) == null) {
                    List<Map<String, Object>> dictList = Lists.newArrayList();
                    Map<String, Object> dictMap = Maps.newHashMap();
                    dictMap.put(dictDataValue.getValue(), dictDataValue.getName());
                    dictList.add(dictMap);
                    dictMaps.put(code, dictList);
                } else {//如果有就直接加进去
                    List<Map<String, Object>> dictList = dictMaps.get(code);
                    Map<String, Object> dictMap = Maps.newHashMap();
                    dictMap.put(dictDataValue.getValue(), dictDataValue.getName());
                    dictList.add(dictMap);
                    dictMaps.put(code, dictList);
                }
            }
        }
    }

    //获取下拉列表
    public List<Map<String, Object>> getMapList(String code) {
        return dictMaps.get(code);
    }

    //获取字典值
    public static String getValue(String code, String type) {
        List<Map<String, Object>> maps = dictMaps.get(code);
        for (Map<String, Object> map : maps) {
            if (map.get(type) != null) {
                return map.get(type).toString();
            }
        }
        return null;
    }
}
