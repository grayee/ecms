package com.qslion.moudles.ddic.dao;

import com.qslion.framework.dao.IGenericRepository;
import com.qslion.moudles.ddic.entity.DictDataType;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2018/11/1.
 */
public interface DictDataTypeRepository extends IGenericRepository<DictDataType, Long> {


    /**
     * 根据编码查询类型
     *
     * @param code 字典编码
     * @return 字典类型
     */
    DictDataType findByCode(String code);
}
