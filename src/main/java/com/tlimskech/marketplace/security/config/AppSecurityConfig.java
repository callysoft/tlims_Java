package com.tlimskech.marketplace.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tlimskech.marketplace.security.AppAuthProvider;
import com.tlimskech.marketplace.security.AppTokenAuthenticationProcessingFilter;
import com.tlimskech.marketplace.security.SkipPathRequestMatcher;
import com.tlimskech.marketplace.security.login.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";
    public static final String FORM_BASED_LOGIN_ENTRY_POINT = "/auth";
    public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/api/**";
    public static final String TOKEN_REFRESH_ENTRY_POINT = "/auth/token";

    @Autowired
    private SecurityAuthenticationEntryPoint securityAuthenticationEntryPoint;
    @Autowired
    private SecurityAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private SecurityAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private SecurityAuthenticationProvider securityAuthenticationProvider;
    @Autowired
    private AppAuthProvider stockAuthProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ObjectMapper objectMapper;

    private SecurityAuthenticationTokenFilter buildSecurityLoginProcessingFilter() {
        SecurityAuthenticationTokenFilter filter = new SecurityAuthenticationTokenFilter(FORM_BASED_LOGIN_ENTRY_POINT,
                authenticationSuccessHandler, authenticationFailureHandler, objectMapper);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    private AppTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter() {
        List<String> pathsToSkip = Arrays.asList(TOKEN_REFRESH_ENTRY_POINT, FORM_BASED_LOGIN_ENTRY_POINT);
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, TOKEN_BASED_AUTH_ENTRY_POINT);
        AppTokenAuthenticationProcessingFilter filter = new AppTokenAuthenticationProcessingFilter(matcher,
                authenticationFailureHandler);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(securityAuthenticationProvider);
        auth.authenticationProvider(stockAuthProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/actuator/**", "/instances/**").disable() // We don't need CSRF for JWT based authentication
                .exceptionHandling().authenticationEntryPoint(this.securityAuthenticationEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests().antMatchers(FORM_BASED_LOGIN_ENTRY_POINT).permitAll() // Login

                .antMatchers(TOKEN_REFRESH_ENTRY_POINT).permitAll() // Token
                // refresh
                // end-point
                .antMatchers("/console", "/auth/**").permitAll() // H2 Console Dash-board -
                // only for testing
                .and().authorizeRequests().antMatchers(TOKEN_BASED_AUTH_ENTRY_POINT).authenticated() // Protected
                // API
                // End-points
                .and().addFilterBefore(new CorsConfig(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildSecurityLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(),
                        UsernamePasswordAuthenticationFilter.class);
//        http.csrf()
//                .ignoringAntMatchers("/actuator/**", "/instances/**");
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/assets/**", "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

    /*@Component
    public class LoginAttemptsLogger {

        @EventListener
        public void auditEventHappened(
                AuditApplicationEvent auditApplicationEvent) {

            AuditEvent auditEvent = auditApplicationEvent.getAuditEvent();
            System.out.println("Principal " + auditEvent.getPrincipal()
                    + " - " + auditEvent.getType());

            WebAuthenticationDetails details =
                    (WebAuthenticationDetails) auditEvent.getData().get("details");
            System.out.println("Remote IP address: "
                    + details.getRemoteAddress());
            System.out.println("  Session Id: " + details.getSessionId());
        }
    }*/

    /*@Component
    public class ExposeAttemptedPathAuthorizationAuditListener
            extends AbstractAuthorizationAuditListener {

        public static final String AUTHORIZATION_FAILURE
                = "AUTHORIZATION_FAILURE";

        @Override
        public void onApplicationEvent(AbstractAuthorizationEvent event) {
            if (event instanceof AuthorizationFailureEvent) {
                onAuthorizationFailureEvent((AuthorizationFailureEvent) event);
            }
        }

        private void onAuthorizationFailureEvent(
                AuthorizationFailureEvent event) {
            Map<String, Object> data = new HashMap<>();
            data.put(
                    "type", event.getAccessDeniedException().getClass().getName());
            data.put("message", event.getAccessDeniedException().getMessage());
            data.put(
                    "requestUrl", ((FilterInvocation)event.getSource()).getRequestUrl() );

            if (event.getAuthentication().getDetails() != null) {
                data.put("details",
                        event.getAuthentication().getDetails());
            }
            publish(new AuditEvent(event.getAuthentication().getName(),
                    AUTHORIZATION_FAILURE, data));
        }
    }*/


    /*@Component
    public class LoginAttemptsLogger {

        @EventListener
        public void auditEventHappened(
                AuditApplicationEvent auditApplicationEvent) {
            AuditEvent auditEvent = auditApplicationEvent.getAuditEvent();

            System.out.println("Principal " + auditEvent.getPrincipal()
                    + " - " + auditEvent.getType());

            WebAuthenticationDetails details
                    = (WebAuthenticationDetails) auditEvent.getData().get("details");

            System.out.println("  Remote IP address: "
                    + details.getRemoteAddress());
            System.out.println("  Session Id: " + details.getSessionId());
            System.out.println("  Request URL: "
                    + auditEvent.getData().get("requestUrl"));
        }
    }*/
}
