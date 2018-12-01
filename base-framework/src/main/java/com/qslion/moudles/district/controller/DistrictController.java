/**
 *
 */
package com.qslion.moudles.district.controller;

import com.qslion.framework.bean.Pager;
import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.bean.TreeNode;
import com.qslion.framework.controller.BaseController;
import com.qslion.moudles.district.entity.CommonDistrict;
import com.qslion.moudles.district.service.DistrictService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller 区域
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@ResponseResult
@RestController
@RequestMapping(value = "/district")
public class DistrictController extends BaseController<CommonDistrict> {

    @Autowired
    private DistrictService districtService;

    //列表
    @RequestMapping(value = "/admin/district/index.jspx")
    public String list(HttpServletRequest request, HttpServletResponse response, CommonDistrict entity,
        @ModelAttribute("pager") Pager<CommonDistrict> pager) {
        entity = new CommonDistrict();
        entity.setUpid(Long.valueOf(request.getParameter("id")));
        /*pager.setProperty("upid");
        pager.setKeyword(request.getParameter("id"));
        pager = this.service.findByPager(pager);
        model.addAttribute("pager", pager);*/
        return "list";
    }

    //增加
    @RequestMapping(value = "/admin/district/save.jspx")
    public String insert(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") CommonDistrict entity) {
        this.districtService.save(entity);
        logger.info("--------------------save!!!!!------------------------");
        return "index";
    }

    //删除
    @RequestMapping(value = "/admin/district/deletes.jspx")
    public String deletes(HttpServletRequest request, HttpServletResponse response, ModelMap model, String[] ids) {
        //districtService.delete(ids);
        logger.info("--------------------deletes!!!!!------------------------");
        return "index";
    }

    //更新
    @RequestMapping(value = "/admin/district/update.jspx")
    public String update(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("entity") CommonDistrict entity) {
        districtService.update(entity);
        return "index";
    }

    //编辑，新增
    @RequestMapping(value = "/admin/district/input.jspx")
    public String input(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        Long id = Long.valueOf(request.getParameter("ids"));

        CommonDistrict entity = this.districtService.findById(id);
        model.addAttribute("entity", entity);

        return "input";
    }

    //查看，明细
    @RequestMapping(value = "/admin/district/view.jspx")
    public String detail(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        Long id = Long.valueOf(request.getParameter("ids"));

        CommonDistrict entity = this.districtService.findById(id);
        model.addAttribute("entity", entity);

        return "view";
    }

    @RequestMapping(value = "/admin/district/ajaxupdatestatus.jspx")
    public void ajaxUpdateStatus(HttpServletRequest request, HttpServletResponse response, ModelMap model,
        @ModelAttribute("entity") CommonDistrict entity) {
        boolean flag = false;//this.partyTypeService.update(entity);
        //this.ajaxJsonSuccessMessage(flag ? "success" : "faild", response);
    }

    @RequestMapping(value = "/admin/district/manage.jspx")
    public String getManage() {
        return "";
    }

    //区划树
    @RequestMapping(value = "/admin/district/tree.jspx")
    public String getDistrctTree(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String upid = "";
        List<TreeNode> resultList = districtService.getDistrctTreeByUpid(upid);
        //this.ajaxJson(JSONSerializer.toJSON(resultList).toString(), response);
        return "tree";
    }

}
