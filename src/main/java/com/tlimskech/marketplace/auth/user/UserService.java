package com.tlimskech.marketplace.auth.user;

import com.tlimskech.marketplace.auth.data.ChangePasswordRequest;
import com.tlimskech.marketplace.auth.role.Role;
import com.tlimskech.marketplace.core.data.SearchRequest;
import com.tlimskech.marketplace.core.service.BaseService;
import com.tlimskech.marketplace.core.valueobject.facebook_vo.FbUser;
import com.tlimskech.marketplace.exception.ApplicationException;
import com.tlimskech.marketplace.exception.DataNotFoundException;
import com.tlimskech.marketplace.exception.PasswordUnMatchException;
import com.tlimskech.marketplace.notification.Notification;
import com.tlimskech.marketplace.notification.NotificationService;
import com.tlimskech.marketplace.security.login.SecurityAuthenticationProvider;
import com.tlimskech.marketplace.security.login.UserContext;
import com.tlimskech.marketplace.security.utils.AppTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UserService implements BaseService<User, Long> {

    private final UserRepository userRepository;
    private NotificationService notificationService;
    @Value("${email.verification.link}")
    private String verificationLink;
    @Value("${app.FACEBOOK_GRAPH_API}")
    private String fbUri;
    private RestTemplate restTemplate;
    private final AppTokenUtil appTokenUtil;

    public UserService(UserRepository userRepository, NotificationService notificationService, RestTemplate restTemplate, AppTokenUtil appTokenUtil) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
        this.restTemplate = restTemplate;
        this.appTokenUtil = appTokenUtil;
    }

    @Override
    public User create(User user) {
        user.setRole(Role.MEMBER);
        if (!StringUtils.isEmpty(user.getPassword())) {
            user.encryptPassword();
        }
        if (exists(user)) throw new ApplicationException(String.format("User with email %s already exist", user.getEmail()));
        user = userRepository.save(user);
        sendVerificationMessage(user);
        return user;
    }

    private void sendVerificationMessage(User user) {
        Map<String, String> variables = new HashMap<>();
        variables.put("link", verificationLink + "/" + user.getCode());
        variables.put("name", String.format("%s %s", user.getLastName(), user.getFirstName()));

        Notification message = Notification.builder()
                .content(variables)
                .receipient(user.getEmail())
                .subject("Email Account Verification")
                .templateName("registration")
                .build();
        notificationService.prepareAndSendHtmlMessage(message);
    }

    @Override
    public User update(User user) {
        User found = this.findById(user.getId()).orElseThrow(() -> new DataNotFoundException("User not found"));
        found.copyForUpdate(user, "role", "password");
        if (exists(found)) throw new ApplicationException(String.format("User with email %s already exist", user.getEmail()));
        return userRepository.save(found);
    }

    @Override
    public boolean exists(User user) {
        return ObjectUtils.isEmpty(user.getId()) ? userRepository.findByEmail(user.getEmail()) != null
                : userRepository.findByEmailAndIdNot(user.getEmail(), user.getId()).isPresent();
    }

    public User updateProfile(User user) {
        User found = findByUsername(getCurrentUser()).orElseThrow(() -> new DataNotFoundException("User not found"));
        found.copyForUpdate(user, "role", "password");
        return userRepository.save(found);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public Page<User> findAll(User user, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Page<User> findAll(SearchRequest request, Pageable p) {
        return userRepository.findAll(new User().searchPredicate(request), p);
    }

    public Optional<User> findByUsername(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public void changePassword(ChangePasswordRequest passwordRequest) {
        User user = findByUsername(getCurrentUser()).orElseThrow(() -> new DataNotFoundException("User not found"));
        boolean matches = SecurityAuthenticationProvider.ENCODER.matches(passwordRequest.getCurrentPassword(), user.getPassword());
        if (matches) {
            user.setPassword(passwordRequest.getPassword());
            user.encryptPassword();
            userRepository.save(user);
        } else {
            throw new PasswordUnMatchException("Current password is invalid");
        }
    }

    public static String getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || "anonymousUser".equals(authentication.getPrincipal()))
            return "SYSTEM";
        UserContext context = (UserContext) authentication.getPrincipal();
        return context.getEmail();
    }

    public void verifyUser(String code) {
        User user = userRepository.findByCode(code).orElseThrow(() -> new DataNotFoundException("User not found"));
        user.setVerified(Boolean.TRUE);
        userRepository.save(user);
    }

    public Map<String, String> fbLogin(String authorizationCode) {
        String[] fields = {"name", "email", "first_name", "last_name", "picture"};
        log.info("Facebook authorization code: {}", authorizationCode);

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(fbUri)
                .queryParam("fields", String.join(",", fields))
                .queryParam("access_token", authorizationCode).build();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<FbUser> exchange = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, FbUser.class);
        if (ObjectUtils.isEmpty(exchange.getBody())) {
            throw new DataNotFoundException("User details not found");
        }
        FbUser fbUser = exchange.getBody();
        User user = User.builder().displayName(fbUser.getName()).email(fbUser.getEmail()).firstName(fbUser.getFirst_name())
                .lastName(fbUser.getLast_name()).verified(Boolean.TRUE).imageUrl(fbUser.getPicture().getData().getUrl()).build();
        if (exists(user)) {
            Optional<User> userOptional = findByUsername(user.getEmail());
            return generateToken(userOptional.get());
        }
        User saved = create(user);
        return generateToken(saved);
    }

    private Map<String, String> generateToken(User user) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(user.getRole().getDescription());
        UserContext userContext = UserContext.create(user.getEmail(), grantedAuthorities);
        return User.generateAuthToken(appTokenUtil.generateToken(userContext), user.getRole().getName(), user);
    }
}
