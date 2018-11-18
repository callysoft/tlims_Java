package com.tlimskech.marketplace.category.vehicle;

import com.tlimskech.marketplace.ad.Ad;
import com.tlimskech.marketplace.core.data.Active;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "cat_vehicle")
@DiscriminatorValue("vehicle")
@Active
public class Vehicle extends Ad {

    private String make;
    private String model;
    private String year;
    private String trim;
    private String color;
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    private String mileage;
    private Boolean registered;
    private Boolean exchangeable;
}
