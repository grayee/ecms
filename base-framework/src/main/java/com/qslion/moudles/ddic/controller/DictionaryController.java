package com.qslion.moudles.ddic.controller;

import com.google.common.collect.Lists;
import com.qslion.framework.bean.*;
import com.qslion.framework.controller.BaseController;
import com.qslion.moudles.ddic.entity.DictDataType;
import com.qslion.moudles.ddic.entity.DictDataValue;
import com.qslion.moudles.ddic.service.DictionaryService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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


    @GetMapping(value = "/tree/{typeId}")
    public List<TreeNode> tree(@PathVariable Long typeId) {
        DictDataType dictDataType = dictionaryService.findById(typeId);
        TreeNode root = new TreeNode(dictDataType.getId().toString(), dictDataType.getName());
        root.setState(TreeNode.NodeState.OPEN);
        if (dictDataType != null) {
            List<TreeNode> valueNodes = dictDataType.getDictDataValueList().stream().map(dictDataValue -> {
                TreeNode tn = new TreeNode(dictDataValue.getId().toString(), dictDataValue.getName());
                tn.setState(TreeNode.NodeState.OPEN);
                tn.addAttribute("meta",dictDataValue);
                return tn;
            }).collect(Collectors.toList());
            root.setChildren(valueNodes);
        }
        return Lists.newArrayList(root);
    }

    @GetMapping(value = "/detail/{typeId}")
    public List<DictDataValue> detail(@PathVariable Long typeId) {
        return dictionaryService.findByTypeId(typeId);
    }

    @PostMapping(value = "/list")
    public Pager<EntityVo> list(@RequestBody Pageable pageable) {
        Pager<DictDataType> pager = dictionaryService.findPage(pageable);
        return pager.wrap(getEntityVoFunction());
    }

    private Function<DictDataType, EntityVo> getEntityVoFunction() {
        return dictDataType -> {
            EntityVo ev = EntityVo.getResult(dictDataType);
            ev.put("isSystem", dictDataType.isSystem() ? "是" : "否");
            return ev;
        };
    }

    @PutMapping
    public boolean update(@RequestBody DictDataType dictDataType) {
        DictDataType update= dictionaryService.findById(dictDataType.getId());
        update.setCode(dictDataType.getCode());
        update.setName(dictDataType.getName());
        update.setDescription(dictDataType.getDescription());
        DictDataType baseDataType = dictionaryService.update(update);
        return baseDataType == null;
    }

    @PutMapping(value = "/{id}")
    public boolean updateValue(@PathVariable Long id, @Validated @RequestBody DictDataValue dictDataValue) {
        return  dictionaryService.updateValue(id,dictDataValue);
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

    @DeleteMapping(value = "/{id}")
    public boolean deleteValue(@PathVariable Long id) {
        return dictionaryService.deleteValue(id);
    }
}
