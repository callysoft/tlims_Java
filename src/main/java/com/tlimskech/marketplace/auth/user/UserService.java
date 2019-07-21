package com.tlimskech.marketplace.auth.user;

import com.tlimskech.marketplace.auth.data.ChangePasswordRequest;
import com.tlimskech.marketplace.auth.role.Role;
import com.tlimskech.marketplace.core.data.SearchRequest;
import com.tlimskech.marketplace.core.service.BaseService;
import com.tlimskech.marketplace.exception.DataNotFoundException;
import com.tlimskech.marketplace.exception.PasswordUnMatchException;
import com.tlimskech.marketplace.notification.Notification;
import com.tlimskech.marketplace.notification.NotificationService;
import com.tlimskech.marketplace.security.login.SecurityAuthenticationProvider;
import com.tlimskech.marketplace.security.login.UserContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService implements BaseService<User, Long> {

    private final UserRepository userRepository;
    private NotificationService notificationService;
    @Value("${email.verification.link}")
    private String verificationLink;

    public UserService(UserRepository userRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    @Override
    public User create(User user) {
        user.setStatus(Boolean.TRUE);
        user.setStatus(Boolean.FALSE);
        user.setRole(Role.MEMBER);
        user.encryptPassword();
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
        return userRepository.save(found);
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
}
