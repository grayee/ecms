/**
 *
 */
package com.qslion.authority.custom.controller;


import com.qslion.authority.core.entity.AuOrgRelation;
import com.qslion.authority.core.enums.AuOrgRelationType;
import com.qslion.authority.core.service.AuOrgRelationService;
import com.qslion.authority.core.service.ConnectionRuleService;
import com.qslion.authority.core.util.TreeTools;
import com.qslion.framework.bean.EntityVo;
import com.qslion.framework.bean.Pageable;
import com.qslion.authority.custom.entity.AuPosition;
import com.qslion.authority.custom.service.AuPositionService;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.controller.BaseController;
import java.util.List;

import com.qslion.framework.util.ValidatorUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 岗位控制类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@ResponseResult
@RestController
@RequestMapping(value = "/org/position")
public class PositionController extends BaseController<AuPosition> {

    @Autowired
    public AuPositionService positionService;
    @Autowired
    public AuOrgRelationService auOrgRelationService;
    @Autowired
    public ConnectionRuleService connectionRuleService;


    /**
     * 保存
     *
     * @param position 岗位
     * @return ID
     */
    @ApiOperation("保存岗位信息")
    @PostMapping
    public Long save(@Validated({ValidatorUtils.AddGroup.class}) @RequestBody AuPosition position) {
        AuPosition auPosition = positionService.insert(position);
        return auPosition.getId();
    }


    /**
     * 从页面的表单获取团体关系id，并删除团体关系及相关的权限记录
     */
    @DeleteMapping
    public boolean delete(@RequestBody List<Long> ids) {
        return CollectionUtils.isNotEmpty(ids) && positionService.remove(ids);
    }

    /**
     * 从页面表单获取信息注入vo，并修改单条记录，同时调用接口更新相应的团体、团体关系记录
     */
    @PutMapping
    public boolean update(@RequestBody AuPosition position) {
        AuPosition auPosition = positionService.update(position);
        return auPosition == null;
    }

    @PostMapping(value = "/list")
    public Pager<EntityVo> list(@RequestBody Pageable pageable) {
        Pager<AuPosition> pager = positionService.findPage(pageable);
        List<AuOrgRelation> relations = auOrgRelationService.findByRelationType(AuOrgRelationType.ADMINISTRATIVE);
        return pager.wrap(position -> {
            EntityVo ev = EntityVo.getEntityVo(position);
            ev.put("parentId", TreeTools.getTreePathStr(relations, position.getParentId()));
            return ev;
        });
    }

    /**
     * 详细信息
     */
    @GetMapping(value = "/detail/{id}")
    public AuPosition detail(@PathVariable Long id) {
        return positionService.findById(id);
    }

}
