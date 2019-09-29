/**
 *
 */
package com.qslion.authority.core.controller.admin;

import com.qslion.authority.core.entity.AuUser;
import com.qslion.framework.controller.BaseController;
import java.util.Properties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

/**
 * 修改备注：
 */
@Controller
public class AuAdminController extends BaseController<AuUser> {

    /**
     *
     */
    private static final long serialVersionUID = 2680675959440559044L;
   /* @Autowired
    public AdminService adminService;
    @Autowired
    public PartyService partyService;
    @Autowired
    public AuMenuService auMenuService;
    @Autowired
    public PartyRelationService partyRelationService;
    @Autowired
    public AuthorizeService authorizeService;*/

    // 后台主页面
    public String main() {
        return "main";
    }

    // 后台中间(显示/隐藏菜单)
    public String middle() {
        return "middle";
    }

    // 后台Header
    public String header() {
        return "header";
    }

    // 后台首页
    public String index() {
        return "index";
    }

    //欢迎页
    public String welcome(ModelMap modelMap) {
        /*WebSite site = SiteUtils.getSite(this.getRequest());
		User user = SiteUtils.getUser(this.getRequest());
		String version = site.getSystemConfig().getSystemVersion();*/
        Properties props = System.getProperties();
        Runtime runtime = Runtime.getRuntime();
        long freeMemoery = runtime.freeMemory();
        long totalMemory = runtime.totalMemory();
        long usedMemory = totalMemory - freeMemoery;
        long maxMemory = runtime.maxMemory();
        long useableMemory = maxMemory - totalMemory + freeMemoery;
        modelMap.addAttribute("props", props);
        modelMap.addAttribute("freeMemoery", freeMemoery);
        modelMap.addAttribute("totalMemory", totalMemory);
        modelMap.addAttribute("usedMemory", usedMemory);
        modelMap.addAttribute("maxMemory", maxMemory);
        modelMap.addAttribute("useableMemory", useableMemory);
		/*this.setAttribute("version", version);
		this.setAttribute("user", user);*/
        return "default";
    }

    // 获取系统配置信息
	/*public SystemConfig getSystemConfig() {
		return SystemConfigUtil.getSystemConfig();
	}*/
}
