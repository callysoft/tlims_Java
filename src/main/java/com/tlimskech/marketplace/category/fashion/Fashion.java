package com.tlimskech.marketplace.category.fashion;


import com.tlimskech.marketplace.ad.Ad;
import com.tlimskech.marketplace.core.data.Active;
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
    private String material;
    private String color;
    private String closure;
    private Integer size;
    private String fashionStyle;
    private String jewelStone;
    private String features;
    private String typeList;
}
