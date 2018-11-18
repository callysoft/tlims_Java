package com.tlimskech.marketplace.category.mobile;

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
@Table(name = "cat_mobile")
@DiscriminatorValue("mobile")
@Active
public class Mobile extends Ad {

    private String model;
    private String storageCapacity;
    private String color;
    private String screenSize;
    private String ram;
    private String os;
    private Boolean isExchangeable;

}
