package com.qslion.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.exceptions.BadClientCredentialsException;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.util.Assert;

/**
 * 登出控制，清空token
 *
 * @author Gray.Z
 * @date 2018/5/1 15:29.
 */
public class CustomLogoutHandler implements LogoutHandler {

    private static Logger logger = LogManager.getLogger();
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String BEARER_AUTHORIZATION = "Bearer";

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Assert.notNull(tokenStore, "tokenStore must be set");
        String token = request.getHeader(HEADER_AUTHORIZATION);
        Assert.hasText(token, "token must be set");
        if (isJwtBearerToken(token)) {
            token = token.substring(6);
            OAuth2AccessToken existingAccessToken = tokenStore.readAccessToken(token);
            OAuth2RefreshToken refreshToken;
            if (existingAccessToken != null) {
                if (existingAccessToken.getRefreshToken() != null) {
                    logger.info("remove refreshToken!", existingAccessToken.getRefreshToken());
                    refreshToken = existingAccessToken.getRefreshToken();
                    tokenStore.removeRefreshToken(refreshToken);
                }
                logger.info("remove existingAccessToken!", existingAccessToken);
                tokenStore.removeAccessToken(existingAccessToken);
            }
        } else {
            throw new BadClientCredentialsException();
        }

    }


    private boolean isJwtBearerToken(String token) {
        return StringUtils.countMatches(token, ".") == 2 && (token.startsWith(BEARER_AUTHORIZATION) || token
            .startsWith(BEARER_AUTHORIZATION.toLowerCase()));
    }
}
