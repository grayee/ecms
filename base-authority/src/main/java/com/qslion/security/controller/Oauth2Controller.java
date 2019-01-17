package com.qslion.security.controller;

import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.controller.BaseController;
import com.qslion.framework.util.ValidatorUtils.AddGroup;
import io.swagger.annotations.Api;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
@Api(value="Oauth2登陆登出controller",description="登陆登出控制类",tags={"登陆登出控制接口"})
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

    private static final String ECMS_PROVIDER = "ecms-oauth-provider";

    @PostMapping(value = "/login/oauth")
    public ResponseEntity<OAuth2AccessToken> login(HttpServletRequest request,@RequestBody @Validated LoginDTO loginDTO,
        @RequestHeader("Authorization") String header, HttpServletResponse response) {
        String clientId = StringUtils.EMPTY;
        //这里需要注意为 Basic 而非 Bearer
        if (header != null && header.startsWith("Basic ")) {
            try {
                String[] tokens = this.extractAndDecodeHeader(header, request);
                assert tokens.length == 2;
                clientId = tokens[0];
            } catch (IOException e) {
                logger.error(e);
            }
        }

        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        ResourceOwnerPasswordResourceDetails resources = getResourceOwnerPasswordResourceDetails(loginDTO, clientDetails);
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resources, oauth2ClientContext);
        oAuth2RestTemplate.setAccessTokenProvider(new ResourceOwnerPasswordAccessTokenProvider());
        OAuth2AccessToken oAuth2AccessToken = oAuth2RestTemplate.getAccessToken();
        response.addCookie(new Cookie("access_token", oAuth2AccessToken.getValue()));
        response.addCookie(new Cookie("refresh_token", oAuth2AccessToken.getRefreshToken().getValue()));
        return ResponseEntity.ok(oAuth2AccessToken);
    }

    private ResourceOwnerPasswordResourceDetails getResourceOwnerPasswordResourceDetails(LoginDTO loginDTO,
        ClientDetails clientDetails ) {
        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setUsername(loginDTO.getUsername());
        resourceDetails.setPassword(loginDTO.getPassword());
        Provider provider = oAuth2ClientProperties.getProvider().get(ECMS_PROVIDER);
        resourceDetails.setAccessTokenUri(provider.getTokenUri());
        resourceDetails.setClientId(clientDetails.getClientId());
        resourceDetails.setClientSecret(clientDetails.getClientSecret());
        return resourceDetails;
    }

    @GetMapping("/userinfo")
    public Map userinfo(Model model, OAuth2AuthenticationToken authentication) {
        // authentication.getAuthorizedClientRegistrationId() returns the
        // registrationId of the Client that was authorized during the Login flow
        OAuth2AuthorizedClient authorizedClient =
            this.authorizedClientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(), authentication.getName());
        System.out.println(authorizedClient.getAccessToken().getTokenValue());
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
        return userAttributes;
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

    public static void main(String[] args) {
        System.out
            .println(Base64.getEncoder().encodeToString("client_id_1234567890:client_secret_1234567890".getBytes()));
    }
}