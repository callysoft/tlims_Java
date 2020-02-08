package com.tlimskech.marketplace.category.homegarden;

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
@Table(name = "cat_garden")
@DiscriminatorValue("garden")
@Active
public class HomeGarden extends Ad {

    private String color;
}
