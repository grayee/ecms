package com.qslion.core.controller.au;

import com.google.common.collect.Lists;
import com.qslion.core.entity.AuPermission;
import com.qslion.core.entity.AuResource;
import com.qslion.core.entity.AuUser;
import com.qslion.core.service.AuResourceService;
import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.bean.TreeNode;
import com.qslion.framework.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 资源控制类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@ResponseResult
@RestController
@RequestMapping(value = "/auth/resource")
public class AuResourceController extends BaseController<AuResource> {

    @Autowired
    private AuResourceService resourceService;

    @PostMapping
    public Long save(@Validated @RequestBody AuResource resource) {
        AuResource auResource = resourceService.insert(resource);
        return auResource.getId();
    }

    @PostMapping(value = "/permission/{id}")
    public boolean addPermission(@PathVariable Long id, @Validated @RequestBody AuPermission permission) {
        return resourceService.addPermission(id, permission);
    }

    @PutMapping(value = "/permission/{id}")
    public boolean updatePermission(@PathVariable Long id, @RequestBody AuPermission permission) {
        return resourceService.updatePermission(id, permission);
    }

    @DeleteMapping(value = "/permission")
    public boolean removePermission(@RequestBody List<Long> ids) {
        return resourceService.removePermission(ids);
    }

    @GetMapping(value = "/tree")
    public List<TreeNode> getResourceTree(@ApiIgnore @AuthenticationPrincipal AuUser user) {
        List<AuPermission> userPerms = Lists.newArrayList();
        user.getRoles().forEach(auRole -> userPerms.addAll(auRole.getPermissions()));
        return resourceService.getResourceTree(userPerms,false);
    }
}
