package com.tlimskech.marketplace.category.pet;

import com.tlimskech.marketplace.ad.Ad;
import com.tlimskech.marketplace.category.fashion.Gender;
import com.tlimskech.marketplace.core.data.Active;
import com.tlimskech.marketplace.core.data.CodeValue;
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
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "age_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "age_nm"))})
    private CodeValue age;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "breed_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "breed_nm"))})
    private CodeValue breed;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "brdtyp_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "brdtyp_nm"))})
    private CodeValue breedType;
@AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "others_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "others"))})
    private CodeValue others;
}
