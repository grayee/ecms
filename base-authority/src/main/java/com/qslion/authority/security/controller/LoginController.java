package com.qslion.authority.security.controller;


import com.qslion.authority.core.entity.AuLoginLog;
import com.qslion.authority.core.entity.AuUser;
import com.qslion.authority.core.service.AuUserService;
import com.qslion.authority.core.service.LoginLogService;
import com.qslion.framework.bean.*;
import com.qslion.framework.controller.BaseController;
import com.qslion.framework.util.Localize;
import com.qslion.framework.util.SystemConfigUtil;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 登录日志控制类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@Api(value = "登录Controller", description = "登录Controller", tags = {"登录控制器"})
@ResponseResult
@RestController
public class LoginController extends BaseController {

    @Autowired
    private AuUserService auUserService;

    @Autowired
    private LoginLogService loginLogService;

    @RequestMapping(value = "/")
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "home";
    }

    @GetMapping(value = "/login")
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

    @ResponseBody
    @RequestMapping(value = "/api/test")
    public String test() {
        logger.info("==================test=============");
        return "just for api test";
    }

    @RequestMapping(value = "/loginSuccess")
    public String loginSuccess() {
        return "home";
    }

    @RequestMapping(value = "/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

    @RequestMapping(value = "/loginFailure")
    public String loginFailure(HttpServletRequest request, ModelMap model) {
        /**
         * see SimpleUrlAuthenticationFailureHandler >>onAuthenticationFailure
         */
        Exception springSecurityLastException = (Exception) request.getSession().getAttribute(
                WebAttributes.AUTHENTICATION_EXCEPTION);

        if (springSecurityLastException != null) {
            if (springSecurityLastException instanceof BadCredentialsException) {
                String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
                AuUser admin = auUserService.findUserByUsername(loginUsername);
                if (admin != null) {
                    //登录失败锁定次数，默认5次失败后将锁定帐号5分钟
                    int loginFailureLockCount = getSystemConfig().getLoginFailureLockCount();
                    //系统记录到登录失败的次数
                    int loginFailureCount = admin.getLoginFailureCount();
                    //锁定开关,默认打开
                    boolean isLoginFailureLock = getSystemConfig().getIsLoginFailureLock();
                    if (isLoginFailureLock && loginFailureLockCount - loginFailureCount <= 3) {
                        model.addAttribute("login_error",
                                Localize.getMessage("login_failure_lock", loginFailureLockCount));
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
        logger.warn("系统登录失败，账户错误信息:{}", model.get("login_error"));
        return "login";
    }

    @PostMapping(value = "/login/log")
    public Pager<AuLoginLog> list(@RequestBody Pageable pageable) {
        pageable.setOrderDirection(Order.Direction.desc);
        pageable.setOrderProperty("loginTime");
        return loginLogService.findPage(pageable);
    }

    // 获取系统配置信息
    public SystemConfig getSystemConfig() {
        return SystemConfigUtil.getSystemConfig();
    }
}
