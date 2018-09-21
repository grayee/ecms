package com.qslion.custom.controller;


import com.qslion.core.service.ConnectionRuleService;
import com.qslion.core.service.PartyRelationService;
import com.qslion.core.service.PartyService;
import com.qslion.custom.entity.AuCompany;
import com.qslion.custom.service.AuCompanyService;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.bean.RestResult;
import com.qslion.framework.controller.BaseController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公司控制类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@RestController
@RequestMapping(value = "/org/company")
public class CompanyController extends BaseController<AuCompany, String> {

    @Autowired
    private AuCompanyService companyService;
    @Autowired
    private PartyService partyService;
    @Autowired
    private PartyRelationService partyRelationService;
    @Autowired
    private ConnectionRuleService connectionRuleService;

    /**
     * 保存
     *
     * @param request 请求对象
     * @param response 响应对象
     * @param auCompany 公司
     * @return RestResult
     */
    @PostMapping
    public ResponseEntity<RestResult> save(HttpServletRequest request, HttpServletResponse response,
        @Valid @RequestBody AuCompany auCompany) {
        String parentCode = "";
        boolean isRoot = Boolean.valueOf(request.getParameter("isRoot"));
        if (isRoot) {
            companyService.insertRoot(auCompany);
        } else {
            companyService.insert(auCompany, parentCode);
        }

        return ResponseEntity.ok(RestResult.success());
    }


    /**
     * 从页面的表单获取团体关系id，并删除团体关系及相关的权限记录
     */
    @DeleteMapping
    public String delete(HttpServletRequest request, HttpServletResponse response, ModelMap model, String[] ids)
        throws Exception {
        // companyService.delete(ids);
        return "";
    }

    /**
     * 从页面表单获取信息注入vo，并修改单条记录，同时调用接口更新相应的团体、团体关系记录
     */
    @PutMapping
    public String update(HttpServletRequest request, HttpServletResponse response, @RequestBody AuCompany auCompany) {
        companyService.update(auCompany);  //更新单条记录
        if ("0".equals(request.getParameter("cmd"))) {
            //返回组织关系管理页面
            return "redirect:/admin/relation/manage.jspx";
        } else {
            //返回公司管理列表页面
            return "";
        }
    }

    /**
     * 查询全部记录，分页显示，支持页面上触发的后台排序
     */
    @GetMapping(value = "/list")
    public String queryAll(HttpServletRequest request, HttpServletResponse response,
        @RequestBody Pager<AuCompany> pager) {
        //pager=companyService.findByPager(pager); //定义结果集
        return "";
    }

    /**
     * 详细信息
     */
    @GetMapping(value = "/detail")
    public String detail(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        // entity =companyService.get(request.getParameter("ids"));
       /* List<TreeNode> resultList=partyRelationService.getPartyDetailRelationTree(entity.getId(),"1099100400000000001");
    model.addAttribute("data", JSON.toJSONString(resultList));
        model.addAttribute("rid", request.getParameter("rid"));
        model.addAttribute("entity", entity);*/
        return "";
    }

}
