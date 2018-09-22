package com.tlimskech.marketplace.security.login;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author Goodluck
 */
@Data
public class UserContext {

    private final String email;
    private final List<GrantedAuthority> authorities;

    private UserContext(String email, List<GrantedAuthority> authorities) {
        this.email = email;
        this.authorities = authorities;
    }

    public static UserContext create(String email, List<GrantedAuthority> authorities) {
        if (StringUtils.isEmpty(email))
            throw new IllegalArgumentException("Email is blank: " + email);
        return new UserContext(email, authorities);
    }

}
