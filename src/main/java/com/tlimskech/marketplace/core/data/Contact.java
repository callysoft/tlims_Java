package com.tlimskech.marketplace.core.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact implements Serializable {

    private String name;
    private String phoneNumber;
    private String email;
}
