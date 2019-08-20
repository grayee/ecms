package com.qslion.core.controller.au;

import com.alibaba.fastjson.JSON;
import com.qslion.core.entity.AuUser;
import com.qslion.core.service.AuRoleService;
import com.qslion.core.service.AuUserService;
import com.qslion.core.service.PartyRelationService;
import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.bean.TreeNode;
import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.controller.BaseController;
import io.swagger.annotations.Api;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@Api(value="用户Controller",description="用户Controller",tags={"用户控制器"})
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
     * 授权管理>>用户授权
     */
    @RequestMapping(value = "/getAuthUser")
    public String getAuthUser(HttpServletRequest request, HttpServletResponse response, ModelMap model,
        @ModelAttribute("pager") Pager<AuUser> pager) {
        // pager = auUserService.findByPager(pager);
        model.addAttribute("pager", pager);
        return "";
    }

    /**
     * 授权管理>>用户授权>>用户关联角色
     */
    @RequestMapping(value = "/setReferenceRole")
    public String setReferenceRole(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String roleIds = "";
        String userId = "";
        // AuUser user = auUserService.get(userId);
        String[] roleIdArray = roleIds.split(",");
        for (int i = 0; i < roleIdArray.length; i++) {
            // AuRole role = roleService.get(roleIdArray[i]);
            //role.getAuUserSet().add(user);
            // user.getAuRoleSet().add(role);
        }
        // auUserService.insert(user);
        return "";
    }


    /**
     * 跳转用户授权
     */
    @RequestMapping(value = "/grantAuthDetail")
    public String grantAuthDetail(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String userId = "";
        // AuUser user = auUserService.get(userId);
        List<TreeNode> resultList = null;//partyRelationService.getPartyDetailRelationTree(user.getAuParty().getId(), GlobalConstants.getRelTypeComp());
        model.addAttribute("data", JSON.toJSON(resultList));
        // model.addAttribute("user", user);
        return "";
    }
}