package com.qslion.core.controller.au;

import com.alibaba.fastjson.JSONException;
import com.mysql.jdbc.StringUtils;
import com.qslion.core.entity.AuResource;
import com.qslion.core.entity.AuUser;
import com.qslion.core.service.AuResourceService;
import com.qslion.core.util.TreeNode;
import com.qslion.framework.controller.BaseController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "au/resource")
public class AuResourceController extends BaseController<AuResource> {

    @Autowired
    public AuResourceService resourceService;

    @RequestMapping(value = "/namager")
    public String manage() {
        return ("manage");
    }

    @RequestMapping(value = "/default")
    public String getDefault() {
        return ("default");
    }

    @RequestMapping(value = "/menuDetail")
    public String getMenuNodeInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model, String menuId) {

        return ("detail");
    }

    //初始化添加子菜单父级节点信息,修改
    @RequestMapping(value = "/admin/functree/input.jspx")
    public String input(HttpServletRequest request, HttpServletResponse response, ModelMap model, String nodeId, AuResource entity) {
        AuResource parentNode = null;//service.get(nodeId);
        String cmd = request.getParameter("cmd");
        if (!StringUtils.isNullOrEmpty(cmd)) {
            // entity.setParentCode(parentNode.getCode());
            entity.setName(parentNode.getName());
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", parentNode);
        }
        return ("input");
    }

    //添加
    @RequestMapping(value = "/insert")
    public String insert(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") AuResource entity) {
        if (this.resourceService.checkUnique(entity)) {
            model.addAttribute("error_info", "功能节点名称重复");
            return ("manage");
        }
        AuResource parent = this.resourceService.getParent(entity);

        //插入对应的资源
        String code = String.valueOf(resourceService.getParentChilds(entity).size());
        int stepLen = 3;
        if ((code == null) || (code.length() == 0)) {
            code = "1";
        } else {
            code = String.valueOf(Integer.parseInt(code) + 1);
        }
        while (code.length() < stepLen) {
            code = "0" + code;
        }
        //code = entity.getParentCode() + code;

        AuResource resVo = new AuResource();

        resVo.setName(entity.getName());

        resVo.setCreateDate(entity.getCreateDate());
        resVo.setEnableStatus("1");

        resVo.setValue(entity.getValue());

        // resVo.setAuFuncTree(entity);
        //this.resourceService.insert(resVo);  
        //this.resourceService.insert(entity);
        return ("manage");
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
        List<AuResource> childrens = resourceService.getChildrens(entity);
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
        if (resourceService.getParentChilds(entity).size() <= 0) {
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
        List<TreeNode> resultList = this.resourceService.getFuncMenuTree(loginUser, status, request.getContextPath());
        //model.addAttribute("data", JSONUtil.serialize(resultList));
        model.addAttribute("status", status);
        return ("tree");
    }
}
