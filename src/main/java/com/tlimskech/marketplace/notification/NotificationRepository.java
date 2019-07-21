package com.tlimskech.marketplace.notification;

import com.tlimskech.marketplace.core.data.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByNotificationStatus(Status status);
}
