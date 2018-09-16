package com.qslion.core.controller.au; /**
 * 
 *//*
package com.qslion.authority.core.controller.au;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.jdbc.StringUtils;
import AuResource;
import AuUser;
import AuResourceService;
import TreeNode;
import com.qslion.framework.web.controller.SpringMVCBaseAction;

*//**   
 *    
 * 项目名称：authority   
 * 类名称：AuResourceAction   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2011-8-17 下午02:02:05   
 * 修改人：Administrator   
 * 修改时间：2011-8-17 下午02:02:05   
 * 修改备注：   
 * @version    
 *    
 *//*
@Controller
public class AuResourceAction extends SpringMVCBaseAction<AuResource, String> {

	private static final long serialVersionUID = -5233308038568545680L;
	private static Logger logger=Logger.getLogger(AuResourceAction.class);
	
	public static final String PAGE_PATH = "authority/au/functree/";

	@Autowired
	public AuResourceService resourceService;
	@Autowired
	public void setService(AuResourceService resourceService){
		super.setService(resourceService);
	}
	//菜单
	@RequestMapping(value = "/admin/getFuncMenu.jspx")
	public String manage(){
		return forward("manage");
	}
	//
	@RequestMapping(value = "/admin/functree/default.jspx")
	public String getDefault(){
		return forward("default");
	}
	//查询 菜单节点信息
	@RequestMapping(value = "/admin/functree/getMenuDetail.jspx")
	public String getMenuNodeInfo(HttpServletRequest request,HttpServletResponse response,ModelMap model,String menuId){
		entity=service.get(menuId);
		model.addAttribute("entity",entity);
		return forward("detail");
	}
	//初始化添加子菜单父级节点信息,修改
	@RequestMapping(value = "/admin/functree/input.jspx")
	public String input(HttpServletRequest request,HttpServletResponse response,ModelMap model,String nodeId,AuResource entity){
		AuResource parentNode=service.get(nodeId);
		String cmd=request.getParameter("cmd");
		if(!StringUtils.isNullOrEmpty(cmd)){
			entity.setParentCode(parentNode.getCode());
			entity.setName(parentNode.getName());
			model.addAttribute("entity", entity);
		}else{
			model.addAttribute("entity", parentNode);
		}
		return forward("input");
	}
	//添加
	@RequestMapping(value = "/admin/functree/insert.jspx")
	public String insert(HttpServletRequest request,HttpServletResponse response,ModelMap model,@ModelAttribute("entity") AuResource entity){
		if(this.resourceService.checkUnique(entity)){
			model.addAttribute("error_info", "功能节点名称重复");
			return forward("manage");
		}
		AuResource parent=this.resourceService.getParent(entity);
		if (("1".equals(parent.getIsLeaf())) ) {
		      boolean isUpdate = false;
		      if ("1".equals(parent.getIsLeaf())) {
			        parent.setIsLeaf("0");
			        isUpdate = true;
			      }
			
			 if (isUpdate) {
			        parent.setModifyDate(new Date());
			        resourceService.update(parent);
			  }
		 }
		//插入对应的资源
		String code=String.valueOf(resourceService.getParentChilds(entity).size());
		int stepLen = 3;
		if ((code == null) || (code.length() == 0)){
		      code = "1";
		  }else {
		      code = String.valueOf(Integer.parseInt(code) + 1);
		  }
	    while (code.length() < stepLen) {
	      code = "0" + code;
	    }
		code=entity.getParentCode()+code;
		
		AuResource resVo = new AuResource();
	    resVo.setResourceType("");
	    resVo.setName(entity.getName());
	    resVo.setIsPublic("0");
	    resVo.setIsSystem(false);
	    resVo.setOrderList(0);
	    resVo.setCreateDate(entity.getCreateDate());
	    resVo.setEnableStatus("1");
	    resVo.setAccessType(1);
	    resVo.setValue(entity.getValue());
	    resVo.setFieldName(entity.getKeyword());
	    resVo.setFilterType(entity.getHotKey());
	    resVo.setTableName("");
	    resVo.setHelp(entity.getHelp());
	    
		entity.setTotalCode(code);
		entity.setOrderCode(code);
	    if ((entity.getKeyword() == null) || (entity.getKeyword().trim().equals(""))) {
	    	entity.setKeyword(code);
	    }
	    entity.setTreeLevel((short)(code.length()/ 3));
	    entity.setCode(code.substring(code.length() - 3));
	   // entity.setAuResource(resVo);
	   // resVo.setAuFuncTree(entity);
	    //this.resourceService.insert(resVo);  
	    this.resourceService.insert(entity);
	    return forward("manage");
	}
	//更新
	@RequestMapping(value = "/admin/functree/update.jspx")
	public String update(HttpServletRequest request,HttpServletResponse response,ModelMap model,@ModelAttribute("entity") AuResource entity){
		//级联更新
	   AuResource resVo=this.resourceService.get(entity.getId());
	   resVo.setName(entity.getName());
	   resVo.setValue(entity.getValue());
	   this.resourceService.update(entity);
	   return forward("manage");
	}
	
	@RequestMapping(value = "/admin/functree/delete.jspx")
	public String delete(HttpServletRequest request,HttpServletResponse response,ModelMap model,@ModelAttribute("entity") AuResource entity){
		boolean flag=false;
		//步骤：首先判断该节点是否包含子节点，有的话下需要删除子节点,同时删除对应的资源然后更新父节点的状态
		entity=resourceService.get(entity.getId());
		//存在下级子节点
		if(entity.getIsLeaf().equals("0")){
			List<AuResource> childrens=resourceService.getChildrens(entity);
			String []ids=new String[childrens.size()];
			for(int i=0;i<childrens.size();i++){
				ids[i]=childrens.get(i).getId();
			}
			//删除所有下级子节点以及相应的资源
			resourceService.delete(ids);
			//resourceService.delete(ids);
		}
		resourceService.delete(entity.getId());
			//resourceService.delete(entity.getId());
		//判断父节点是否还 存在子节点
		if(resourceService.getParentChilds(entity).size()<=0){
			AuResource parent=this.resourceService.getParent(entity);
			parent.setIsLeaf("1");
			resourceService.update(parent);
		}	
		return forward("manage");
	}
	
	//动态构建功能菜单
	@RequestMapping(value = "/admin/getFuncManage.jspx")
	public String getFuncMenuTree(HttpServletRequest request,HttpServletResponse response,ModelMap model,String status) throws JSONException{
		logger.info("-------------------------构建系统菜单---------------------------------------");
		AuUser loginUser=(AuUser) request.getSession().getAttribute("loginUser");
		//根据登录用户获取菜单树
		List<TreeNode> resultList= this.resourceService.getFuncMenuTree(loginUser,status,request.getContextPath());
		model.addAttribute("data", JSONUtil.serialize(resultList));
		model.addAttribute("status", status);
		return forward("tree");
	}
	
	public String forward(String viewName){
		String view=PAGE_PATH+viewName;
		return view;
	}

}
*/