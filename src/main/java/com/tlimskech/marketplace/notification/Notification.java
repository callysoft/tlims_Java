package com.tlimskech.marketplace.notification;

import com.tlimskech.marketplace.core.data.BaseModel;
import com.tlimskech.marketplace.core.data.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "core_messages")
public class Notification extends BaseModel {

    @Column(length = 6000)
    private String plainText;
    private String subject;
    private String templateName;
    private String receipient;
    @Transient
    private Map<String, String> content = new HashMap<>();
    @Enumerated(EnumType.STRING)
    private Status notificationStatus;
    private int noRetry;
}
