/**
 *
 */
package com.qslion.custom.controller;


import com.alibaba.fastjson.JSON;
import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.core.service.ConnectionRuleService;
import com.qslion.core.service.PartyRelationService;
import com.qslion.core.util.TreeNode;
import com.qslion.custom.entity.AuPosition;
import com.qslion.custom.service.AuPositionService;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.controller.BaseController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目名称：authority 类名称：PositionAction 类描述： 创建人：Administrator 创建时间：2011-8-16 下午05:22:19
 * 修改人：Administrator 修改时间：2011-8-16 下午05:22:19 修改备注：
 */
@RestController
@RequestMapping(value = "/organization/position")
public class PositionAction extends BaseController<AuPosition, String> {

    /**
     *
     */
    private static final long serialVersionUID = -6804522170136946987L;
    @Autowired
    public AuPositionService positionService;
    @Autowired
    public PartyRelationService partyRelationService;
    @Autowired
    public ConnectionRuleService connectionRuleService;


    /**
     * 从页面表单获取信息注入vo，并插入单条记录，同时添加团体、团体关系（如果parentRelId为空则不添加团体关系）
     */
    @RequestMapping(value = "/admin/position/save.jspx")
    public String insert(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") AuPosition entity) {
        String parentCode = "";
        positionService.insert(entity, parentCode);
        if ("0" .equals(request.getParameter("cmd"))) {
            return "redirect:/admin/relation/manage.jspx";
        }
        return forward("index", true);
    }

    //编辑，新增
    @RequestMapping(value = "/admin/position/input.jspx")
    public String input(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String id = request.getParameter("ids");
        String parentRelId = request.getParameter("parentRelId");
        //组织机构新增,公司新增
        if (!StringUtils.isNotEmpty(parentRelId)) {
            model.addAttribute("parentRelId", parentRelId);
        } else {
            //更新
            if (!StringUtils.isNotEmpty(id)) {
             /*   AuPosition entity = positionService.get(id);
                String parentCode = partyRelationService.getRelationByPartyId(entity.getId(), AuPartyRelationType.ADMINISTRATIVE.getId() + "") == null ? "" : partyRelationService.getRelationByPartyId(entity.getId(), AuPartyRelationType.ADMINISTRATIVE.getId() + "").getParentId();
                if (parentCode != null && !"" .equals(parentCode)) {
                    model.addAttribute("relation", partyRelationService.get(parentCode));
                }
                model.addAttribute("entity", entity);*/
            }
            List<TreeNode> resultList = partyRelationService.getPartyRelationTree(AuPartyRelationType.ADMINISTRATIVE.getId() + "", false);
            model.addAttribute("data", JSON.toJSON(resultList));
        }
        return forward("input", false);
    }

    /**
     * 从页面的表单获取团体关系id，并删除团体关系及相关的权限记录
     */
    @RequestMapping(value = "/admin/position/deletes.jspx")
    public String delete(HttpServletRequest request, HttpServletResponse response, ModelMap model, String[] ids) throws Exception {
        //positionService.delete(ids);
        return forward("index", true);
    }


    /**
     * 从页面表单获取信息注入vo，并修改单条记录，同时调用接口更新相应的团体、团体关系记录
     */
    @RequestMapping(value = "/admin/position/update.jspx")
    public String update(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        //positionService.update(entity);
        if ("0" .equals(request.getParameter("cmd"))) {
            //返回组织关系管理页面
            return "redirect:/admin/relation/manage.jspx";
        } else {
            //返回公司管理列表页面
            return forward("index", true);
        }
    }

    /**
     * 查询全部记录，分页显示，支持页面上触发的后台排序
     */
    @RequestMapping(value = "/admin/position/index.jspx")
    public String queryAll(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("pager") Pager<AuPosition> pager) throws Exception {
        //pager = positionService.findByPager(pager);
        return forward("list", false);
    }

    /**
     * 详细信息
     */
    @RequestMapping(value = "/admin/position/view.jspx")
    public String detail(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        //entity = positionService.get(request.getParameter("ids"));
       // List<TreeNode> resultList = partyRelationService.getPartyDetailRelationTree(entity.getId(), AuPartyRelationType.ADMINISTRATIVE.getId() + "");
       /* model.addAttribute("data", JSON.toJSONString(resultList));
        model.addAttribute("rid", request.getParameter("rid"));
        model.addAttribute("entity", entity);*/
        return forward("view", false);
    }

    public String forward(String viewName, boolean isRedirect) {
        String baseUrl = "";
        if (isRedirect) {
            baseUrl = "redirect:/admin/position/";
            return baseUrl + viewName + ".jspx";
        } else {
            baseUrl = "authority/position/";
            return baseUrl + viewName;
        }
    }

}
