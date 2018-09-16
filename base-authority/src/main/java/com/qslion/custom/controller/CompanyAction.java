package com.qslion.custom.controller;


import com.alibaba.fastjson.JSON;
import com.qslion.core.entity.AuPartyRelationType;
import com.qslion.core.service.ConnectionRuleService;
import com.qslion.core.service.PartyRelationService;
import com.qslion.core.service.PartyService;
import com.qslion.core.util.TreeNode;
import com.qslion.custom.entity.AuCompany;
import com.qslion.custom.service.AuCompanyService;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.controller.BaseController;
import com.qslion.framework.util.ParameterUtil;
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
 * 功能、用途、现存BUG:
 * @author zhangrg
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping(value = "/organization/company")
public class CompanyAction  extends BaseController<AuCompany,String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3904609616359954970L;
	
	@Autowired
	public AuCompanyService companyService;

	@Autowired
	public PartyService partyService;
    @Autowired
    public PartyRelationService partyRelationService;

    @Autowired
    public ConnectionRuleService connectionRuleService;
    


    //组织机构添加
	@RequestMapping(value = "/admin/company/save.jspx")
    public String insert(HttpServletRequest request,HttpServletResponse response,ModelMap model,@ModelAttribute("entity") AuCompany entity){
		String parentCode = ParameterUtil.getParameter(request, "parentRelId");
		boolean isRoot= Boolean.valueOf(request.getParameter("isRoot"));
	    if(isRoot){
	    	companyService.insertRoot(entity);
	    	return "redirect:/admin/relation/manage.jspx";
	    }else{
	    	companyService.insert(entity, parentCode);
	    	if("0".equals(request.getParameter("cmd"))){
	           return "redirect:/admin/relation/manage.jspx";
	    	}
	    }
        return forward("index",true);
    }
	//编辑，新增
	@RequestMapping(value="/admin/company/input.jspx")
	public String input(HttpServletRequest request,HttpServletResponse response,ModelMap model) {
		//创建根节点
		String isAddRoot=request.getParameter("isAddRoot");
		if("1".equals(isAddRoot)){
			model.addAttribute("isRoot", true);
		}else{
			String id=request.getParameter("ids");
			String parentRelId=request.getParameter("parentRelId");
			//组织机构新增,公司新增
			if(StringUtils.isNotEmpty(parentRelId)){
				model.addAttribute("parentRelId", parentRelId);
			}else{
				//更新
				if(StringUtils.isNotEmpty(id)){
				/*	AuCompany entity=this.companyService.get(id);
					String parentCode=partyRelationService.getRelationByPartyId(entity.getId(), AuPartyRelationType.ADMINISTRATIVE.getId()+"").getParentId();
					if(parentCode!=null&&!"".equals(parentCode)){
						model.addAttribute("relation", partyRelationService.get(parentCode));
					}*/
		          /*  model.addAttribute("entity", entity);*/
				}
				List<TreeNode> resultList=partyRelationService.getPartyRelationTree(AuPartyRelationType.ADMINISTRATIVE.getId()+"", false);
				model.addAttribute("data", JSON.toJSON(resultList));
			}
		}
		return forward("input",false);
	}
    

    /**
     * 从页面的表单获取团体关系id，并删除团体关系及相关的权限记录
     */
	@RequestMapping(value = "/admin/company/deletes.jspx")
    public String delete(HttpServletRequest request,HttpServletResponse response,ModelMap model,String []ids) throws Exception {
       // companyService.delete(ids);
        return forward("index",true);
    }

    /**
     * 从页面表单获取信息注入vo，并修改单条记录，同时调用接口更新相应的团体、团体关系记录
     */
	@RequestMapping(value = "/admin/company/update.jspx")
    public String update(HttpServletRequest request,HttpServletResponse response,ModelMap model,@ModelAttribute("entity") AuCompany entity){
        companyService.update(entity);  //更新单条记录
        if("0".equals(request.getParameter("cmd"))){
            //返回组织关系管理页面
            return "redirect:/admin/relation/manage.jspx";
        }else {
        	//返回公司管理列表页面
        	return forward("index",true);
        }
    }

    /**
     * 查询全部记录，分页显示，支持页面上触发的后台排序
     */
	@RequestMapping(value = "/admin/company/index.jspx")
    public String queryAll(HttpServletRequest request,HttpServletResponse response,ModelMap model,@ModelAttribute("pager") Pager<AuCompany> pager){
        //pager=companyService.findByPager(pager); //定义结果集
        return forward("list",false);
    }

    /**
     * 详细信息
     */
	@RequestMapping(value = "/admin/company/view.jspx")
    public String detail(HttpServletRequest request,HttpServletResponse response,ModelMap model) throws Exception {
       // entity =companyService.get(request.getParameter("ids"));
       /* List<TreeNode> resultList=partyRelationService.getPartyDetailRelationTree(entity.getId(),"1099100400000000001");
		model.addAttribute("data", JSON.toJSONString(resultList));
        model.addAttribute("rid", request.getParameter("rid"));
        model.addAttribute("entity", entity);*/
        return forward("view",false);
    }

	public String forward(String viewName,boolean isRedirect){
		String baseUrl="";
		if(isRedirect){
			 baseUrl="redirect:/admin/company/";
			 return baseUrl+viewName+".jspx";
		}else{
			 baseUrl="authority/company/";
			 return baseUrl+viewName;
		}
	}
 
}
