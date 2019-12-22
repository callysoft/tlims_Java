package com.tlimskech.marketplace.category.commercial;

import com.tlimskech.marketplace.ad.Ad;
import com.tlimskech.marketplace.core.data.Active;
import com.tlimskech.marketplace.core.data.CodeValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "cat_comm_tool")
@DiscriminatorValue("comm_tool")
@Active
public class CommercialTool extends Ad {

    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "pw_src_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "pw_src_nm"))})
    private CodeValue powerSource;
    private String trayNo;
    private String deckNo;
    private String voltage;
    private String maxTemperature;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "shape_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "shape_nm"))})
    private CodeValue shape;
    private String weight;
    private Boolean contactForPrice;
}
