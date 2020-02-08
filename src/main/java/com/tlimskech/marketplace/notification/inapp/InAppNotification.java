package com.tlimskech.marketplace.notification.inapp;

import com.tlimskech.marketplace.core.data.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "core_inapp_ntfy")
public class InAppNotification extends Data {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(nullable = false, name = "msg_subj")
    private String subject;
    @Column(name = "msg_read")
    private boolean read;
}
