/**
 *
 */
package com.qslion.core.controller.au;


import com.qslion.core.entity.AuMenu;
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
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
 * 菜单控制类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@Api(value="菜单Controller",description="菜单Controller",tags={"菜单控制器"})
@ResponseResult
@RestController
@RequestMapping(value = "/au/menu")
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
        AuMenu auMenu = auMenuService.update(menu);
        return auMenu == null;
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
    public List<TreeNode> getMenuTree(@AuthenticationPrincipal AuUser user) {
        String username = StringUtils.defaultString(user.getUsername(), auUserService.getCurrentUsername());
        //根据登录用户获取菜单树
        List<TreeNode> menuTree = this.auMenuService.getMenuTree(username);
        logger.info("用户：{} 菜单树JSON:{}", username, JSONUtils.writeValueAsString(menuTree));
        return menuTree;
    }

}
