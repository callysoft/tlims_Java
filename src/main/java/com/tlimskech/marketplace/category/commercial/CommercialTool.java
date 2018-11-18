package com.tlimskech.marketplace.category.commercial;

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
@Table(name = "cat_comm_tool")
@DiscriminatorValue("comm_tool")
@Active
public class CommercialTool extends Ad {

    private String powerSource;
    private String trayNo;
    private String deckNo;
    private String voltage;
    private String maxTemperature;
    private String shape;
    private String weight;
    private Boolean contactForPrice;
}
