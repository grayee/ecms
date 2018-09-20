/**
 *
 */
package com.qslion.core.controller.au;

import com.qslion.core.entity.AuMenu;
import com.qslion.core.entity.AuParty;
import com.qslion.core.entity.AuResource;
import com.qslion.core.service.AuResourceService;
import com.qslion.core.service.AuUserService;
import com.qslion.core.service.AuMenuService;
import com.qslion.core.util.TreeNode;
import com.qslion.framework.bean.PropertyGrid;
import com.qslion.framework.controller.BaseController;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/function/menu")
public class FuncMenuAction extends BaseController<AuMenu, Long> {


    public static final String PAGE_PATH = "authority/au/functree/";
    @Autowired
    public AuMenuService auMenuService;
    @Autowired
    public AuResourceService resourceService;
    @Autowired
    public AuUserService auUserService;


    //菜单
    @RequestMapping(value = "/admin/getFuncMenu.jspx")
    public String manage() {
        return forward("manage");
    }

    //
    @RequestMapping(value = "/admin/functree/default.jspx")
    public String getDefault() {
        return forward("default");
    }

    //查询 菜单节点信息
    @RequestMapping(value = "/input")
    public PropertyGrid<AuMenu> getMenuNodeInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model, String menuId) {
       /* entity = service.get(menuId);
        PropertyGrid<AuMenu> pg = new PropertyGrid(entity);*/
        return null;
    }

    //初始化添加子菜单父级节点信息,修改
    @RequestMapping(value = "/admin/functree/input.jspx")
    public String input(HttpServletRequest request, HttpServletResponse response, ModelMap model, String nodeId, AuMenu entity) {
       // AuMenu parentNode = service.get(nodeId);
        String cmd = request.getParameter("cmd");
  /*      if (!StringUtils.isNotEmpty(cmd)) {
            entity.setParentId(parentNode.getId());
            entity.setName(parentNode.getName());
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", parentNode);
        }*/
        return forward("input");
    }

    //添加
    @RequestMapping(value = "/admin/functree/insert.jspx")
    public String insert(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") AuMenu entity) {
        if (this.auMenuService.checkUnique(entity)) {
            model.addAttribute("error_info", "功能节点名称重复");
            return forward("manage");
        }
        AuMenu parent = this.auMenuService.getParent(entity);
        if (("1".equals(parent.isLeaf()))) {
            boolean isUpdate = false;
            if ("1".equals(parent.isLeaf())) {
                parent.setLeaf(false);
                isUpdate = true;
            }

            if (isUpdate) {
                parent.setModifyDate(new Date());
                auMenuService.update(parent);
            }
        }
        //插入对应的资源
        String code = String.valueOf(auMenuService.getParentChilds(entity).size());

        code = entity.getParentId() + String.format("%003d", code);

        AuResource resVo = new AuResource();
        //resVo.setResourceType(entity.getType().toString());
        resVo.setName(entity.getName());
        //resVo.setIsPublic("0");
        //resVo.setIsSystem(false);
        //resVo.setOrderList(0);
        resVo.setCreateDate(entity.getCreateDate());
        resVo.setEnableStatus("1");
       // resVo.setAccessType(1);
        resVo.setValue(entity.getUrl());
       // resVo.setFieldName(entity.getKeyword());
       // resVo.setFilterType(entity.getHotKey());
       // resVo.setTableName("");
      //  resVo.setHelp(entity.getRemark());


        // resVo.setAuFuncTree(entity);
        //this.resourceService.insert(resVo);
        //this.auMenuService.insert(entity);
        return forward("manage");
    }

    //更新
    @RequestMapping(value = "/admin/functree/update.jspx")
    public String update(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") AuMenu entity) {
        //级联更新
        //AuResource resVo = this.resourceService.get(entity.getId());
       // resVo.setName(entity.getName());
        //resVo.setValue(entity.getUrl());
        this.auMenuService.update(entity);
        return forward("manage");
    }

    @RequestMapping(value = "/admin/functree/delete.jspx")
    public String delete(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") AuMenu entity) {
        boolean flag = false;
        //步骤：首先判断该节点是否包含子节点，有的话下需要删除子节点,同时删除对应的资源然后更新父节点的状态
        //entity = //auMenuService.get(entity.getId());
        //存在下级子节点
        if (!entity.isLeaf()) {
            List<AuMenu> childrens = auMenuService.getChildrens(entity);
            String[] ids = new String[childrens.size()];
            for (int i = 0; i < childrens.size(); i++) {
              //  ids[i] = childrens.get(i).getId();
            }
            //删除所有下级子节点以及相应的资源
           // auMenuService.delete(ids);
            //resourceService.delete(ids);
        }
        auMenuService.delete(entity.getId());
        //resourceService.delete(entity.getId());
        //判断父节点是否还 存在子节点
        if (auMenuService.getParentChilds(entity).size() <= 0) {
            AuMenu parent = this.auMenuService.getParent(entity);
            auMenuService.update(parent);
        }
        return forward("manage");
    }

    //动态构建功能菜单
    @RequestMapping(value = "/tree")
    public List<TreeNode> getFuncMenuTree(HttpServletRequest request, HttpServletResponse response, ModelMap model, String status, AuParty visitor) {
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

    public String forward(String viewName) {
        String view = PAGE_PATH + viewName;
        return view;
    }

}
