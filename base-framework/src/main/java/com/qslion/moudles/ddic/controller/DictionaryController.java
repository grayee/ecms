package com.qslion.moudles.ddic.controller;

import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.controller.BaseController;
import com.qslion.moudles.ddic.entity.DictDataValue;
import com.qslion.moudles.ddic.entity.DictDataType;
import com.qslion.moudles.ddic.service.DictionaryService;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据字典控制类
 *
 * @author Gray.Z
 * @date 2018/12/6.
 */
@ResponseResult
@RestController
@RequestMapping(value = "/sys/dict")
public class DictionaryController extends BaseController<DictDataType> {

    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping(value = "/detail/{typeId}")
    public List<DictDataValue> detail(@PathVariable Long typeId) {
        return dictionaryService.findByTypeId(typeId);
    }

    @PostMapping(value = "/list")
    public Pager<DictDataType> list(@RequestBody Pageable pageable) {
        return dictionaryService.findPage(pageable);
    }

    @PutMapping
    public boolean update(@RequestBody DictDataType dictDataType) {
        DictDataType baseDataType = dictionaryService.update(dictDataType);
        return baseDataType == null;
    }

    @PostMapping
    public Long save(@Validated @RequestBody DictDataType dictDataType) {
        DictDataType baseDataType = dictionaryService.save(dictDataType);
        return baseDataType.getId();
    }

    @PostMapping(value = "/{id}")
    public Long save(@PathVariable Long id, @Validated @RequestBody DictDataValue dictDataValue) {
        DictDataType baseDataType = dictionaryService.findById(id);
        dictDataValue.setDictDataType(baseDataType);
        DictDataValue baseData = dictionaryService.insert(dictDataValue);
        return baseData.getId();
    }

    @DeleteMapping
    public boolean delete(@RequestBody List<Long> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            for (Long id : ids) {
                dictionaryService.delete(id);
            }
        }
        return false;
    }
}
