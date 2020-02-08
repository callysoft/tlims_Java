package com.tlimskech.marketplace.security;

import com.tlimskech.marketplace.auth.user.User;
import com.tlimskech.marketplace.auth.user.UserService;
import com.tlimskech.marketplace.exception.ApplicationException;
import com.tlimskech.marketplace.security.login.UserContext;
import com.tlimskech.marketplace.security.utils.AppTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppAuthProvider implements AuthenticationProvider {

    private final AppTokenUtil tokenUtil;
    private final UserService userService;

    @Autowired
    public AppAuthProvider(AppTokenUtil tokenUtil, UserService userService) {
        this.tokenUtil = tokenUtil;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getCredentials();
        String userName = tokenUtil.getUsernameFromToken(token);
        List<String> authorities = tokenUtil.getAuthoritiesFromToken(token);

        User user = userService.findByUsername(userName).orElseThrow(() -> new ApplicationException("Invalid user credentials"));

        List<GrantedAuthority> grantedAuthorities = authorities.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        UserContext userContext = UserContext.create(user.getEmail(), grantedAuthorities);
        return new AppAuthenticationToken(userContext, userContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return (AppAuthenticationToken.class.isAssignableFrom(clazz));
    }

}
