package com.qslion.moudles.ddic.service.impl;

import com.qslion.framework.service.impl.GenericServiceImpl;
import com.qslion.moudles.ddic.dao.DictBaseDataRepository;
import com.qslion.moudles.ddic.dao.DictBaseDataTypeRepository;
import com.qslion.moudles.ddic.entity.DictBaseData;
import com.qslion.moudles.ddic.entity.DictBaseDataType;
import com.qslion.moudles.ddic.service.DictionaryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2018/12/6.
 */
@Service
public class DictionaryServiceImpl extends GenericServiceImpl<DictBaseDataType, Long> implements DictionaryService {

    @Autowired
    private DictBaseDataRepository dictBaseDataRepository;
    @Autowired
    private DictBaseDataTypeRepository dictBaseDataTypeRepository;


    @Override
    public DictBaseData insert(DictBaseData dictBaseData) {
        return dictBaseDataRepository.save(dictBaseData);
    }

    @Override
    public List<DictBaseData> findByTypeId(Long typeId) {
        return dictBaseDataRepository.findAll(Example.of(new DictBaseData()));
    }
}
