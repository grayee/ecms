package com.qslion.moudles.ddic.service.impl;

import com.qslion.framework.service.impl.GenericServiceImpl;
import com.qslion.moudles.ddic.dao.DictDataTypeRepository;
import com.qslion.moudles.ddic.dao.DictDataValueRepository;
import com.qslion.moudles.ddic.entity.DictDataType;
import com.qslion.moudles.ddic.entity.DictDataValue;
import com.qslion.moudles.ddic.service.DictionaryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2018/12/6.
 */
@Service
@Transactional(value = "transactionManager", rollbackFor = Exception.class)
public class DictionaryServiceImpl extends GenericServiceImpl<DictDataType, Long> implements DictionaryService {

    @Autowired
    private DictDataValueRepository dictDataValueRepository;
    @Autowired
    private DictDataTypeRepository dictDataTypeRepository;


    @Override
    public DictDataValue insert(DictDataValue dictDataValue) {
        return dictDataValueRepository.save(dictDataValue);
    }

    @Override
    public boolean deleteValue(Long id) {
        dictDataValueRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean updateValue(Long id, DictDataValue dictDataValue) {
        DictDataType dictDataType = dictDataTypeRepository.findById(id).orElse(null);
        dictDataValue.setDictDataType(dictDataType);
        dictDataValueRepository.saveAndFlush(dictDataValue);
        return false;
    }

    @Override
    public List<DictDataValue> findByTypeId(Long typeId) {
        DictDataType dictDataType = dictDataTypeRepository.findById(typeId).orElse(null);
        if (dictDataType != null) {
            return dictDataType.getDictDataValueList();
        }
        return null;
    }

    @Override
    public List<DictDataValue> findByCode(String code) {
        DictDataType dictDataType = dictDataTypeRepository.findByCode(code);
        if (dictDataType != null) {
            return dictDataType.getDictDataValueList();
        }
        return null;
    }
}
