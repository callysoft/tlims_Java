package com.tlimskech.marketplace.category.beautyhealth;

import com.tlimskech.marketplace.ad.Ad;
import com.tlimskech.marketplace.category.fashion.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "cat_beauty")
@DiscriminatorValue("beauty")
public class Beauty extends Ad {

    @Enumerated(EnumType.STRING)
    private Gender gender;
    String color;
}
