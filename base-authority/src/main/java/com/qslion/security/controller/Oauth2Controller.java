package com.qslion.security.controller;

import com.google.common.collect.Lists;
import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.controller.BaseController;
import com.qslion.framework.util.ValidatorUtils.AddGroup;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties.Provider;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2018/11/16.
 */
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

    @PostMapping(value = "/login/test")
    public ResponseEntity<OAuth2AccessToken> login(HttpServletRequest request,
        @RequestBody @Validated LoginDTO loginDTO, HttpServletResponse response) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String clientId = StringUtils.EMPTY;
        String header = request.getHeader("Authorization");
        //这里需要注意为 Basic 而非 Bearer
        if (header != null && header.startsWith("Basic ")) {
            //Http Basic 验证 base64 clientId:clientSecret
            httpHeaders.set("Authorization", header);

            try {
                String[] tokens = this.extractAndDecodeHeader(header, request);
                assert tokens.length == 2;
                clientId = tokens[0];
            } catch (IOException e) {
                logger.error(e);
            }
        }

        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

        //授权请求信息
        MultiValueMap<String, String> authReqBody = new LinkedMultiValueMap<>();
        authReqBody.put("username", Collections.singletonList(loginDTO.getUsername()));
        authReqBody.put("password", Collections.singletonList(loginDTO.getPassword()));
        authReqBody.put("grant_type", Lists.newArrayList(clientDetails.getAuthorizedGrantTypes()));
        authReqBody.put("scope", Lists.newArrayList(clientDetails.getScope()));
        //HttpEntity
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(authReqBody, httpHeaders);

        Provider provider = oAuth2ClientProperties.getProvider().get("ecms-oauth-provider");

        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resourceDetails, oauth2ClientContext);
        //获取 Token
        ResponseEntity<OAuth2AccessToken> body = oAuth2RestTemplate.exchange(provider.getTokenUri(), HttpMethod.POST, httpEntity,
            OAuth2AccessToken.class);
        OAuth2AccessToken oAuth2AccessToken = body.getBody();
        response.addCookie(new Cookie("access_token", oAuth2AccessToken.getTokenValue()));
        response.addCookie(new Cookie("refresh_token", oAuth2AccessToken.getTokenValue()));
        return body;

    }

    @RequestMapping("/userinfo")
    public String userinfo(Model model, OAuth2AuthenticationToken authentication) {
        // authentication.getAuthorizedClientRegistrationId() returns the
        // registrationId of the Client that was authorized during the Login flow
        OAuth2AuthorizedClient authorizedClient =
            this.authorizedClientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(), authentication.getName());
        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        System.out.println(accessToken.getTokenValue());
        Map userAttributes = Collections.emptyMap();
        String userInfoEndpointUri = authorizedClient.getClientRegistration()
            .getProviderDetails().getUserInfoEndpoint().getUri();
        if (!StringUtils.isEmpty(userInfoEndpointUri)) {
            // userInfoEndpointUri is optional for OIDC Clients
            userAttributes = WebClient.builder()
                .filter(oauth2Credentials(authorizedClient))
                .build().get().uri(userInfoEndpointUri).retrieve().bodyToMono(Map.class).block();
        }
        model.addAttribute("userAttributes", userAttributes);
        return "userinfo";
    }

    private ExchangeFilterFunction oauth2Credentials(OAuth2AuthorizedClient authorizedClient) {
        return ExchangeFilterFunction.ofRequestProcessor(
            clientRequest -> {
                ClientRequest authorizedRequest = ClientRequest.from(clientRequest)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " +
                        authorizedClient.getAccessToken().getTokenValue()).build();
                return Mono.just(authorizedRequest);
            });
    }

    private String[] extractAndDecodeHeader(String header, HttpServletRequest request) throws IOException {
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
        private Boolean isRobot;

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
}