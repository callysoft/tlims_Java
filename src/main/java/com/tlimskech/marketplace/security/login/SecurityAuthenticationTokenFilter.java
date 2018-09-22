package com.tlimskech.marketplace.security.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tlimskech.marketplace.exception.AuthMethodNotSupportedException;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Goodluck
 */
public class SecurityAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    private final SecurityAuthenticationSuccessHandler successHandler;
    private final SecurityAuthenticationFailureHandler failureHandler;
    private final ObjectMapper objectMapper;

    public SecurityAuthenticationTokenFilter(String defaultProcessUrl,
                                             SecurityAuthenticationSuccessHandler successHandler, SecurityAuthenticationFailureHandler failureHandler,
                                             ObjectMapper mapper) {
        super(defaultProcessUrl);
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        this.objectMapper = mapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        System.out.println("Request Method " + request.getMethod());
        if (!HttpMethod.POST.name().equals(request.getMethod())) {// || // !WebUtil.isAjax(request)
            if (logger.isDebugEnabled()) {
                logger.debug("Authentication method not supported. Request method: " + request.getMethod());
            }
            throw new AuthMethodNotSupportedException("Authentication method not supported");
        }
        LoginRequest loginRequest = objectMapper.readValue(request.getReader(), LoginRequest.class);
        if (StringUtils.isEmpty(loginRequest.getUsername()) || StringUtils.isEmpty(loginRequest.getPassword())) {
            throw new AuthenticationServiceException("Username or Password not provided");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword());
        return this.getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
