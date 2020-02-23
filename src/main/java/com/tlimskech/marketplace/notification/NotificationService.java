package com.tlimskech.marketplace.notification;

import com.tlimskech.marketplace.core.data.Status;
import lombok.SneakyThrows;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
public class NotificationService {

    private JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    private final NotificationRepository notificationRepository;

    public NotificationService(JavaMailSender mailSender, TemplateEngine templateEngine, NotificationRepository notificationRepository) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.notificationRepository = notificationRepository;
    }


    @Async
    @SneakyThrows
    public void prepareAndSend(Notification notification) {
        notification.setNotificationStatus(Status.PENDING);
        notificationRepository.save(notification);
    }

    public void prepareAndSendHtmlMessage(Notification notification) {
        String message = build(notification.getContent(), notification.getTemplateName());
        notification.setPlainText(message);
        prepareAndSend(notification);
    }

    public String build(Map<String, String> content, String templateName) {
        Context context = new Context();
        content.forEach(context::setVariable);
        return templateEngine.process("mailtemplates/" + templateName, context);
    }

    @Scheduled(fixedRate = 10000)
    public void scheduleEmailSender() {
        List<Notification> messageList = notificationRepository.findByNotificationStatus(Status.PENDING);
        if (messageList.isEmpty()) return;
        messageList.forEach(mailMessage -> {
            MimeMessagePreparator messagePreparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
                messageHelper.setFrom("admin@instantcl.com", "Tlims-Kech Market Place");
                messageHelper.setTo(mailMessage.getReceipient());
                messageHelper.setSubject(mailMessage.getSubject());
                messageHelper.setText(mailMessage.getPlainText(), !StringUtils.isEmpty(mailMessage.getTemplateName()));
            };
            try {
                mailSender.send(messagePreparator);
                mailMessage.setNotificationStatus(Status.SUCCESS);
                notificationRepository.save(mailMessage);
            } catch (MailException e) {
                if (mailMessage.getNoRetry() > 5) {
                    mailMessage.setNotificationStatus(Status.FAILED);
                } else {
                    mailMessage.setNoRetry((mailMessage.getNoRetry() + 1));
                    mailMessage.setNotificationStatus(Status.PENDING);
                }
                notificationRepository.save(mailMessage);
                e.printStackTrace();
                // runtime exception; compiler will not force you to handle it
            }
        });

    }

}
