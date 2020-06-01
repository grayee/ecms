/**
 *
 */
package com.qslion.authority.core.controller.au;


import com.qslion.authority.core.entity.AuMenu;
import com.qslion.authority.core.entity.AuResource;
import com.qslion.authority.core.entity.AuUser;
import com.qslion.authority.core.enums.MenuType;
import com.qslion.authority.core.service.AuMenuService;
import com.qslion.authority.core.service.AuResourceService;
import com.qslion.authority.core.service.AuUserService;
import com.qslion.authority.core.util.TreeTools;
import com.qslion.framework.bean.*;
import com.qslion.framework.controller.BaseController;
import com.qslion.framework.util.CGlibMapper;
import com.qslion.framework.util.CopyUtils;
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
@Api(value = "菜单Controller", description = "菜单Controller", tags = {"菜单控制器"})
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

    @PostMapping(value = "/list")
    public Pager<EntityVo> list(@RequestBody Pageable pageable) {
        Pager<AuMenu> pager = auMenuService.getMenuList(pageable);
        pager.addExtras("menuTypeMap", MenuType.getMapList());
        return pager.wrap(EntityVo::getEntityVo);
    }

    @PutMapping
    public boolean update(@RequestBody AuMenu menu) {
        AuMenu oldAuMenu = auMenuService.findById(menu.getId());
        if (menu.getResource() == null) {
            AuResource resource = resourceService.findByMenu(oldAuMenu);
            if (resource == null) {
                resource = menu.buildResource();
            } else {
                resource.setName(menu.getName());
                resource.setValue(menu.getUrl());
            }
            oldAuMenu.setResource(resource);
        }
        CopyUtils.copyProperties(menu, oldAuMenu);
        AuMenu auMenu = auMenuService.update(oldAuMenu);
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
        List<AuMenu> menuList = this.auMenuService.getMenuList(username);
        List<TreeNode> menuTree = TreeTools.getTreeList(menuList);
        if (CollectionUtils.isNotEmpty(menuTree) && menuTree.size() <= 1) {
            menuTree = menuTree.get(0).getChildren();
        }
        logger.info("用户：{} 菜单树JSON:{}", username, JSONUtils.writeValueAsString(menuTree));
        return menuTree;
    }

}
