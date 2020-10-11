package com.tlimskech.marketplace.notification;

import com.tlimskech.marketplace.core.data.Status;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.SneakyThrows;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class NotificationService {

    private final JavaMailSender mailSender;
    private final NotificationRepository notificationRepository;
    private final Configuration freemarkerConfig;

    public NotificationService(JavaMailSender mailSender, NotificationRepository notificationRepository, Configuration freemarkerConfig) {
        this.mailSender = mailSender;
        this.notificationRepository = notificationRepository;
        this.freemarkerConfig = freemarkerConfig;
    }

    @Async
    @SneakyThrows
    public void prepareAndSend(Notification notification) {
        notification.setNotificationStatus(Status.PENDING);
        notification.setNextRetry(new Date());
        notificationRepository.save(notification);
    }

    @SneakyThrows
    public void prepareAndSendHtmlMessage(Notification notification) {
        String message = build(notification.getContent(), notification.getTemplateName());
        notification.setPlainText(message);
        prepareAndSend(notification);
    }

    public String build(Map<String, String> templateModel, String templateName) throws IOException, TemplateException {
        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/mailtemplates");
        Template freemarkerTemplate = freemarkerConfig.getTemplate(templateName);
        return FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplate, templateModel);
    }

    private void fireEmail(Notification notification) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            messageHelper.setFrom("admin@instantcl.com", "Tlims-Kech Market Place");
            messageHelper.setTo(notification.getReceipient());
            messageHelper.setSubject(notification.getSubject());
            messageHelper.setText(notification.getPlainText(), !StringUtils.isEmpty(notification.getTemplateName()));
        };
        try {
            mailSender.send(messagePreparator);
            notification.setNotificationStatus(Status.SUCCESS);
            notificationRepository.save(notification);
        } catch (MailException e) {
            if (notification.getNoRetry() > 5) {
                notification.setNotificationStatus(Status.FAILED);
            } else {
                int retryTimes = notification.getNoRetry() + 1;
                notification.setNoRetry(retryTimes);
                notification.setNextRetry(DateUtils.addMinutes(new Date(), retryTimes));
                notification.setNotificationStatus(Status.PENDING);
            }
            notificationRepository.save(notification);
            e.printStackTrace();
        }
    }

    @Scheduled(fixedRate = 10000)
    public void scheduleEmailSender() {
        List<Notification> notifications = notificationRepository.findByNotificationStatus(Status.PENDING);
        if (notifications.isEmpty()) return;
        notifications.forEach(notification -> {
            if (new Date().compareTo(notification.getNextRetry()) > -1) {
                this.fireEmail(notification);
            }
        });
    }

}
