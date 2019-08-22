package com.qslion.moudles.ddic.service;

import com.qslion.framework.bean.TreeNode;
import com.qslion.framework.service.IGenericService;
import com.qslion.moudles.ddic.entity.DictDataValue;
import com.qslion.moudles.ddic.entity.DictDataType;

import java.util.List;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2018/12/6.
 */
public interface DictionaryService extends IGenericService<DictDataType, Long> {

    /**
     * 保存字典值
     *
     * @param dictDataValue 字典数据
     * @return 字典值
     */
    DictDataValue insert(DictDataValue dictDataValue);

    /**
     * 获取字典列表
     *
     * @param typeId 字典类型ID
     * @return 字典列表
     */
    List<DictDataValue> findByTypeId(Long typeId);

    /**
     * 获取字典列表
     *
     * @param code 字典编码
     * @return 字典列表
     */
    List<DictDataValue> findByCode(String code);

}
