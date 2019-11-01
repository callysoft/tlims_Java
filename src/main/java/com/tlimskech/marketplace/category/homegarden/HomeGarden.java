package com.tlimskech.marketplace.category.homegarden;

import com.tlimskech.marketplace.ad.Ad;
import com.tlimskech.marketplace.core.data.Active;
import com.tlimskech.marketplace.core.data.CodeValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "cat_garden")
@DiscriminatorValue("garden")
@Active
public class HomeGarden extends Ad {

    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "color_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "color"))})
    private CodeValue color;
}
