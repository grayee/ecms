package com.qslion.security.filter;

import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;

/**
 * oauth 自定义异常处理器
 *
 * 认证失败返回401的时候调用此异常处理器，通过异常处理器结合refresh_token进行token的刷新，
 *
 * 刷新成功则通过请求转发（request.getRequestDispatcher）的方式再次访问受拦截资源
 *
 * @author Gray.Z
 * @date 2018/11/16.
 */
public class AuAuthenticationEntryPoint extends OAuth2AuthenticationEntryPoint {

 /*   @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;
    @Autowired
    private BaseOAuth2ProtectedResourceDetails baseOAuth2ProtectedResourceDetails;
    private WebResponseExceptionTranslator exceptionTranslator = new DefaultWebResponseExceptionTranslator();
    @Autowired
    RestTemplate restTemplate;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) {
        try {
            //解析异常，如果是401则处理
            ResponseEntity<?> result = exceptionTranslator.translate(authException);
            if (result.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
                formData.add("client_id", oAuth2ClientProperties.getClientId());
                formData.add("client_secret", oAuth2ClientProperties.getClientSecret());
                formData.add("grant_type", "refresh_token");
                Cookie[] cookie = request.getCookies();
                for (Cookie coo : cookie) {
                    if ("refresh_token".equals(coo.getName())) {
                        formData.add("refresh_token", coo.getValue());
                    }
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                Map map = restTemplate.exchange(baseOAuth2ProtectedResourceDetails.getAccessTokenUri(), HttpMethod.POST,
                    new HttpEntity<MultiValueMap<String, String>>(formData, headers), Map.class).getBody();
                //如果刷新异常,则坐进一步处理
                if (map.get("error") != null) {
                    // 返回指定格式的错误信息
                    response.setStatus(401);
                    response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
                    response.getWriter().print("{\"code\":1,\"message\":\"" + map.get("error_description") + "\"}");
                    response.getWriter().flush();
                    //如果是网页,跳转到登陆页面
                    //response.sendRedirect("login");
                } else {
                    //如果刷新成功则存储cookie并且跳转到原来需要访问的页面
                    for (Object key : map.keySet()) {
                        response.addCookie(new Cookie(key.toString(), map.get(key).toString()));
                    }
                    request.getRequestDispatcher(request.getRequestURI()).forward(request, response);
                }
            } else {
                //如果不是401异常，则以默认的方法继续处理其他异常
                super.commence(request, response, authException);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
