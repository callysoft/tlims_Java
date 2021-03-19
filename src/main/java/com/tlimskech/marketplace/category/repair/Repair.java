package com.tlimskech.marketplace.category.repair;

import com.tlimskech.marketplace.ad.Ad;
import com.tlimskech.marketplace.core.data.Active;
import com.tlimskech.marketplace.core.data.CodeValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "cat_repair")
@DiscriminatorValue("repair")
@Active
public class Repair extends Ad {

    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "shape_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "shape"))})
    private CodeValue shape;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "frame_mat_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "frame_mat"))})
    private CodeValue frameMaterial;
@AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "others_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "others"))})
    private CodeValue others;
}
