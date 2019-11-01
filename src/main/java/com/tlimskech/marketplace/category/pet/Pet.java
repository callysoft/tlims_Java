package com.tlimskech.marketplace.category.pet;

import com.tlimskech.marketplace.ad.Ad;
import com.tlimskech.marketplace.category.fashion.Gender;
import com.tlimskech.marketplace.core.data.Active;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "cat_pets")
@DiscriminatorValue("pets")
@Active
public class Pet extends Ad {

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String age;
    private String breed;
    private String breedType;
}
