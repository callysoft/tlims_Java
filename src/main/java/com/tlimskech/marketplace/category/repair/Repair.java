package com.tlimskech.marketplace.category.repair;

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
@Table(name = "cat_repair")
@DiscriminatorValue("repair")
@Active
public class Repair extends Ad {

    private Boolean contactForPrice;
}
