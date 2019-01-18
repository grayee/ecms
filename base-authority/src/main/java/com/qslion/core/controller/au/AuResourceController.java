package com.qslion.core.controller.au;

import com.alibaba.fastjson.JSONException;
import com.qslion.core.entity.AuResource;
import com.qslion.core.entity.AuUser;
import com.qslion.core.service.AuResourceService;
import com.qslion.framework.bean.TreeNode;
import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.controller.BaseController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 资源控制类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@ResponseResult
@RestController
@RequestMapping(value = "/au/resource")
public class AuResourceController extends BaseController<AuResource> {

    @Autowired
    public AuResourceService resourceService;

    @PostMapping
    public Long save(@Validated @RequestBody AuResource resource) {
       AuResource auResource = resourceService.insert(resource);
       return  auResource.getId();
    }

    @RequestMapping(value = "/update")
    public String update(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") AuResource entity) {
        //级联更新
        AuResource resVo = null;//this.resourceService.get(entity.getId());
        resVo.setName(entity.getName());
        resVo.setValue(entity.getValue());
        this.resourceService.update(entity);
        return ("manage");
    }

    @DeleteMapping
    public String delete(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") AuResource entity) {
        boolean flag = false;
        //步骤：首先判断该节点是否包含子节点，有的话下需要删除子节点,同时删除对应的资源然后更新父节点的状态
        //entity = resourceService.get(entity.getId());
        //存在下级子节点
        //if (entity.getIsLeaf().equals("0")) {
        List<AuResource> childrens = resourceService.getChildren(entity);
        String[] ids = new String[childrens.size()];
        for (int i = 0; i < childrens.size(); i++) {
            //ids[i] = childrens.get(i).getId();
        }
        //删除所有下级子节点以及相应的资源
        // resourceService.delete(ids);
        //resourceService.delete(ids);
        // }
        resourceService.delete(entity.getId());
        //resourceService.delete(entity.getId());
        //判断父节点是否还 存在子节点
        if (resourceService.getParentChildren(entity).size() <= 0) {
            AuResource parent = this.resourceService.getParent(entity);
            //parent.isLeaf(true);
            resourceService.update(parent);
        }
        return ("manage");
    }

    @RequestMapping(value = "/funcManage.jspx")
    public String getFuncMenuTree(HttpServletRequest request, HttpServletResponse response, ModelMap model, String status) throws JSONException {
        logger.info("-------------------------构建系统菜单---------------------------------------");
        AuUser loginUser = (AuUser) request.getSession().getAttribute("loginUser");
        //根据登录用户获取菜单树
        List<TreeNode> resultList = this.resourceService.getResourceTree(loginUser, status, request.getContextPath());
        //model.addAttribute("data", JSONUtil.serialize(resultList));
        model.addAttribute("status", status);
        return ("tree");
    }
}
