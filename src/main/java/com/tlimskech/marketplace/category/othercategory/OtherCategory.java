package com.tlimskech.marketplace.category.services;

import com.tlimskech.marketplace.ad.Ad;
import com.tlimskech.marketplace.core.data.Active;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "cat_othercategory")
@DiscriminatorValue("other_sub")
@Active
public class Services extends Ad {

    private Boolean contactForPrice;
    /*private String serviceArea;
    private Boolean roundClockServiceFg;
    private String serviceFeatures;
    private Double duration;
    private String level;
    private String subType;
    private String topic;*/
}
