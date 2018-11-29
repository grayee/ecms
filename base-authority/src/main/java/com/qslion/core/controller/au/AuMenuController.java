/**
 *
 */
package com.qslion.core.controller.au;


import com.qslion.core.entity.AuMenu;
import com.qslion.core.entity.AuParty;
import com.qslion.core.service.AuMenuService;
import com.qslion.core.service.AuResourceService;
import com.qslion.core.service.AuUserService;
import com.qslion.core.util.TreeNode;
import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.controller.BaseController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
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
 * 菜单制类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@ResponseResult
@RestController
@RequestMapping(value = "/au/menu")
public class AuMenuController extends BaseController<AuMenu, Long> {

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

    //动态构建功能菜单
    @RequestMapping(value = "/tree")
    public List<TreeNode> getFuncMenuTree(HttpServletRequest request, HttpServletResponse response, ModelMap model,
        String status, AuParty visitor) {
        logger.info("-------------------------构建系统菜单---------------------------------------");
      /*  if (null == visitor.getAuPartyType()) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            *//*AuUser auUser=userService.get("loginId", userDetails.getUsername());
            visitor=auUser.getAuParty();*//*
        }*/
        //根据登录用户获取菜单树
        List<TreeNode> resultList = this.auMenuService.getFuncMenuTree(visitor, request.getContextPath());
        return resultList;
    }

}
