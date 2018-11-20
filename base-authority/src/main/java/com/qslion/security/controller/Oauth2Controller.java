package com.qslion.security.controller;

import com.qslion.core.entity.AuUser;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@Controller
public class Oauth2Controller {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;

    @Autowired
    OAuth2RestTemplate oAuth2RestTemplate;

    @PostMapping(value = "/login")
    public ResponseEntity<OAuth2AccessToken> login(@RequestBody @Valid AuUser loginDTO, BindingResult bindingResult,
        HttpServletResponse response) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("登录信息格式错误");
        } else {
            //Http Basic 验证
            String clientAndSecret =
                oAuth2ClientProperties.getClientId() + ":" + oAuth2ClientProperties.getClientSecret();
            //这里需要注意为 Basic 而非 Bearer
            clientAndSecret = "Basic " + Base64.getEncoder().encodeToString(clientAndSecret.getBytes());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", clientAndSecret);
            //授权请求信息
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.put("username", Collections.singletonList(loginDTO.getUsername()));
            map.put("password", Collections.singletonList(loginDTO.getPassword()));

            OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails = new ResourceOwnerPasswordResourceDetails();

            map.put("grant_type", Collections.singletonList(oAuth2ProtectedResourceDetails.getGrantType()));
            map.put("scope", oAuth2ProtectedResourceDetails.getScope());
            //HttpEntity
            HttpEntity httpEntity = new HttpEntity(map, httpHeaders);
            //获取 Token
            ResponseEntity<OAuth2AccessToken> body = oAuth2RestTemplate
                .exchange(oAuth2ProtectedResourceDetails.getAccessTokenUri(), HttpMethod.POST, httpEntity,
                    OAuth2AccessToken.class);
            OAuth2AccessToken oAuth2AccessToken = body.getBody();
            response.addCookie(new Cookie("access_token", oAuth2AccessToken.getTokenValue()));
            response.addCookie(new Cookie("refresh_token", oAuth2AccessToken.getTokenValue()));
            return body;
        }
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
}