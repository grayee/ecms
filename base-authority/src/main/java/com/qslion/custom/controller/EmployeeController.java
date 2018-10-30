/**
 *
 */
package com.qslion.custom.controller;


import com.qslion.core.service.ConnectionRuleService;
import com.qslion.core.service.PartyRelationService;
import com.qslion.custom.entity.AuEmployee;
import com.qslion.custom.service.AuEmployeeService;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.controller.BaseController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 雇员控制类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@ResponseResult
@RestController
@RequestMapping(value = "/org/employee")
public class EmployeeController extends BaseController<AuEmployee, Long> {


    @Autowired
    public AuEmployeeService employeeService;
    @Autowired
    public PartyRelationService partyRelationService;
    @Autowired
    public ConnectionRuleService connectionRuleService;



    /**
     * 从页面表单获取信息注入vo，并插入单条记录，同时添加团体、团体关系（如果parentRelId为空则不添加团体关系）
     */
    @RequestMapping(value = "/admin/employee/save.jspx")
    public String insert(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") AuEmployee entity) {
        String parentCode ="";
        employeeService.insert(entity, parentCode);
        if ("0".equals(request.getParameter("cmd"))) {
            return "redirect:/admin/relation/manage.jspx";
        }
        return forward("index", true);
    }

    //编辑，新增
    @RequestMapping(value = "/admin/employee/input.jspx")
    public String input(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String id = request.getParameter("ids");
        String parentRelId = request.getParameter("parentRelId");
        //组织机构新增,公司新增
        if (!StringUtils.isNotEmpty(parentRelId)) {
            model.addAttribute("parentRelId", parentRelId);
        } else {
            //更新
          /*  if (!StringUtils.isNotEmpty(id)) {
                AuEmployee entity = employeeService.get(id);
                String parentCode = partyRelationService.getRelationByPartyId(entity.getId(), AuPartyRelationType.ADMINISTRATIVE.getId() + "") == null ? "" : partyRelationService.getRelationByPartyId(entity.getId(), AuPartyRelationType.ADMINISTRATIVE.getId() + "").getParentId();
                if (parentCode != null && !"".equals(parentCode)) {
                    model.addAttribute("relation", partyRelationService.get(parentCode));
                }
                model.addAttribute("entity", entity);
            }
            List<TreeNode> resultList = partyRelationService.getPartyRelationTree(AuPartyRelationType.ADMINISTRATIVE.getId() + "", false);
            model.addAttribute("data", JSON.toJSON(resultList));*/
        }
        return forward("input", false);
    }

    /**
     * 从页面的表单获取团体关系id，并删除团体关系及相关的权限记录
     */
    //删除
    @RequestMapping(value = "/admin/employee/deletes.jspx")
    public String delete(HttpServletRequest request, HttpServletResponse response, ModelMap model, String[] ids) throws Exception {
        //employeeService.delete(ids);
        return forward("index", true);
    }


    /**
     * 从页面表单获取信息注入vo，并修改单条记录，同时调用接口更新相应的团体、团体关系记录
     */
    @RequestMapping(value = "/admin/employee/update.jspx")
    public String update(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        //employeeService.update(entity);  //更新单条记录

        String parentRelId = request.getParameter("parentRelId");
        if (parentRelId != null && !"".equals(parentRelId) && !"null".equals(parentRelId)) {
            model.addAttribute("parent_code", "GlobalConstants.getRelaType_comp()");
            return "FORWARD_QUERY_TREE_KEY";//返回组织关系管理页面
        } else {
            return forward("index", true);//返回人员管理列表页面
        }
    }

    @RequestMapping(value = "/admin/employee/index.jspx")
    public String queryAll(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("pager") Pager<AuEmployee> pager) {
        //pager = employeeService.findByPager(pager);
        return forward("list", false);
    }

    /**
     * 详细信息
     */
    @RequestMapping(value = "/admin/employee/view.jspx")
    public String detail(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
      /*  entity = employeeService.get(request.getParameter("ids"));
        List<TreeNode> resultList = partyRelationService.getPartyDetailRelationTree(entity.getId(), "1099100400000000001");
        model.addAttribute("data", JSON.toJSONString(resultList));
        model.addAttribute("rid", request.getParameter("rid"));
        model.addAttribute("entity", entity);*/
        return forward("view", false);
    }

    public String forward(String viewName, boolean isRedirect) {
        String baseUrl = "";
        if (isRedirect) {
            baseUrl = "redirect:/admin/employee/";
            return baseUrl + viewName + ".jspx";
        } else {
            baseUrl = "authority/employee/";
            return baseUrl + viewName;
        }
    }

}
