package com.qslion.authority.core.controller.au;

import com.qslion.authority.core.entity.AuPartyRelation;
import com.qslion.authority.core.entity.AuRole;
import com.qslion.authority.core.entity.AuUser;
import com.qslion.authority.core.service.AuRoleService;
import com.qslion.authority.core.service.AuUserService;
import com.qslion.authority.core.service.PartyRelationService;
import com.qslion.authority.custom.entity.AuEmployee;
import com.qslion.authority.custom.service.AuEmployeeService;
import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.controller.BaseController;
import io.swagger.annotations.Api;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
 * 用户控制类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@Api(value = "用户Controller", description = "用户Controller", tags = {"用户控制器"})
@ResponseResult
@RestController
@RequestMapping(value = "/au/user")
public class AuUserController extends BaseController<AuUser> {

    @Autowired
    private AuUserService auUserService;
    @Autowired
    private AuRoleService roleService;
    @Autowired
    private PartyRelationService partyRelationService;
    @Autowired
    private AuEmployeeService auEmployeeService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 列表
     */
    @PostMapping(value = "/list")
    public Pager<AuUser> list(@RequestBody Pageable pageable) {
        return auUserService.findPage(pageable);
    }

    /**
     * 增加
     */
    @PostMapping
    public Long save(@Validated @RequestBody AuUser user) {
        String encrypt = passwordEncoder.encode(user.getPassword());
        user.setPassword(encrypt);
        AuUser auUser = auUserService.save(user);
        return auUser.getId();
    }

    /**
     * 通过关联职员档案增加
     */
    @PostMapping(value = "/ref")
    public Long refSave(@Validated @RequestBody AuUser user) {
        String encrypt = passwordEncoder.encode(user.getPassword());
        user.setPassword(encrypt);
        AuPartyRelation relation = partyRelationService.findById(Long.valueOf(user.getUsername()));
        AuEmployee emp = auEmployeeService.findById(relation.getPartyId());
        user.setUsername(emp.getEnglishName());
        user.setEmail(emp.getEmail());
        user.setMobile(emp.getMobilePhone());
        AuUser auUser = auUserService.save(user);
        return auUser.getId();
    }

    /**
     * 删除
     */
    @DeleteMapping
    public boolean delete(@RequestBody List<Long> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            ids.forEach(id -> auUserService.delete(id));
            return true;
        }
        return false;
    }

    /**
     * 更新
     */
    @PutMapping
    public boolean update(@RequestBody AuUser user) {
        AuUser auUser = auUserService.update(user);
        return auUser == null;
    }

    /**
     * 查看，明细
     */
    @GetMapping(value = "/detail/{id}")
    public AuUser detail(@PathVariable Long id) {
        return auUserService.findById(id);
    }


    /**
     * 用户关联角色
     */
    @PostMapping(value = "/role/{userId}")
    public Boolean grantRoleAuth(@PathVariable Long userId, @RequestBody Long[] relationIds) {
        AuUser user = auUserService.findById(userId);
        List<AuPartyRelation> relations = partyRelationService.findList(relationIds);
        Long[] roleIds = relations.stream().map(AuPartyRelation::getPartyId).collect(Collectors.toList()).toArray(new Long[0]);
        List<AuRole> roles = roleService.findList(roleIds);
        user.getRoles().addAll(roles);
        return auUserService.saveOrUpdate(user) == null;
    }
}