package com.tlimskech.marketplace.security.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tlimskech.marketplace.auth.user.User;
import com.tlimskech.marketplace.auth.user.UserService;
import com.tlimskech.marketplace.exception.ApplicationException;
import com.tlimskech.marketplace.security.utils.AppTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SecurityAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper mapper;
    private final AppTokenUtil appTokenUtil;
    private final UserService userService;

    @Autowired
    public SecurityAuthenticationSuccessHandler(final ObjectMapper mapper, AppTokenUtil appTokenUtil, UserService userService) {
        this.mapper = mapper;
        this.appTokenUtil = appTokenUtil;
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        UserContext userContext = (UserContext) authentication.getPrincipal();

        String accessToken = appTokenUtil.generateToken(userContext);
        String username = appTokenUtil.getUsernameFromToken(accessToken);
        List<String> authorities = appTokenUtil.getAuthoritiesFromToken(accessToken);
        Set<String> authoritiesSet = new HashSet<>(authorities);
//        System.out.println("Roles: "+list);

        User user = userService.findByUsername(username).orElseThrow(() -> new ApplicationException("User not found"));

        String authority = String.join(",", authoritiesSet);
        // String refreshToken = JwtTokenUtil.refreshToken(userContext);
        // System.out.println(authority);

        Map<String, String> tokenMap =  User.generateAuthToken(accessToken, authority, user);

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getWriter(), tokenMap);
        clearAuthenticationAttributes(request);
    }

    /**
     * Removes temporary authentication-related data which may have been stored
     * in the session during the authentication process..
     */
    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return;
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
