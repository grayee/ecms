package com.qslion.custom.controller;

import com.alibaba.fastjson.JSON;
import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.core.service.ConnectionRuleService;
import com.qslion.core.service.PartyRelationService;
import com.qslion.core.util.TreeNode;
import com.qslion.custom.entity.AuDepartment;
import com.qslion.custom.service.AuDepartmentService;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.controller.BaseController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 部门控制类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@ResponseResult
@RestController
@RequestMapping(value = "/org/department")
public class DepartmentController extends BaseController<AuDepartment, Long> {

    @Autowired
    public AuDepartmentService departmentService;
    @Autowired
    public PartyRelationService partyRelationService;
    @Autowired
    public ConnectionRuleService connectionRuleService;


    @PostMapping
    public Long save(@Validated @RequestBody AuDepartment department, @RequestParam(required = false) Long parentCode) {
        AuDepartment auDepartment = departmentService.insert(department, parentCode);
        return auDepartment.getId();
    }

    //编辑，新增
    @RequestMapping(value = "/admin/department/input.jspx")
    public String input(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String id = request.getParameter("ids");
        String parentRelId = request.getParameter("parentRelId");
        //组织机构新增,公司新增
        if (!StringUtils.isNotEmpty(parentRelId)) {
            model.addAttribute("parentRelId", parentRelId);
        } else {
            //更新
            if (!StringUtils.isNotEmpty(id)) {
               /* AuDepartment entity = departmentService.get(id);*/
           /*     String parentCode = partyRelationService.getRelationByPartyId(entity.getId(), AuPartyRelationType.ADMINISTRATIVE.getId() + "").getParentId();
                if (parentCode != null && !"" .equals(parentCode)) {
                    model.addAttribute("relation", partyRelationService.get(parentCode));
                }*/
               /* model.addAttribute("entity", entity);*/
            }
            List<TreeNode> resultList = partyRelationService.getPartyRelationTree(AuPartyRelationType.ADMINISTRATIVE.getId() + "", false);
            model.addAttribute("data", JSON.toJSON(resultList));
        }
        return "";
    }


    @RequestMapping(value = "/admin/department/deletes.jspx")
    public String delete(HttpServletRequest request, HttpServletResponse response, ModelMap model, String[] ids) throws Exception {
        //departmentService.delete(ids);
        return "";
    }

    @RequestMapping(value = "/admin/department/update.jspx")
    public String update(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") AuDepartment entity) {
        departmentService.update(entity);  //更新单条记录
        if ("0" .equals(request.getParameter("cmd"))) {
            //返回组织关系管理页面
            return "redirect:/admin/relation/manage.jspx";
        } else {
            //返回公司管理列表页面
            return "";
        }
    }

    @RequestMapping(value = "/admin/department/index.jspx")
    public String queryAll(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("pager") Pager<AuDepartment> pager) throws Exception {
       // pager = departmentService.findByPager(pager);
        return "";
    }

    @RequestMapping(value = "/admin/department/view.jspx")
    public String detail(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
       /* entity = departmentService.get(request.getParameter("ids"));
        List<TreeNode> resultList = partyRelationService.getPartyDetailRelationTree(entity.getId(), "1099100400000000001");
        model.addAttribute("data", JSON.toJSON(resultList));
        model.addAttribute("rid", request.getParameter("rid"));
        model.addAttribute("entity", entity);*/
        return "";
    }
}
