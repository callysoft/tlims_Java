package com.tlimskech.marketplace.global.contact;

import com.tlimskech.marketplace.core.data.Active;
import com.tlimskech.marketplace.core.data.BaseModel;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ad_contact")
@Active
public class Contact extends BaseModel {

    private String name;
    private String phoneNumber;
    private String email;
}
