package com.tlimskech.marketplace.category.beautyhealth;

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
@Table(name = "cat_beauty")
@DiscriminatorValue("beauty")
@Active
public class Beauty extends Ad {

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String color;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "scent_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "scent"))})
    private CodeValue scent;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "formula_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "formula"))})
    private CodeValue formulation;
    private String volume;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "tone_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "tone"))})
    private CodeValue tone;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "skin_type_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "skin_type"))})
    private CodeValue skinType;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "trgt_area_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "trgt_area"))})
    private CodeValue targetArea;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "benefits_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "benefits"))})
    private CodeValue benefits;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "age_grp_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "age_grp"))})
    private CodeValue ageGroup;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "vPackage_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "vPackage"))})
    private CodeValue vPackage;

}
