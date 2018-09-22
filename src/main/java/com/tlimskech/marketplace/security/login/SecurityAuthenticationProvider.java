package com.tlimskech.marketplace.security.login;

import com.tlimskech.marketplace.auth.user.User;
import com.tlimskech.marketplace.auth.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author Goodluck
 */
@Component
public class SecurityAuthenticationProvider implements AuthenticationProvider {
    public static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();
    private final UserService userService;

    @Autowired
    public SecurityAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Authentication Failed. Username or Password not valid."));

        if (!ENCODER.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }


        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(user.getRole().getDescription());
        grantedAuthorities.forEach(g -> System.out.println(g.getAuthority()));

        UserContext userContext = UserContext.create(user.getEmail(), grantedAuthorities);
        return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(clazz));
    }
}
