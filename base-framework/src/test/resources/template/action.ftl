package ${actionPackageName};

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.commons.lang3.StringUtils;
import ${entityPackageName}.${entityClassName};
import ${servicePackageName}.${entityClassName}Service;
import com.qslion.framework.bean.Pager;

import org.apache.log4j.Logger;
import com.qslion.framework.web.action.SpringMVCBaseAction;

/**
* @descrip: ${descript}
* @copyright: ${copyright}
* @author: ${author}
* @link: ${link}
* @create_date: ${createDate?string("yyyy-MM-dd HH:mm:ss")}
* @update_date: ${modifyDate?string("yyyy-MM-dd HH:mm:ss")}
*
*/

@Controller
public class ${entityClassName}Action extends SpringMVCBaseAction<${entityClassName}, ${idType}> {


private static Logger logger=Logger.getLogger(${entityClassName}Action.class);

@Resource
public ${entityClassName}Service ${entityClassName?uncap_first}Service;

@Resource
public void setService(${entityClassName}Service ${entityClassName?uncap_first}Service){
super.setService(${entityClassName?uncap_first}Service);
}

/**
* 列表
*/
@RequestMapping(value = "${requestPath}/index.jspx")
public String list(HttpServletRequest request,HttpServletResponse response,ModelMap model,@ModelAttribute("pager") Pager<${entityClassName}> pager){
pager=${entityClassName?uncap_first}Service.findByPager(pager);
model.addAttribute("pager", pager);
return forward("list",false);
}

/**
* 增加
*/
@RequestMapping(value = "${requestPath}/save.jspx")
public String insert(HttpServletRequest request,HttpServletResponse response,ModelMap model,@ModelAttribute("entity") ${entityClassName} entity){
${entityClassName?uncap_first}Service.save(entity);
return forward("index",true);
}

/**
* 删除
*/
@RequestMapping(value = "${requestPath}/deletes.jspx")
public String deletes(HttpServletRequest request,HttpServletResponse response,ModelMap model,String []ids){
${entityClassName?uncap_first}Service.delete(ids);
return forward("index",true);
}

/**
* 更新
*/
@RequestMapping(value = "${requestPath}/update.jspx")
public String update(HttpServletRequest request,HttpServletResponse response,ModelMap model,@ModelAttribute("entity") ${entityClassName} entity){
${entityClassName?uncap_first}Service.update(entity);
return forward("index",true);
}

/**
* 编辑，新增
*/
@RequestMapping(value="${requestPath}/input.jspx")
public String input(HttpServletRequest request,HttpServletResponse response,ModelMap model,@ModelAttribute("entity") ${entityClassName} entity){
String id=request.getParameter("ids");
if(StringUtils.isNotEmpty(id)){
entity=${entityClassName?uncap_first}Service.get(id);
}
model.addAttribute("entity", entity);
return forward("input",false);
}

/**
* 查看，明细
*/
@RequestMapping(value="${requestPath}/view.jspx")
public String detail(HttpServletRequest request,HttpServletResponse response,ModelMap model){
String id=request.getParameter("ids");
if(StringUtils.isNotEmpty(id)){
${entityClassName} entity=${entityClassName?uncap_first}Service.get(id);
model.addAttribute("entity", entity);
}
return forward("view",false);
}

public String forward(String viewName,boolean isRedirect){
String baseUrl="";
if(isRedirect){
baseUrl="redirect:${requestPath}/";
return baseUrl+viewName+".jspx";
}else{
baseUrl="${pagePath}";
return baseUrl+viewName;
}
}

}