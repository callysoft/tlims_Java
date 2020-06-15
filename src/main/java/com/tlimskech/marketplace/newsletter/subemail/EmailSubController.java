package com.tlimskech.marketplace.newsletter.subemail;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/emailSubs")
@Validated
@Log4j2
public class EmailSubController {

    private final EmailSubRepository emailRepository;

    public EmailSubController(EmailSubRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @PostMapping("subscribe")
    public ResponseEntity<?> subscribe(@RequestBody @NotBlank @Email(message = "Not a valid email address") String email) {
        log.info("Email to subscribe {}", email);
        EmailSub emailSub = EmailSub.builder().email(email).isActive(true).build();
        EmailSub save = emailRepository.save(emailSub);
        log.info("Email {} subscribed to newsletter", email);
        return ResponseEntity.ok(save);
    }

    @GetMapping("unsubscribe/{email}")
    public ResponseEntity<?> unsubscribe(@PathVariable @NotBlank @Email(message = "Not a valid email address") String email) {
        log.info("Email to unsubscribe {}", email);
        EmailSub emailSub = EmailSub.builder().email(email).isActive(false).build();
        EmailSub save = emailRepository.save(emailSub);
        log.info("Email {} unsubscribed from newsletter", email);
        return ResponseEntity.ok(save);
    }
}
