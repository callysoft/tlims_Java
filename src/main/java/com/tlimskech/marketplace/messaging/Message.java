package com.tlimskech.marketplace.messaging;

import com.tlimskech.marketplace.core.data.BaseModel;
import com.tlimskech.marketplace.core.data.CodeValue;
import com.tlimskech.marketplace.global.contact.Contact;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Table(name = "core_msg")
@Entity
@Data
public class Message extends BaseModel {

    @OneToOne
    private Contact contact;
    @Lob
    private String content;
    @Enumerated(EnumType.STRING)
    private MessageSource source;
    @NotBlank(message = "Recipient is required")
    private String recipient;
    @Transient
    private Long postId;
    private String postCode;
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "post_id")),
            @AttributeOverride(name = "name", column = @Column(name = "post_title"))})
    private CodeValue post;
}

enum MessageSource {
    INTERNAL, EXTERNAL
}
