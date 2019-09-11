package com.qslion.security.controller;

import com.qslion.core.entity.AuLoginLog;
import com.qslion.core.entity.AuLoginLog.LoginType;
import com.qslion.core.entity.AuRole;
import com.qslion.core.entity.AuUser;
import com.qslion.core.service.AuUserService;
import com.qslion.core.service.LoginLogService;
import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.bean.SystemConfig;
import com.qslion.framework.controller.BaseController;
import com.qslion.framework.enums.ResultCode;
import com.qslion.framework.exception.BusinessException;
import com.qslion.framework.util.IpUtil;
import com.qslion.framework.util.SystemConfigUtil;
import com.qslion.framework.util.ValidatorUtils.AddGroup;
import io.swagger.annotations.Api;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties.Provider;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2018/11/16.
 */
@Api(value = "Oauth2登陆登出controller", description = "Oauth2登陆登出controller", tags = {"登陆登出控制器"})
@ResponseResult
@RestController
public class Oauth2Controller extends BaseController {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;
    @Autowired
    ClientDetailsService clientDetailsService;
    @Autowired
    private OAuth2ClientContext oauth2ClientContext;
    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;
    @Autowired
    private AuUserService auUserService;
    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private TokenStore tokenStore;

    private static final String ECMS_PROVIDER = "ecms-oauth-provider";

    @PostMapping(value = "/login/oauth")
    public OAuth2AccessToken login(HttpServletRequest request, @RequestBody @Validated LoginDTO loginDTO,
                                   @RequestHeader(HttpHeaders.AUTHORIZATION) String header, HttpServletResponse response) {
        String clientId = StringUtils.EMPTY;
        String clientSecret = StringUtils.EMPTY;
        //这里需要注意为 Basic 而非 Bearer
        if (header != null && header.startsWith("Basic ")) {
            try {
                String[] tokens = this.extractAndDecodeHeader(header);
                assert tokens.length == 2;
                clientId = tokens[0];
                clientSecret = tokens[1];
            } catch (IOException e) {
                logger.error(e);
            }
        }

        OAuth2AccessToken oAuth2AccessToken = null;
        AuUser admin = auUserService.findUserByUsername(loginDTO.getUsername());
        if (admin != null) {
            //登录失败锁定次数，默认5次失败后将锁定帐号5分钟
            int loginFailureLockCount = getSystemConfig().getLoginFailureLockCount();
            //系统记录到登录失败的次数
            int loginFailureCount = admin.getLoginFailureCount();
            //锁定开关,默认打开
            boolean isLoginFailureLock = getSystemConfig().getIsLoginFailureLock();
            int lastFailureCount = loginFailureLockCount - loginFailureCount;
            if (isLoginFailureLock && lastFailureCount <= 3) {
                if (lastFailureCount == 0) {
                    admin.setAccountNonLocked(false);
                    admin.setLockedDate(DateTime.now().toDate());
                    auUserService.update(admin);
                    throw new BusinessException(ResultCode.USER_ACCOUNT_LOCKED);
                }
                admin.setLoginFailureCount(admin.getLoginFailureCount() + 1);
                auUserService.update(admin);
                throw new BusinessException(ResultCode.USER_ACCOUNT_FAILURE_LOCK,
                        String.valueOf(loginFailureLockCount));
            } else if (!admin.isEnabled()) {
                throw new BusinessException(ResultCode.USER_ACCOUNT_FORBIDDEN);
            } else if (!admin.isAccountNonExpired()) {
                int lockDuration = Minutes.minutesBetween(DateTime.now(), new DateTime(admin.getLockedDate()))
                        .getMinutes();
                if (lockDuration > getSystemConfig().getLoginFailureLockTime()) {
                    admin.setAccountNonLocked(true);
                    auUserService.update(admin);
                } else {
                    throw new BusinessException(ResultCode.USER_ACCOUNT_FORBIDDEN);
                }
            }

            try {
                ResourceOwnerPasswordResourceDetails resources = getResourceOwnerPasswordResourceDetails(loginDTO, clientId, clientSecret);
                OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resources, oauth2ClientContext);
                oAuth2RestTemplate.setAccessTokenProvider(new ResourceOwnerPasswordAccessTokenProvider());
                oAuth2AccessToken = oAuth2RestTemplate.getAccessToken();
                response.addCookie(new Cookie("access_token", oAuth2AccessToken.getValue()));
                response.addCookie(new Cookie("refresh_token", oAuth2AccessToken.getRefreshToken().getValue()));
            } catch (ClientRegistrationException e) {
                throw new BusinessException(ResultCode.PERMISSION_NO_ACCESS);
            } catch (UserRedirectRequiredException e) {
                throw new BusinessException(ResultCode.PARAMETER_IS_INVALID);
            } catch (OAuth2AccessDeniedException e) {
                throw new BusinessException(ResultCode.USER_LOGIN_ERROR);
            }
        } else {
            throw new BusinessException(ResultCode.USER_NOT_EXIST);
        }

        String finalClientId = clientId;
        CompletableFuture.runAsync(() -> {
            AuLoginLog loginLog = new AuLoginLog();
            loginLog.setLoginId(finalClientId);
            loginLog.setUsername(loginDTO.getUsername());
            loginLog.setLoginIp(IpUtil.getRealIp(request));
            loginLog.setLoginType(LoginType.LOGIN);
            loginLogService.addLoginLog(loginLog);
        });
        return oAuth2AccessToken;
    }

    private ResourceOwnerPasswordResourceDetails getResourceOwnerPasswordResourceDetails(LoginDTO loginDTO,
                                                                                         String clientId, String clientSecret) {
        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setUsername(loginDTO.getUsername());
        resourceDetails.setPassword(loginDTO.getPassword());
        Provider provider = oAuth2ClientProperties.getProvider().get(ECMS_PROVIDER);
        resourceDetails.setAccessTokenUri(provider.getTokenUri());
        resourceDetails.setClientId(clientId);
        resourceDetails.setClientSecret(clientSecret);
        return resourceDetails;
    }

    @PostMapping(value = "/logout/oauth")
    public boolean logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AccessToken accessToken = tokenStore.getAccessToken((OAuth2Authentication) auth);
        tokenStore.removeAccessToken(accessToken);
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return true;
    }

    @GetMapping("/userinfo")
    public UserInfo userinfo(@AuthenticationPrincipal AuUser user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(String.valueOf(user.getId()));
        userInfo.setName(user.getUsername());
        userInfo.setRoles(user.getRoles().stream().map(AuRole::getValue).collect(Collectors.toList()));
        userInfo.setAvatar(user.getAvatar());
        userInfo.setInfo(user.getBirthday() + "" + user.getEmail());
        return userInfo;
    }


    private String[] extractAndDecodeHeader(String header) throws IOException {
        byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8.name());

        byte[] decoded;
        try {
            decoded = Base64.getDecoder().decode(base64Token);
        } catch (IllegalArgumentException var7) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }

        String token = new String(decoded, StandardCharsets.UTF_8.name());
        int delim = token.indexOf(":");
        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        } else {
            return new String[]{token.substring(0, delim), token.substring(delim + 1)};
        }
    }

    private static class LoginDTO {

        @NotBlank(message = "用户名不能为空")
        private String username;
        @NotBlank(message = "密码不能为空", groups = {AddGroup.class})
        @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_-]{5,19}$", groups = AddGroup.class, message = "{custom.pwd.invalid}")
        private String password;
        private Boolean isRobot = false;

        public String getUsername() {
            return username;
        }

        public LoginDTO setUsername(String username) {
            this.username = username;
            return this;
        }

        public String getPassword() {
            return password;
        }

        public LoginDTO setPassword(String password) {
            this.password = password;
            return this;
        }

        public boolean isRobot() {
            return isRobot;
        }

        public LoginDTO setRobot(boolean robot) {
            isRobot = robot;
            return this;
        }
    }

    private static class UserInfo {

        private String id;
        private String name;
        private String avatar;
        private List<String> roles;
        private String info;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    public SystemConfig getSystemConfig() {
        return SystemConfigUtil.getSystemConfig();
    }

    public static void main(String[] args) {
        System.out.println(Base64.getEncoder().encodeToString("client_id_1234567890:client_secret_1234567890".getBytes()));
    }
}