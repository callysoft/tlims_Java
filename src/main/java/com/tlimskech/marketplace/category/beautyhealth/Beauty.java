package com.tlimskech.marketplace.category.beautyhealth;

import com.tlimskech.marketplace.ad.Ad;
import com.tlimskech.marketplace.category.fashion.Gender;
import com.tlimskech.marketplace.core.data.Active;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "cat_beauty")
@DiscriminatorValue("beauty")
@Active
public class Beauty extends Ad {

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String color;
    private String scent;
    private String formulation;
    private String volume;
    private String tone;
    private String skinType;
    private String targetArea;
    private String benefits;
    private String ageGroup;
    private String vPackage;

}
