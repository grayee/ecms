/**
 *
 */
package com.qslion.core.controller.au;


import com.qslion.core.entity.AuMenu;
import com.qslion.core.entity.AuResource;
import com.qslion.core.entity.AuUser;
import com.qslion.core.service.AuMenuService;
import com.qslion.core.service.AuResourceService;
import com.qslion.core.service.AuUserService;
import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.bean.TreeNode;
import com.qslion.framework.controller.BaseController;
import com.qslion.framework.util.JSONUtils;
import io.swagger.annotations.Api;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 菜单控制类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@Api(value="菜单Controller",description="菜单Controller",tags={"菜单控制器"})
@ResponseResult
@RestController
@RequestMapping(value = "/auth/menu")
public class AuMenuController extends BaseController<AuMenu> {

    @Autowired
    public AuMenuService auMenuService;
    @Autowired
    public AuResourceService resourceService;
    @Autowired
    public AuUserService auUserService;

    @GetMapping(value = "/detail/{id}")
    public AuMenu detail(@PathVariable Long id) {
        return auMenuService.findById(id);
    }

    @GetMapping(value = "/list")
    public Pager<AuMenu> list(Pageable pageable) {
        return auMenuService.findPage(pageable);
    }

    @PutMapping
    public boolean update(@RequestBody AuMenu menu) {
        if (menu.getResource() == null) {
            AuMenu oldAuMenu = auMenuService.findById(menu.getId());
            AuResource resource = resourceService.findByMenu(oldAuMenu);
            if (resource == null) {
                resource = menu.buildResource();
            } else {
                resource.setName(menu.getName());
                resource.setValue(menu.getUrl());
            }
            menu.setLeaf(oldAuMenu.isLeaf());
            menu.setLevel(oldAuMenu.getLevel());
            menu.setEnableStatus(oldAuMenu.getEnableStatus());
            menu.setStatus(oldAuMenu.getStatus());
            menu.setResource(resource);
        }
        AuMenu auMenu = auMenuService.update(menu);
        return auMenu != null;
    }

    @PostMapping
    public Long save(@Validated @RequestBody AuMenu menu) {
        AuMenu auMenu = auMenuService.insert(menu);
        return auMenu.getId();
    }

    @DeleteMapping
    public boolean delete(@RequestBody List<Long> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            auMenuService.remove(ids);
        }
        return false;
    }

    @GetMapping(value = "/tree")
    public List<TreeNode> getMenuTree(@ApiIgnore @AuthenticationPrincipal AuUser user) {
        String username = StringUtils.defaultString(user.getUsername(), auUserService.getCurrentUsername());
        //根据登录用户获取菜单树
        List<TreeNode> menuTree = this.auMenuService.getMenuTree(username);
        if (CollectionUtils.isNotEmpty(menuTree) && menuTree.size() <= 1) {
            menuTree = menuTree.get(0).getChildren();
        }
        logger.info("用户：{} 菜单树JSON:{}", username, JSONUtils.writeValueAsString(menuTree));
        return menuTree;
    }

}
