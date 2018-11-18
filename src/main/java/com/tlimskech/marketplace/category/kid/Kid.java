package com.tlimskech.marketplace.category.kid;

import com.tlimskech.marketplace.ad.Ad;
import com.tlimskech.marketplace.category.fashion.Gender;
import com.tlimskech.marketplace.core.data.Active;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "cat_kid")
@DiscriminatorValue("kid")
@Active
public class Kid extends Ad {

    private String color;
    private String ageGroup;
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
