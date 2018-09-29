package com.qslion.controller;


import com.qslion.core.entity.AuUser;
import com.qslion.core.service.AuUserService;
import com.qslion.framework.bean.SystemConfig;
import com.qslion.framework.controller.BaseController;
import com.qslion.framework.util.Localize;
import com.qslion.framework.util.SystemConfigUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * FORM登录控制类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@Controller
public class LoginController extends BaseController{

    @Autowired
    private AuUserService auUserService;

    @RequestMapping(value = "/")
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "home";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/home")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/loginSuccess")
    public String loginSuccess() {
        return "home";
    }

    @RequestMapping(value = "/loginFailure")
    public String loginFailure(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        /**
         * see SimpleUrlAuthenticationFailureHandler >>onAuthenticationFailure
         */
        Exception springSecurityLastException = (Exception) request.getSession().getAttribute(
            WebAttributes.AUTHENTICATION_EXCEPTION);

        if (springSecurityLastException != null) {
            if (springSecurityLastException instanceof BadCredentialsException) {
                String loginUsername = (request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION) == null ? "" :
                    (String) request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION)).toLowerCase();
                AuUser admin = auUserService.findUserByUsername(loginUsername);
                if (admin != null) {
                    //登录失败锁定次数，默认5次失败后将锁定帐号5分钟
                    int loginFailureLockCount = getSystemConfig().getLoginFailureLockCount();
                    //系统记录到登录失败的次数
                    int loginFailureCount = 0;//admin.getLoginFailureCount();
                    //锁定开关,默认打开
                    boolean isLoginFailureLock = getSystemConfig().getIsLoginFailureLock();
                    if (isLoginFailureLock && loginFailureLockCount - loginFailureCount <= 3) {
                        model.addAttribute("login_error", Localize.getMessage("login_failure_lock", loginFailureLockCount));
                    } else {
                        model.addAttribute("login_error", Localize.getMessage("login_username_or_password_error"));
                    }
                } else {
                    model.addAttribute("login_error", Localize.getMessage("login_username_or_password_error"));
                }
            } else if (springSecurityLastException instanceof DisabledException) {
                model.addAttribute("login_error", Localize.getMessage("login_account_disabled"));
            } else if (springSecurityLastException instanceof LockedException) {
                model.addAttribute("login_error", Localize.getMessage("login_account_locked"));
            } else if (springSecurityLastException instanceof AccountExpiredException) {
                model.addAttribute("login_error", Localize.getMessage("login_account_expired"));
            } else if (springSecurityLastException instanceof AuthenticationServiceException) {
                model.addAttribute("login_error", springSecurityLastException.getMessage());
            } else {
                springSecurityLastException.printStackTrace();
                model.addAttribute("login_error", Localize.getMessage("login_unknown_error"));
            }
            request.getSession().removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
        logger.info("-----------------------------系统登录账户错误信息结束---------------------------------");
        return "login";
    }

    // 获取系统配置信息
    public SystemConfig getSystemConfig() {
        return SystemConfigUtil.getSystemConfig();
    }
}
