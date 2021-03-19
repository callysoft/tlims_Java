package com.tlimskech.marketplace.category.fashion;


import com.tlimskech.marketplace.ad.Ad;
import com.tlimskech.marketplace.core.data.Active;
import com.tlimskech.marketplace.core.data.CodeValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "cat_fashion")
@DiscriminatorValue("fashion")
@Active
public class Fashion extends Ad {

    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Lob
    @Column(name = "mat")
    private String material;
    @Lob
    @Column(name = "mat_2")
    private String material2;
    @Lob
    private String fastening;
    @Lob
    private String color;
    private String closure;
    @Lob
    private String fashionSize;
    @Lob
    private String fashionStyle;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "stone_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "stone_nm"))})
    private CodeValue jewelStone;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "movement_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "movement_nm"))})
    private CodeValue movement;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "display_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "display_nm"))})
    private CodeValue display;
@AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "others_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "others"))})
    private CodeValue others;
    @Lob
    private String features;
    @Lob
    private String typeList;
}
