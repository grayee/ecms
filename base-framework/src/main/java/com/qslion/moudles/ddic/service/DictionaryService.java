package com.qslion.moudles.ddic.service;

import com.qslion.framework.service.IGenericService;
import com.qslion.moudles.ddic.entity.DictBaseData;
import com.qslion.moudles.ddic.entity.DictBaseDataType;
import java.util.List;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2018/12/6.
 */
public interface DictionaryService extends IGenericService<DictBaseDataType, Long> {

    /**
     * 保存字典值
     *
     * @param dictBaseData 字典数据
     * @return 字典值
     */
    DictBaseData insert(DictBaseData dictBaseData);

    /**
     * 获取字典列表
     *
     * @param typeId 字典类型ID
     * @return 字典列表
     */
    List<DictBaseData> findByTypeId(Long typeId);
}
