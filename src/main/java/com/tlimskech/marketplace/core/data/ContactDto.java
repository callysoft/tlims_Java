package com.tlimskech.marketplace.core.data;

import com.tlimskech.marketplace.global.contact.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactDto implements Serializable {

    private String name;
    private String phoneNumber;
    private String email;

    public static ContactDto copyFromContact(Contact contact) {
        return ContactDto.builder().email(contact.getEmail())
                .name(contact.getName())
                .phoneNumber(contact.getPhoneNumber()).build();
    }
}
